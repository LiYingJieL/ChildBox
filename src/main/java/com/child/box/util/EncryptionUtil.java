package com.child.box.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @description 加密
 * @author xxm
 * @date 2018/5/17
 **/
public class EncryptionUtil {

    private static final String SALT_PASSWORD = "3#cc87tyty@zh@";

    public static String encryptPassword(String password){
        return DigestUtils.sha256Hex(SALT_PASSWORD+password);
    }

    public static String encrypt(String u,String p){
        return DigestUtils.sha256Hex(u+p);
    }

    public static String generateToken(Long id) {
        return DigestUtils.sha256Hex("wx+w@@t(67a%#ng11" + id.toString()+ "+201a-b099@right");
    }

    public static void main(String[] args) {
        System.out.println("psw:"+encryptPassword("123456"));
    }
}
