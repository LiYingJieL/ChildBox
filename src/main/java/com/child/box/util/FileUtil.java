package com.child.box.util;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class FileUtil {

    public static String copyFile(MultipartFile img) throws Exception {
        String result = "";
        if (img.isEmpty()) {
            return result;
        }
        String path = DateUtil.getYMD(new DateTime());
        String newFileName = UuidUtil.get32UUID();

        String originalFilename = img.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        OSSManageUtil.uploadFile(img,  path + "/" + newFileName + extName);

        result = path + "/" + newFileName + extName;
        return result;
    }

    public static String copyFile(InputStream inputStream) throws Exception {
        String result = "";
        String newFileName = UuidUtil.get32UUID() + ".jpg";//新文件名
        String path = DateUtil.getYMD(new DateTime());
        OSSManageUtil.uploadFile(inputStream, path + "/" + newFileName, newFileName);

        result = path + "/" + newFileName;
        return result;
    }
}
