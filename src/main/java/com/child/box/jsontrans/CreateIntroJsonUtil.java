package com.child.box.jsontrans;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateIntroJsonUtil {

    private final static String REMOTE_URL = "https://weixiaocourseware.oss-cn-hangzhou.aliyuncs.com/resource/";

    private final static String FILENAME_OLD = "zhongban2";

    private final static String FILENAME_NEW = "zhongban2_new";

    private final static String FILENAME_ZT = "zhongban2_zt";

    private final static String FILENAME_MK = "zhongban2_mk";

    public static void main(String[] args) {
        testJSONStrToJavaBeanList();
    }

    public static void testJSONStrToJavaBeanList() {
        JSONArray jsonArray = JSONArray.parseArray(getDataFromFile(FILENAME_OLD));
        //遍历JSONArray
        List<KejianHome> homes = new ArrayList<>();
        List<ZtIntro> ztIntros = new ArrayList<>();
        List<MkIntro> mkIntros = new ArrayList<>();
        for (Object object : jsonArray) {
            ZtIntro ztIntro = new ZtIntro();
            KejianHome kejianHome;
            String kejianId = "";

            JSONObject jsonObjectone = (JSONObject) object;
            String zt = jsonObjectone.getString("zt");
            JSONArray list = jsonObjectone.getJSONArray("list");
            List<KejianHomeBk> details = new ArrayList<>();
            String gradeName = null;
            if ("数学".equals(zt) || "游戏".equals(zt)) {
                for (int i = 1; i <= list.size(); i++) {
                    KejianHomeBk kejianHomeBk = new KejianHomeBk();
                    JSONObject detail = list.getJSONObject(i - 1);
                    String title = detail.getString("title");
                    JSONArray kejianList = detail.getJSONArray("mkname");
                    kejianId = kejianList.getJSONObject(0).getString("id");//课件id
                    kejianHomeBk.setTitle(title);
                    kejianHomeBk.setMkname(kejianList);
                    kejianHomeBk.setId(kejianId.substring(0, 4));
                    details.add(kejianHomeBk);

                    gradeName = getGrade(String.valueOf(kejianId.charAt(1)));

                    if ("游戏".equals(zt)) {
                        MkIntro mkIntro = new MkIntro();
                        mkIntro.setId(kejianId.substring(0, 4));
                        mkIntro.setName(title);
                        mkIntro.setUrl(REMOTE_URL + gradeName + "/" + zt + "/" + "gs" + kejianId.charAt(1) + "-" + i+".webp");
                        mkIntro.setZipurl("");
                        mkIntros.add(mkIntro);
                    }
                }

                kejianHome = new KejianHome(kejianId.substring(0, 3), zt);
                kejianHome.setList(details);
                homes.add(kejianHome);


                if ("数学".equals(zt)) {
                    ztIntro.setId(kejianId.substring(0, 3));
                    ztIntro.setName(zt);

                    ztIntro.setZipurl(REMOTE_URL + gradeName + "/" + zt + "/" + "教学一览表：" + zt+".zip");

                    List<ZtIntro.ZtIntroDetail> ztIntroDetails = new ArrayList<>();
                    for (int j = 1; j < 5; j++) {
                        ZtIntro.ZtIntroDetail ztIntroDetail =
                                new ZtIntro.ZtIntroDetail(getSxZtIntroName(String.valueOf(j)), REMOTE_URL + gradeName + "/" + zt + "/" + "sx" + kejianId.charAt(1) + "-" + j+".webp");
                        ztIntroDetails.add(ztIntroDetail);
                    }
                    ztIntro.setRes(ztIntroDetails);
                    ztIntros.add(ztIntro);
                }
            } else {

                for (int i = 1; i <= list.size(); i++) {
                    KejianHomeBk kejianHomeBk = new KejianHomeBk();
                    JSONObject detail = list.getJSONObject(i - 1);
                    String title = detail.getString("title");

                    JSONArray kejianList = detail.getJSONArray("mkname");
                    kejianId = kejianList.getJSONObject(0).getString("id");//课件id
                    kejianHomeBk.setTitle(title);
                    kejianHomeBk.setMkname(kejianList);
                    kejianHomeBk.setId(kejianId.substring(0, 4));
                    details.add(kejianHomeBk);

                    gradeName = getGrade(String.valueOf(kejianId.charAt(1)));

                    if("规则游戏".equals(title.substring(5)))continue;
                    MkIntro mkIntro = new MkIntro();
                    mkIntro.setId(kejianId.substring(0, 4));
                    mkIntro.setName(title.substring(5));
                    mkIntro.setUrl(REMOTE_URL + gradeName + "/" + zt.substring(5) + "/" + "list-" + kejianId.charAt(1) + "-" + kejianId.charAt(2) + "-" + i+".webp");
                    mkIntro.setZipurl(REMOTE_URL + gradeName + "/" + zt.substring(5) + "/" + "教学一览表：" + title.substring(5)+".zip");
                    mkIntros.add(mkIntro);
                }

                kejianHome = new KejianHome(kejianId.substring(0, 3), zt);
                kejianHome.setList(details);
                homes.add(kejianHome);

                ztIntro.setId(kejianId.substring(0, 3));
                ztIntro.setName(zt.substring(5));

                ztIntro.setZipurl(REMOTE_URL + gradeName + "/" + zt.substring(5) + "/" + zt.substring(5) + "_" + "给家长的建议"+".zip");

                List<ZtIntro.ZtIntroDetail> ztIntroDetails = new ArrayList<>();
                for (int i = 1; i < 7; i++) {
                    ZtIntro.ZtIntroDetail ztIntroDetail =
                            new ZtIntro.ZtIntroDetail(getZtIntroName(String.valueOf(i)), REMOTE_URL + gradeName + "/" + zt.substring(5) + "/" + "zt1-" + kejianId.charAt(1) + "-" + kejianId.charAt(2) + "-" + i+".webp");
                    ztIntroDetails.add(ztIntroDetail);
                }
                ztIntro.setRes(ztIntroDetails);
                ztIntros.add(ztIntro);
            }
        }
        testJavaBeanListToJSONStr(homes);
        testJavaBeanListToJSONStr2(ztIntros);
        testJavaBeanListToJSONStr3(mkIntros);
    }

    private static String getGrade(String num) {
        switch (num) {
            case "1":
                return "小班上";
            case "2":
                return "小班下";
            case "3":
                return "中班上";
            case "4":
                return "中班下";
            case "5":
                return "大班上";
            case "6":
                return "大班下";
        }
        return "";
    }

    private static String getZtIntroName(String num) {
        switch (num) {
            case "1":
                return "主题来源";
            case "2":
                return "主题目标";
            case "3":
                return "主题脉络";
            case "4":
                return "给老师的建议";
            case "5":
                return "环境创设";
            case "6":
                return "给家人的建议";
        }
        return "";
    }

    private static String getSxZtIntroName(String num) {
        switch (num) {
            case "1":
                return "整体说明";
            case "2":
                return "使用原则";
            case "3":
                return "使用说明";
            case "4":
                return "教学内容一览表";
        }
        return "";
    }

    /**
     * JavaBean_List到json字符串-数组类型的转换
     */
    public static void testJavaBeanListToJSONStr(List<KejianHome> kejianHomes) {
        String jsonString = JSONArray.toJSONString(kejianHomes);
        saveDataToFile(FILENAME_NEW, jsonString);
    }

    /**
     * JavaBean_List到json字符串-数组类型的转换
     */
    public static void testJavaBeanListToJSONStr2(List<ZtIntro> ztIntros) {
        String jsonString = JSONArray.toJSONString(ztIntros);
        saveDataToFile(FILENAME_ZT, jsonString);
    }

    /**
     * JavaBean_List到json字符串-数组类型的转换
     */
    public static void testJavaBeanListToJSONStr3(List<MkIntro> mkIntros) {
        String jsonString = JSONArray.toJSONString(mkIntros);
        saveDataToFile(FILENAME_MK, jsonString);
    }

    /**
     * 获取本地数据
     */
    private static String getDataFromFile(String fileName) {

        String Path = "Z:\\tranjson\\" + fileName + ".json";
        BufferedReader reader = null;
        String lastStr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                lastStr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lastStr;
    }

    /**
     * 保存数据到文件
     */
    private static void saveDataToFile(String fileName, String data) {
        BufferedWriter writer = null;
        File file = new File("Z:\\tranjson\\" + fileName + ".json");
        //如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件写入成功！");
    }
}
