package com.mao.infocrawler.service;

import com.alibaba.fastjson.JSON;
import com.mao.infocrawler.model.entity.Item;
import com.mao.infocrawler.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mao on 2016/5/1.
 */

@Service
public class AppService {

    private RedisUtil redisUtil = new RedisUtil();

    @Autowired
    private ItemService itemService;

    public List<Item> findAll(){

        List<Item> itemList = new ArrayList<Item>();
        for (int i = 0; i < 3; i++) {
            Item item = new Item();
            item.setId(i);
            itemList.add(item);
        }

        return itemList;
    }

    public void redis2DB() {

        Iterator it = redisUtil.keysSet();
        while (it.hasNext()) {
            String key = (String) it.next();
            String json = redisUtil.getStringVal(key);
            Item item = JSON.parseObject(json, Item.class);
            itemService.create(item);
        }
        System.out.println("redis2DB finished.");
        redisUtil.flushDB();
    }

}
