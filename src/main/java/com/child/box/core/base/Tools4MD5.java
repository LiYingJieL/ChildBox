package com.child.box.core.base;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import com.child.box.util.UuidUtil;

public class Tools4MD5 {

	public static String toMD5Password(String un, String pd){
		return new SimpleHash("SHA-1", un, pd).toString();	//密码加密
	}

	public static String generateToken() {
		return DigestUtils.sha256Hex(UuidUtil.get32UUID());
	}
}
