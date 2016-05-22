package com.mao.infocrawler.model.dao;

import com.mao.infocrawler.model.Page;
import com.mao.infocrawler.model.dao.common.IOperations;
import com.mao.infocrawler.model.entity.Item;

import java.util.List;

/**
 * Created by mao on 2016/5/1.
 */
public interface ItemDao extends IOperations<Item> {

    //让所有的DAO都实现基本的操作接口IOperations
    //除了实现IOperations中的基本操作之外，特定的DAO要实现其他操作可以在对应的接口DAO中定义方法

    void createUnique(Item item);

    List<Item> query(final String sql);

    List<String> queryField(final String sql);

    //    List<Item> queryByPage(final String title, Page page);
    List<Item> queryByPage(final String sql, Page page);

}
