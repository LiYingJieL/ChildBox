package com.child.box.jsontrans;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonTranUtil {

    private  final static String FILENAME_OLD = "daban2_old";

    private  final static String FILENAME_NEW = "daban2_new";

    public static void main(String[] args) {
        testJSONStrToJavaBeanList();
    }

    public static void testJSONStrToJavaBeanList() {
        JSONArray jsonArray = JSONArray.parseArray(getDataFromFile(FILENAME_OLD));
        //遍历JSONArray
        List<Kejian> kejians = new ArrayList<>();
        Kejian kejian = null;
        for (Object object : jsonArray) {

            JSONObject jsonObjectone = (JSONObject) object;
            int id = jsonObjectone.getInteger("id");
            String name = jsonObjectone.getString("name");
            String intro = jsonObjectone.getString("intro");
            String intro2 = jsonObjectone.getString("intro2");
            String cover = jsonObjectone.getString("cover");
            JSONArray list = jsonObjectone.getJSONArray("list");
            List<KejianDetail> details = new ArrayList<>();
            for (Object objectDetail : list) {
                JSONObject detail = (JSONObject) objectDetail;
                String title = detail.getString("title");
                if (title.startsWith("教案")) {
                    KejianDetail kejianDetail = new KejianDetail();
                    kejianDetail.setTitle(detail.getString("title"));
                    kejianDetail.setCover(detail.getString("cover"));
                    kejianDetail.setUrl(detail.getString("url"));
                    kejianDetail.setType(detail.getInteger("type"));
                    details.add(kejianDetail);
                }
            }

            for (Object objectDetail : list) {
                JSONObject detail = (JSONObject) objectDetail;
                String title = detail.getString("title");
                int type = detail.getInteger("type");
                if (title.startsWith("教案")) {
                    continue;
                }

                KejianDetail kejianDetail = new KejianDetail();
                kejianDetail.setTitle(title);
                kejianDetail.setCover(detail.getString("cover"));
                kejianDetail.setUrl(detail.getString("url"));
                kejianDetail.setType(type);

                if (type == 2) {
                    if(!title.startsWith("课件")){
                        kejianDetail.setTitle("课件："+title);
                    }
                    details.add(1, kejianDetail);
                } else {
                    details.add(kejianDetail);
                }
            }

            kejian = new Kejian(id, name, intro, intro2, cover);
            kejian.setList(details);
            kejians.add(kejian);
        }
        testJavaBeanListToJSONStr(kejians);
    }

    /**
     * JavaBean_List到json字符串-数组类型的转换
     */
    public static void testJavaBeanListToJSONStr(List<Kejian> kejians) {
        String jsonString = JSONArray.toJSONString(kejians);
        saveDataToFile(FILENAME_NEW,jsonString);
    }

    /**
     * 获取本地数据
     */
    private static String getDataFromFile(String fileName) {

        String Path="D:\\tranjson\\" + fileName+ ".json";
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
    private static void saveDataToFile(String fileName,String data) {
        BufferedWriter writer = null;
        File file = new File("D:\\tranjson\\"+ fileName + ".json");
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("文件写入成功！");
    }
}
