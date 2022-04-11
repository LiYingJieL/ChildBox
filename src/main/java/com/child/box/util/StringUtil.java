package com.child.box.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字符串相关方法
 * 
 */
public class StringUtil {

	private static String htmlStayleStart="<HTML><head> <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>     <meta http-equiv='Cache-Control' content='no-cache'> <meta content='marksense' name='author'>  <meta name='viewport' content='width=device-width,initial-scale=1.33,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no'> <meta content='yes' name='apple-mobile-web-app-capable'> <meta content='black' name='apple-mobile-web-app-status-bar-style'> <meta content='telephone=yes' name='format-detection' /> <style> a:link,a:visited{  text-decoration:none; color:#4d4f4e; }  ul,li,form,h1,h2,h3,h4,h5,h6,pre,p,figure,pre,button{ margin:0; padding:0; } ul{  overflow:hidden; list-style:none; } pre{ white-space:pre-wrap;  word-wrap:break-word; } ::-webkit-input-placeholder { color:#bababa; }  ::-moz-placeholder { color:#bababa; } :-ms-input-placeholder { color:#bababa; }  body{ margin:0 auto; width:100%; color:#4d4f4e; font-size:18px;  -webkit-text-size-adjust:none;} <%--.header{ width:100%; height:55px;  text-align:center; line-height:55px; background:#e3e3e3;  background-size:contain; color:#9dd3f5; box-shadow:0 2px 5px #494949;  position:relative; } --%> .header{ width:100%; height:65px; text-align:center;  line-height:55px; background-size:contain; color:#9dd3f5; box-shadow:0 2px 5px  #494949; position:relative; } .header h1{ font-size:20px; color:#000; } .view{  line-height:24px; padding:5px 10px;  font-size:15px; } .view p{  margin:0;} .view p em{ color:#6c6d6c; font-style:normal; }  .view p em a{ color:#4d4f4e; } .view pre{ margin:0; padding:0 15px;  line-height:20px; color:#6c6d6c; white-space:pre-wrap; word-wrap:break-word; }  .view img{ max-width:100%;} .youduiqi{ text-align:right;} .juzhong{  text-align:center;} .article-time{  	  position: absolute ; 	  margin-top:  -20px; 	  height: 12px; 	  padding-right: 13px; 	  padding-left: 20px; 	   line-height: 12px; 	  color:#000; 	  font:12px/1.75  '\5B8B\4F53','Arial',sans-serif; }  .encart{ width: 80%; height: 40px;  line-height: 40px; text-align: center; background-color: #e13737; font-size:  22px; color: #fff; font-weight: bold; border-radius: 10px; border: none;  margin:0 auto;  } </style>  </head><BODY> <article class='view'>";
	private static String htmlStayleEnd="</article></BODY></HTML>";


	public static String valueOf(Object obj) {
		return (obj == null) ? "" : obj.toString();
	}

	public static String getShowName(String nickName,String name){
		if(StringUtils.isNotBlank(nickName)){
			return nickName;
		}else if(StringUtils.isNotBlank(name)){
			return PhoneUtil.dealPhone(name);
		}
		return "匿名";

	}

	public static String getMinAndMaxNum(String str) {
		if (str != null && str.length() > 0) {
			List<Integer> ss = new ArrayList<Integer>();
			for (String sss : str.replaceAll("[^0-9]", ",").split(",")) {
				if (sss.trim().length() > 0){
					ss.add(Integer.valueOf(sss));
				}
			}
			if (ss.size() > 0) {
				Collections.sort(ss);
				return ss.get(0) + "-" + ss.get(ss.size() - 1);
			}
		}
		return "";
	}

	public static String toHtml(String html){
		return htmlStayleStart+html+htmlStayleEnd;
	}

}
