package com.child.box.cache;

import com.child.box.core.base.Tools4MD5;
import com.child.box.core.session.UserRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.child.box.util.RedisUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author xxm
 * @date 2018-07-04
 */
@Service
public class UserCacheService {
    private Logger logger = LoggerFactory.getLogger(UserCacheService.class);
    private static final String LOGIN_KEY_PREFIX = UserCacheService.class.getName() + ":login:";
    private static final String UID_AUTH_PREFIX = "uid:auth:";

    @Autowired
    private RedisUtil redisUtil;

    public UserRef getByAuth(String auth) {
        if (StringUtils.isEmpty(auth)) {
            return null;
        }
        String key = LOGIN_KEY_PREFIX + auth;
        String id = (String) redisUtil.get(key);
        if (!StringUtils.isEmpty(id)) {
            UserRef user = new UserRef();
            user.setId(Long.valueOf(id));
            return user;
        }
        return null;
    }

    public UserRef getUserRef(Long uid){
        UserRef userRef = new UserRef();
        String auth = Tools4MD5.generateToken();
        userRef.setAuth(auth);
        this.saveUserRef(auth, uid);
        return userRef;
    }
    public void saveUserRef(String auth, Long  id) {
        String key = LOGIN_KEY_PREFIX + auth;
        redisUtil.set(key, id.toString());
        redisUtil.expire(key, 20, TimeUnit.DAYS);

        redisUtil.set(UID_AUTH_PREFIX+id,auth);
        redisUtil.expire(UID_AUTH_PREFIX+id, 20, TimeUnit.DAYS);

        logger.info("save user login auth:id="+id+",auth="+auth);
    }
}
