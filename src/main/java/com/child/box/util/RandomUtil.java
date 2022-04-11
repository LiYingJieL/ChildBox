package com.child.box.util;

import java.util.Random;

/**
 * @author xxm
 * @description
 * @date 2017年7月4日
 */

public class RandomUtil {

    /**
     * 4位随机码
     *
     * @return
     */
    public static String fourDigit() {
        Random ran = new Random();
        int code = 1000 + ran.nextInt(9000);
        return String.valueOf(code);
    }

    /**
     * 6位随机码
     *
     * @return
     */
    public static String sixDigit() {
        Random ran = new Random();
        int code = 100000 + ran.nextInt(900000);
        return String.valueOf(code);
    }
}
