package com.child.box.service;

import com.child.box.core.base.BaseService;
import com.child.box.entity.BugRecord;
import com.child.box.mapper.BugRecordMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.child.box.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xxm
 * @date 2018-05-25
 **/
@Service
public class BugRecordService extends BaseService {

    @Autowired
    private BugRecordMapper bugRecordMapper;

    public void save(HttpServletRequest request, Exception ex){
        BugRecord entity = new BugRecord();

        entity.setRequestMethod(request.getMethod());
        if(!StringUtils.isEmpty(request.getQueryString())){
            entity.setRequestUrl(request.getRequestURL()+"?"+request.getQueryString());
        }else {
            entity.setRequestUrl(request.getRequestURL().toString());
        }
        entity.setUserAgent(StringUtil.valueOf(request.getHeader("User-Agent")));
        entity.setContentType(request.getContentType());

        entity.setCause(StringUtil.valueOf(ex.getCause()));
        entity.setMessage(StringUtil.valueOf(ex.getMessage()));

        entity.setStackTrace(StringUtil.valueOf(ExceptionUtils.getFullStackTrace(ex)));

        bugRecordMapper.insertSelective(entity);
    }

}
