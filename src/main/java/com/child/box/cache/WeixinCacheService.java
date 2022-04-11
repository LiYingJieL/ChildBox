package com.child.box.cache;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class WeixinCacheService {
	
	private static final String WEIXIN_KEY = WeixinCacheService.class.getName()+":weixin:";
	
	private static final String ACCESSTOKEN_KEY = "accessToken";
	private static final String JSAPITICKET_KEY = "jsapiTicket";
	private static final String WXSESSION_KEY = "wxsessionkey";
	
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String>  valueOperations;

	public String getAccessToken(){
		String key = WEIXIN_KEY + ACCESSTOKEN_KEY;
		return valueOperations.get(key);
	}
	
	public void setAccessToken(String accessToken){
		String key = WEIXIN_KEY + ACCESSTOKEN_KEY;
		valueOperations.set(key, accessToken,2,TimeUnit.HOURS);
	}
	
	public String getJsapiTicket(){
		String key = WEIXIN_KEY + JSAPITICKET_KEY;
		return valueOperations.get(key);
	}
	
	public void setJsapiTicket(String jsapiTicket){
		String key = WEIXIN_KEY + JSAPITICKET_KEY;
		valueOperations.set(key, jsapiTicket,2,TimeUnit.HOURS);
	}
	
	public void saveSessionKey(String code,String sessionkey){
		String key = WXSESSION_KEY+":"+code;
		valueOperations.set(key, sessionkey,5,TimeUnit.MINUTES);
	}
	public String getSessionKey(String code){
		String key = WXSESSION_KEY+":"+code;
		return valueOperations.get(key);
	}
}
