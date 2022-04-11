package com.child.box.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 对OSS服务器进行上传删除等的处理
 *
 * @author liujh
 * @ClassName: OSSManageUtil
 * @Description:
 * @date 2015-3-26 上午10:47:00
 */
public class OSSManageUtil {
    private static final String accessKeyId = "P5zAcI94OOLE9LXs";
    private static final String accessKeySecret = "D7dzmbuOqJuuhvG3UPapWzt5FdAvA7";

    private static final String bucketName = "weixiaoabook";
    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    /**
     * 得到文件列表
     *
     * @param filePath 路径名  "upload/wei_course_video/00/01/1/"
     * @return String   返回类型
     * @throws
     */
    public static List<String> getFiles(String filePath) {
        List<String> list = new ArrayList<String>();
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ObjectListing objectListing = ossClient.listObjects(bucketName,
                filePath);
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            list.add(s.getKey());
        }

        ossClient.shutdown();
        return list;
    }

    public static void uploadFile(InputStream inputStream, long filelength, String filetype, String remotePath) throws Exception {

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(filelength);
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(filetype);
        //上传文件
        ossClient.putObject(bucketName, remotePath, inputStream, objectMetadata);

        ossClient.shutdown();
    }

    public static void uploadFile(InputStream inputStream, String remotePath, String fileName) throws Exception {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//          String remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\","/")+"/";
        //创建上传Object的Metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(inputStream.available());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(contentType(fileName.substring(fileName.lastIndexOf(".") + 1)));
//         objectMetadata.setContentDisposition("inline;filename=" + file.getName());
        //上传文件
        ossClient.putObject(bucketName, remotePath, inputStream, objectMetadata);
//         System.out.println(ossConfigure.getAccessUrl()+"/" +remoteFilePath + file.getName());
//         return ossConfigure.getAccessUrl()+"/" +remoteFilePath + file.getName();
        ossClient.shutdown();
    }

    /**
     * 上传OSS服务器文件
     *
     * @param @param  ossConfigure
     * @param @param  file
     * @param @param  remotePath
     * @param @return
     * @param @throws Exception    设定文件
     * @return String    返回类型
     * @throws
     * @Title: uploadFile
     * @Description:
     */
    public static void uploadFile(File file, String remotePath) throws Exception {
        InputStream fileContent = null;
        fileContent = new FileInputStream(file);

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//         String remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\","/")+"/";
        //创建上传Object的Metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileContent.available());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(contentType(file.getName().substring(file.getName().lastIndexOf(".") + 1)));
//        objectMetadata.setContentDisposition("inline;filename=" + file.getName());
        //上传文件
        ossClient.putObject(bucketName, remotePath, fileContent, objectMetadata);
//        System.out.println(ossConfigure.getAccessUrl()+"/" +remoteFilePath + file.getName());
//        return ossConfigure.getAccessUrl()+"/" +remoteFilePath + file.getName();
        ossClient.shutdown();
    }

    public static void uploadFile(MultipartFile file, String remotePath) throws Exception {
        InputStream fileContent = file.getInputStream();

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//         String remoteFilePath = remotePath.substring(0, remotePath.length()).replaceAll("\\\\","/")+"/";
        //创建上传Object的Metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(fileContent.available());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType(file.getContentType());
//        objectMetadata.setContentDisposition("inline;filename=" + file.getName());
        //上传文件
        ossClient.putObject(bucketName, remotePath, fileContent, objectMetadata);
//        System.out.println(ossConfigure.getAccessUrl()+"/" +remoteFilePath + file.getName());
//        return ossConfigure.getAccessUrl()+"/" +remoteFilePath + file.getName();
        ossClient.shutdown();
    }


    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param @param ossConfigure
     * @param @param filePath    设定文件
     * @return void    返回类型
     * @throws
     * @Title: deleteFile
     * @Description:
     */
    public static void deleteFile(String filePath) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.deleteObject(bucketName, filePath);

    }


    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     *
     * @param FilenameExtension 文件后缀
     * @return String
     * @Version1.0
     */
    public static String contentType(String FilenameExtension) {
        if (FilenameExtension.equals("BMP") || FilenameExtension.equals("bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equals("GIF") || FilenameExtension.equals("gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equals("JPEG") || FilenameExtension.equals("jpeg") ||
                FilenameExtension.equals("JPG") || FilenameExtension.equals("jpg") ||
                FilenameExtension.equals("PNG") || FilenameExtension.equals("png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equals("HTML") || FilenameExtension.equals("html")) {
            return "text/html";
        }
        if (FilenameExtension.equals("TXT") || FilenameExtension.equals("txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equals("VSD") || FilenameExtension.equals("vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equals("PPTX") || FilenameExtension.equals("pptx") ||
                FilenameExtension.equals("PPT") || FilenameExtension.equals("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equals("DOCX") || FilenameExtension.equals("docx") ||
                FilenameExtension.equals("DOC") || FilenameExtension.equals("doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equals("XML") || FilenameExtension.equals("xml")) {
            return "text/xml";
        }
        return "";
    }
}