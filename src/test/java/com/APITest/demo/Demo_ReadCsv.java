package com.APITest.demo;

import org.jumpmind.symmetric.csv.CsvReader;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @Author: Aikachin
 * @Description: 读取CSV文件内容并打印出来
 * @Date: Created in 10:04 2017/11/30 0030.
 * @Modified by :
 */
public class Demo_ReadCsv {
    public static void main(String[] args) {
        ArrayList<String[]> list = readCsv("./userinfo.csv");
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                // System.out.println(list.get(i).length);
                System.out.println(list.get(i)[j]);   // name
                // System.out.println(list.get(i)[1]);   // name
                // System.out.println(list.get(i)[2]);   // name
            }
        }
    }






    public static ArrayList<String[]> readCsv(String filePath) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        CsvReader csvReader = null;
        try {
            csvReader = new CsvReader(filePath, ',', Charset.forName("GBK"));
            // 是否跳过表头
            csvReader.readHeaders();
            // 录入数据
            while (csvReader.readRecord()) {
                list.add(csvReader.getValues());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            csvReader.close();
        }
        // 如果使用testNG的DataProvider，可以返回一个二维数组
        Object data[][] = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            data[i] = list.get(i);
        }
        return list;
    }
}
