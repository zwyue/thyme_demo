package com.zhu.thyme_demo.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GenerateWord {

    private Configuration configuration = null;

    public GenerateWord(){
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public static void main(String[] args) {
        GenerateWord test = new GenerateWord();
        test.createWord();
    }

    public String createWord(){
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getData(dataMap);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");//模板文件所在路径
        Template t=null;
        try {
            t = configuration.getTemplate("test.ftl"); //获取模板文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = "C:/zwy/outFile/"+ UUID.randomUUID() +".doc" ;
        File outFile = new File(filePath); //导出文件
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return null ;
        }

        try {
            t.process(dataMap, out); //将填充数据填入模板文件并输出到目标文件
        } catch (TemplateException e) {
            e.printStackTrace();
            return null ;
        } catch (IOException e) {
            e.printStackTrace();
            return null ;
        }
        return filePath ;
    }

    private void getData(Map<String, Object> dataMap) {
        dataMap.put("class", "班级：xx班级");
//        dataMap.put("nian", "2016");
//        dataMap.put("yue", "3");
//        dataMap.put("ri", "6");
//        dataMap.put("shenheren", "lc");

//        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//        for (int i = 0; i < 10; i++) {
//            Map<String,Object> map = new HashMap<String,Object>();
//            map.put("xuehao", i);
//            map.put("neirong", "内容"+i);
//            list.add(map);
//        }
//
//
//        dataMap.put("list", list);
    }
}