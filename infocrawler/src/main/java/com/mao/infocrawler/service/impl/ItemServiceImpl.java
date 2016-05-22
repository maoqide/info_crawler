package com.mao.infocrawler.service.impl;

import com.mao.infocrawler.model.Page;
import com.mao.infocrawler.model.dao.ItemDao;
import com.mao.infocrawler.model.dao.common.IOperations;
import com.mao.infocrawler.model.entity.Item;
import com.mao.infocrawler.service.ItemService;
import com.mao.infocrawler.service.common.AbstractService;
import com.mao.infocrawler.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mao on 2016/5/1.
 */

@Service("itemService")
public class ItemServiceImpl extends AbstractService<Item> implements ItemService {

    @Resource(name = "itemDao")
    private ItemDao dao;

    private static final String QUERY_TITLE = "title";

    private static final String QUERY_CONTENT = "content";

    public ItemServiceImpl() {
        super();
    }

    @Override
    protected IOperations<Item> getDao() {
        return this.dao;
    }

    @Override
    public Page listAll(Page page) {
        String sql = "SELECT * FROM item";
        page.setContent(dao.queryByPage(sql, page));
        return page;
    }

//    @Override
//    public Page queryByKeyword(String keyword, Page page) {
//        String sql = "SELECT * FROM item WHERE title LIKE '%"+keyword+"%'";
//        page.setContent(dao.queryByPage(sql, page));
//        return page;
//    }
//
//    @Override
//    public Page filterByTime(String time, Page page) {
//        String sql = "SELECT * FROM item WHRER time = " + time;
//        page.setContent(dao.queryByPage(sql, page));
//        return page;
//    }

    @Override
    public List<Item> queryNoPage(String keyword, String time, String resource, String content) {

        if (!StringUtil.validateString(keyword) && !StringUtil.validateString(time) && !StringUtil.validateString(resource)) {
            return findAll();
        }

        String sql = generateQuerySQL(keyword, time, resource, content);
        return dao.query(sql);
    }

    @Override
    public List<String> queryField(String field) {
        String sql = "SELECT DISTINCT " + field + " FROM item";
        return dao.queryField(sql);
    }

    @Override
    public Page query(String keyword, String time, String resource, String content, Page page) {

        if (!StringUtil.validateString(keyword) && !StringUtil.validateString(time) && !StringUtil.validateString(resource)) {
            return listAll(page);
        }

        String sql = generateQuerySQL(keyword, time, resource, content);
        page.setContent(dao.queryByPage(sql, page));
        return page;
    }

    @Override
    public void createUnique(Item item) {
        dao.createUnique(item);
    }

    /**
     * 拼接查询方法的sql语句
     *
     * @param keyword
     * @param time
     * @param resource
     * @return
     */
    private String generateQuerySQL(String keyword, String time, String resource, String queryWhich) {

        StringBuffer sql = new StringBuffer("SELECT * FROM item WHERE ");
        String titleFilterSQL = "title LIKE '%" + keyword + "%'";
        String timeFilterSQL = "time = '" + time + "'";
        String resourceFilterSQL = "resource = '" + resource + "'";
        String contentFilterSQL = "content LIKE '%" + keyword + "%'";

        //keyword不为空
        if (StringUtil.validateString(keyword)) {
            if (queryWhich.equals(QUERY_CONTENT)) { //全文查询
                sql.append(contentFilterSQL);
            } else {    //标题查询
                sql.append(titleFilterSQL);
            }
            if (StringUtil.validateString(time))
                sql.append(" AND ").append(timeFilterSQL);
            if (StringUtil.validateString(resource))
                sql.append(" AND ").append(resourceFilterSQL);

        } else {    //keyword为空
            if (StringUtil.validateString(time) && StringUtil.validateString(resource)) {   //time和resource都不为空
                sql.append(timeFilterSQL).append(" AND ").append(resourceFilterSQL);
            } else {
                if (StringUtil.validateString(time)) {
                    sql.append(timeFilterSQL);
                } else {
                    sql.append(resourceFilterSQL);
                }
            }
        }
        return sql.toString();
    }

}
