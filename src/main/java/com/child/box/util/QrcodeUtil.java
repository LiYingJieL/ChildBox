package com.child.box.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具
 *
 * @author xxm
 * @date 2018-07-18
 **/
public class QrcodeUtil {

    private static final int QRCOLOR = 0xFF000000;   //默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF;   //背景颜色
    public static final String FORMAT = "jpg";

    /**
     * @param content 内容
     */
    public static String createQrcode(String content) {
        return createQrcode(content, null);
    }

    public static String createQrcode(String content, String logo) {
        String path = null;
        BufferedImage bim = getBufferedImage(content, BarcodeFormat.QR_CODE, 400, 400, getDecodeHintType());
        if (bim == null) {
            return path;
        }
        InputStream inputStream = null;
        if (!StringUtils.isEmpty(logo)) {
            inputStream = addLogo(bim, logo, new LogoConfig());
        } else {
            inputStream = getInputStream(bim);
        }

        if (inputStream == null) {
            return path;
        }
        try {
            return FileUtil.copyFile(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 生成二维码bufferedImage图片
     *
     * @param content       编码内容
     * @param barcodeFormat 编码类型
     * @param width         图片宽度
     * @param height        图片高度
     * @param hints         设置参数
     * @return
     */
    public static BufferedImage getBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BufferedImage image = null;
        try {
            BitMatrix bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 给二维码图片添加Logo
     *
     * @param bim
     * @param logoPic
     * @param logoConfig
     */
    public static InputStream addLogo(BufferedImage bim, String logoPic, LogoConfig logoConfig) {
        try {
            /**
             * 读取二维码图片，并构建绘图对象
             */
            BufferedImage image = bim;
            Graphics2D g = image.createGraphics();

            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(new URL(logoPic));
            /**
             * 设置logo的大小,本人设置为二维码图片的20%,因为过大会盖掉二维码
             */
            int widthLogo = logo.getWidth(null) > image.getWidth() * 3 / 10 ? (image.getWidth() * 3 / 10) : logo.getWidth(null),
                    heightLogo = logo.getHeight(null) > image.getHeight() * 3 / 10 ? (image.getHeight() * 3 / 10) : logo.getWidth(null);

            /**
             * logo放在中心
             */
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            /**
             * logo放在右下角
             *  int x = (image.getWidth() - widthLogo);
             *  int y = (image.getHeight() - heightLogo);
             */

            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
//            g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
//            g.setStroke(new BasicStroke(logoConfig.getBorder()));
//            g.setColor(logoConfig.getBorderColor());
//            g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();
            logo.flush();
            image.flush();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, FORMAT, baos);

            //二维码生成的路径，但是实际项目中，我们是把这生成的二维码显示到界面上的，因此下面的折行代码可以注释掉
            //可以看到这个方法最终返回的是这个二维码的imageBase64字符串
            //前端用 <img src="data:image/png;base64,${imageBase64QRCode}"/>  其中${imageBase64QRCode}对应二维码的imageBase64字符串
            //ImageIO.write(image, "png", new File("C:/Users/Public/Pictures/Sample Pictures/" + new Date().getTime() + "test.png"));
            //String imageBase64QRCode =  Base64.encodeBase64URLSafeString(baos.toByteArray());
            InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
            baos.close();
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream getInputStream(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.flush();
            ImageIO.write(image, FORMAT, baos);
            InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
            baos.close();
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 设置二维码的格式参数
     *
     * @return
     */
    public static Map<EncodeHintType, Object> getDecodeHintType() {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);

        return hints;
    }

}
