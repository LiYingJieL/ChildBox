package com.child.box.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public static String encryt(Long itempackId) {
        String origin = String.valueOf(itempackId);
        byte[] bval;
        try {
            bval = origin.getBytes(StandardCharsets.ISO_8859_1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(bval);
    }

    public static String decryt(String src) {
        return new String(Base64.getDecoder().decode(src));
    }

    public static void main(String[] args) {
        System.out.println(Base64Util.decryt("OTUyNjg="));
    }
}
