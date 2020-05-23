package com.trafficLab.modules.jingjia.utils;

import java.io.*;

/**
 * author jinsq
 *
 * @date 2019/4/20 12:05
 */
public class JSFileUtils {
    /**
     * 修改js文件
     * @param file
     * @param productId 更新的产品ID
     * @param wechat 更新的微信号
     */
    public static void rwJS(File newFile,File file,String productId,String wechat) throws Exception{
        InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),"utf-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        StringBuilder sb = new StringBuilder();
        while((lineTxt = bufferedReader.readLine()) != null){
            if(lineTxt.contains("oldValue")){
                lineTxt = lineTxt.replace("oldValue",productId);
            }
            if(lineTxt.contains("wechat")){
                lineTxt = lineTxt.replace("wechat",wechat);
            }
            sb.append(lineTxt);
        }
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write(sb.toString());
        fileWriter.close();
        read.close();
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
