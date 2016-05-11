package com.mao.infocrawler.utils;

import com.csvreader.CsvWriter;
import com.mao.infocrawler.model.entity.Item;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mao on 2016/5/8.
 */
public class CsvUtil {

    public static final String[] ITEM_CSV_HEADER = {"id","title","content","url","resource","time"};

    /**
     * 导出csv文件
     * records每一个String为一行记录，
     * 其中每个字段中间用'###'隔开
     * @param headers
     * @param records
     */
    public static void writeCsv(String[] headers,List<String> records) {
        String csvFilePath = "C:\\test\\"+DateUtil.getYMDHMSSTime()+".csv";
        CsvWriter wr =new CsvWriter(csvFilePath,',', Charset.forName("GBK"));
        try {
            wr.writeRecord(headers);
            String[] row;
            for (String record : records) {
                //System.out.println("*******************************"+record);
                row = record.split("###");
                wr.writeRecord(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            wr.close();
        }

    }

    /**
     * 将Item对象的List转化为writeCsv可以接受的参数
     * @param itemList
     * @return
     */
    public static List<String> ItemList2StrList(List<Item> itemList) {

        List<String> itemStrList = new ArrayList<String>();
        for (Item item : itemList) {
            String s = item.getId()+"###"
                    +item.getTitle()+"###"
                    +item.getContent()+"###"
                    +item.getUrl()+"###"+
                    item.getResource()+"###"
                    +item.getTime();
            itemStrList.add(s);
        }

        return itemStrList;
    }

    public static void main(String[] args) {
        String[] headers = {"id","title","content","url","resource","time"};
        Item item = new Item();
        item.setId(1);
        item.setTitle("24r4trrwere");
        item.setContent("adsfgglkjlkjfdoiuehfdskjfdskjewufdhjfdsjbvkjnjgngfnkjgfgkjgfnjnjgfjgfnjnjgfkjgfnjrnjfdjgfgfgfgfjffkj" +
                "gfgffhjgfgfjgfkjgfkjgffdkjnmbvcnkjfkjkgklfdgjklfjglkfgfdgf");
        item.setResource("gfdfdgfdfdsf");
        item.setUrl("erfgfdgffdg");
        item.setTime("gfdgfgfgfdgfg");

        List<String> list = new ArrayList<String>();
        String s = item.getId()+"###"+item.getTitle()+"###"+item.getContent()+"###"+item.getUrl()+"###"+item.getResource()+"###"+item.getTime();
        list.add(s);
        writeCsv(headers, list);
    }
}
