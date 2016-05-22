package com.mao.infocrawler.service;

import com.alibaba.fastjson.JSON;
import com.mao.infocrawler.crawler.CrawlerController;
import com.mao.infocrawler.model.entity.Item;
import com.mao.infocrawler.utils.RedisUtil;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by mao on 2016/5/1.
 */

@Service
public class AppService {

    @Autowired
    private ItemService itemService;

    private RedisUtil redisUtil = new RedisUtil();

    private static CrawlerController crawlerController = new CrawlerController();

    public void startCrawler() {

        try {
            crawlerController.neteaseCrawlerStart();
            crawlerController.sohuCrawlerStart();
        } catch (Exception e) {
            System.out.println("###############" + "crawler start failed...");
            e.printStackTrace();
        }
    }

    /**
     * 得到redis中数据的总数
     *
     * @return
     */
    public int total() {
        return redisUtil.totalSize();
    }

    /**
     * 将redis中的数据存入mysql
     */
    public void redis2DB() {

        Iterator it = redisUtil.keysSet();
        while (it.hasNext()) {
            String key = (String) it.next();
            String json = redisUtil.getStringVal(key);
            Item item = JSON.parseObject(json, Item.class);
            itemService.createUnique(item);
        }
        System.out.println("redis2DB finished.");
        redisUtil.flushDB();
    }
}
