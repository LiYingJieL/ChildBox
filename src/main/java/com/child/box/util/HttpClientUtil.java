package com.child.box.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * http client 的一些工具
 *
 * @author 张有良
 * @version 1.0
 * @since 2012-06-20
 */
public class HttpClientUtil {

    /**
     * 当HTTP请求方法为POST方式时，从输入流中获取请求数据
     *
     * @return 如果请求的http信息与协议中定义的不符，返回null
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static String getRequestData(HttpServletRequest request) throws Exception {
        String charset = getCharset(request);
        StringBuffer buffer = new StringBuffer();
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new InputStreamReader(request.getInputStream(), charset));
            String tempLine = null;
            while ((tempLine = rd.readLine()) != null) {
                buffer.append(tempLine);
            }
        } finally {
            try {
                if (rd != null) {
                    rd.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString().trim();
    }

    //获取请求的字符集
    public static String getCharset(HttpServletRequest request) {
        if (request != null) {
            return request.getCharacterEncoding();
        }
        return "UTF-8";
    }
}
