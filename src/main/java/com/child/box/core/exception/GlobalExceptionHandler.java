package com.child.box.core.exception;

import com.child.box.core.base.NullObjectView;
import com.child.box.core.base.Result;
import com.child.box.core.base.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.child.box.service.BugRecordService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xxm
 * @date 2018-05-25
 **/
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private BugRecordService bugRecordService;

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<NullObjectView> defaultErrorHandler(HttpServletRequest request, Exception e){
        Result<NullObjectView> result = new Result<>();
        if(e instanceof NoAuthorizationException){
            result.set(ResultType.ERROR_AUTHENTICATION);
        }else{
            result.set(ResultType.ERROR);
        }
        try {
            bugRecordService.save(request, e);
        } catch (Exception ex) {
            logger.info("bugRecord 保存失败");
        }

        result.setData(null);
        return result;
    }
}
