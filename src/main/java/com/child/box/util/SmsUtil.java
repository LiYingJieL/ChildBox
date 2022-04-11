package com.child.box.util;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SmsUtil {
	
	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	public static void send(String mobile,String content){
//		content = content + "【微校网】";
		String res = null;
		
		//由于此处代码返回值永远小于2故注释掉VerifyCodeLog obj = VerifyCodeLog.dao.queryByMobileTime(mobile);
		//if(obj!=null&&obj.getLong("count")<2){
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				HttpClientUtils.setStrHttpParam("Uid","rcrmj", params);
				HttpClientUtils.setStrHttpParam("Key","385bd17fa0737e8d9543", params);
				HttpClientUtils.setStrHttpParam("smsMob",mobile, params);
				HttpClientUtils.setStrHttpParam("smsText",content, params);
		
				res = HttpClientUtils.postStr("http://utf8.sms.webchinese.cn","UTF-8", params);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			logger.debug("发送号码:"+mobile+"  内容="+content + "返回内容="+res);
		//}else{
		//	logger.debug("连续发送:"+mobile+"  内容="+content + "返回内容="+res);
		//}
	}
	
	public static void send2(String mobile,String content){
//		content = content + "【微校网】";
		String res = null;
		
		//由于此处代码返回值永远小于2故注释掉VerifyCodeLog obj = VerifyCodeLog.dao.queryByMobileTime(mobile);
		//if(obj!=null&&obj.getLong("count")<2){
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				HttpClientUtils.setStrHttpParam("Uid","jwts", params);
				HttpClientUtils.setStrHttpParam("Key","add59429aaf84d867d3f", params);
				HttpClientUtils.setStrHttpParam("smsMob",mobile, params);
				HttpClientUtils.setStrHttpParam("smsText",content, params);
		
				res = HttpClientUtils.postStr("http://utf8.sms.webchinese.cn","UTF-8", params);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			logger.debug("发送号码:"+mobile+"  内容="+content + "返回内容="+res);
		//}else{
		//	logger.debug("连续发送:"+mobile+"  内容="+content + "返回内容="+res);
		//}
	}
	
	public static void send3(String mobile,String content){
//		content = content + "【微校网】";
		String res = null;
		
		//由于此处代码返回值永远小于2故注释掉VerifyCodeLog obj = VerifyCodeLog.dao.queryByMobileTime(mobile);
		//if(obj!=null&&obj.getLong("count")<2){
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				HttpClientUtils.setStrHttpParam("Uid","blwh", params);
				HttpClientUtils.setStrHttpParam("Key","b9ca8dc94760abf948d7", params);
				HttpClientUtils.setStrHttpParam("smsMob",mobile, params);
				HttpClientUtils.setStrHttpParam("smsText",content, params);
		
				res = HttpClientUtils.postStr("http://utf8.sms.webchinese.cn","UTF-8", params);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
			logger.debug("发送号码:"+mobile+"  内容="+content + "返回内容="+res);
		//}else{
		//	logger.debug("连续发送:"+mobile+"  内容="+content + "返回内容="+res);
		//}
	}
	
	public static void main(String[] args) {
		System.out.println("测试短信");
		send2("18857129202", "您的《走向适宜的幼儿园课程》已下单， 请完成转账支付，转账账号请查看订购页面账号信息，支付宝转账请填写备注学校名称并且添加转账支付宝账号好友，方便我们后续为您发货和开发票！");
	}
}
