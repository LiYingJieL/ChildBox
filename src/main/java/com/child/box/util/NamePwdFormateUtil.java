package com.child.box.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lyjie
 * @description 用户名密码格式判断
 * @date 2018-05-17
 **/
public class NamePwdFormateUtil {

    private static Pattern USERNAME = Pattern.compile("^[A-Za-z0-9_]+$");

    private static Pattern PASSWORD = Pattern.compile("^[A-Za-z0-9_]{6,16}$");

    public static boolean userNameFormat(String userName) {
        Matcher m = USERNAME.matcher(userName);
        return m.matches();
    }

    public static boolean pwdFormat(String password) {
        Matcher m = PASSWORD.matcher(password);
        return m.matches();
    }

    public static boolean itemNameFormat(String name) {
        int maxNum = 24;
        return name.length() > maxNum;
    }

}
