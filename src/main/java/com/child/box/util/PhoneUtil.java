package com.child.box.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 电话号码相关
 * @author xxm
 * @date 2018-05-17
 **/
public class PhoneUtil {

    private static Pattern PHONENUMBER_PATTERN = Pattern.compile("^((13[0-9])|(14[5-9])|(15[0-3,5-9])|(16[6])|(17[0-8])|(18[0-9])|(19[8,9]))\\d{8}$");

    private static Pattern NUMBER_PARRERN = Pattern.compile("[0-9]*");

    public static boolean isPhoneNumber(String phone){
        Matcher m = PHONENUMBER_PATTERN.matcher(phone);
        return m.matches();
    }

    public static String dealPhone(String phone){
        if (StringUtils.isEmpty(phone)) {
            return "";
        }
        if(isPhoneNumber(phone)){
            return phone.substring(0, 3)+"****"+phone.substring(7, 11);
        }
        return phone;
    }

    public static boolean isNumeric(String phone) {
        Matcher isNum = NUMBER_PARRERN.matcher(phone);
        return isNum.matches();
    }
}
