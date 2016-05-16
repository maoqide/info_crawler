package com.mao.infocrawler.service;

import com.mao.infocrawler.model.Page;
import com.mao.infocrawler.model.dao.common.IOperations;
import com.mao.infocrawler.model.entity.Item;

import java.util.List;

/**
 * Created by mao on 2016/5/1.
 */
public interface ItemService extends IOperations<Item> {

    /**
     * 列出全部，带分页
     *
     * @param page
     * @return
     */
    Page listAll(Page page);

//    /**
//     * 通过关键字模糊查询，带分页
//     * @param keyword
//     * @param page
//     * @return
//     */
//    Page queryByKeyword(String keyword, Page page);
//
//    /**
//     * 通过爬取时间筛选，带分页
//     * @param time
//     * @param page
//     * @return
//     */
//    Page filterByTime(String time, Page page);

    /**
     * 通过关键字模糊查询，不带分页（导出csv）
     *
     * @param keyword
     * @return
     */
    List<Item> queryNoPage(String keyword, String time, String resource, String queryWhich);

    /**
     * 得到所有'field'字段，无重复
     *
     * @return
     */
    List<String> queryField(String field);

    /**
     *
     * @param keyword
     * @param time
     * @param resource
     * @param queryWhich
     * @param page
     * @return
     */
    Page query(String keyword, String time, String resource, String queryWhich, Page page);

    /**
     *
     * @param item
     */
    void createUnique(Item item);


}
