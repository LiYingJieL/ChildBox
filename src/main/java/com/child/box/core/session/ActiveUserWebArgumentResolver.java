package com.child.box.core.session;

import com.child.box.cache.CacheService;
import com.child.box.cache.UserCacheService;
import com.child.box.core.exception.NoAuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;

/**
 * @author xxm
 * @date 2018-05-22
 **/
@Service
public class ActiveUserWebArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(ActiveUserWebArgumentResolver.class);
    @Autowired
    private CacheService cacheService;
    @Autowired
    private UserCacheService userCacheService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserRef.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Annotation[] annotations = methodParameter.getParameterAnnotations();
        if (methodParameter.getParameterType().equals(UserRef.class)) {
            for (Annotation annotation : annotations) {
                if (ActiveUser.class.isInstance(annotation)) {
                    UserRef userRef = cacheService.getAdminByAuth(webRequest.getHeader(AuthorizationConstant.TOKEN));
                    if (userRef != null && userRef.getId() != null) {
                        //logger.info("====AdminUserWebArgumentResolver.resolveArgument: ActiveUser userRef.id=" + userRef.getId());
                        return userRef;
                    } else {
                        throw new NoAuthorizationException();
                    }
                }
                if (LoginUser.class.isInstance(annotation)) {
                    UserRef userRef = userCacheService.getByAuth(webRequest.getHeader(AuthorizationConstant.TOKEN));
                    if (userRef == null || userRef.getId() == null) {
                        throw new NoAuthorizationException();
                    }
                    //logger.info("====AdminUserWebArgumentResolver.resolveArgument: LoginUser userRef.id=" + userRef.getId());
                    return userRef;
                }
            }
        }

        return WebArgumentResolver.UNRESOLVED;
    }
}
