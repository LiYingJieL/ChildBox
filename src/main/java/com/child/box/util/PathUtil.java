package com.child.box.util;

import org.apache.commons.lang.StringUtils;

public class PathUtil {

    /**
     * OSS自定义访问域名and图片存放的路径
     */
    public final static String OSS_OUTER_URL_PREFIX = "https://weixiaoabook.oss-cn-hangzhou.aliyuncs.com/";//http://www.res.51weixiao.com/

    /**
     * 根据数据库的路径，返回图片的完整路径
     */
    public static String getApiImgHttpPath(String path) {
        String img = "mdef.png";
        return getPath(path, img);
    }

    /**
     * 根据数据库的路径，返回图片的完整路径(杂志默认占位图片)
     */
    public static String getItemImgHttpPath(String path) {
        String img = "gdef.png";
        return getPath(path, img);
    }

    /**
     * 判断是否是远程路径
     */
    public static boolean isRemotePath(String path) {
        boolean condition = (path != null && (path.indexOf("http://") > -1 || path.indexOf("https://") > -1));
        return condition;
    }

    public static String getPath(String path, String img) {
        if (StringUtils.isNotBlank(path)) {
            if (isRemotePath(path)) {
                return path;
            } else {
                return OSS_OUTER_URL_PREFIX + path;
            }
        } else {
            return OSS_OUTER_URL_PREFIX + img;
        }
    }
}
