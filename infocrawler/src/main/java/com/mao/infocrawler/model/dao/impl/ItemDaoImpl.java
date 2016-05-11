package com.mao.infocrawler.model.dao.impl;

import com.mao.infocrawler.model.Page;
import com.mao.infocrawler.model.dao.ItemDao;
import com.mao.infocrawler.model.dao.common.AbstractHibernateDao;
import com.mao.infocrawler.model.entity.Item;
import com.mao.infocrawler.utils.PageUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mao on 2016/5/1.
 */

@Repository("itemDao")
public class ItemDaoImpl extends AbstractHibernateDao<Item> implements ItemDao {

    public ItemDaoImpl() {
        super();
        setClazz(Item.class);
    }

    /**
     * for query
     *
     * @param sql
     * @return
     */
    @Override
    public List<Item> query(final String sql) {
        return getCurrentSession().createSQLQuery(sql).addEntity(Item.class).list();
    }

    /**
     * @param sql
     * @param page
     * @return
     */
    @Override
    public List<Item> queryByPage(final String sql, Page page) {
        Query query = getCurrentSession().createSQLQuery(sql).addEntity(Item.class);

        //得到总结果数，设置page
        page.setTotalCount(query.list().size());
        page.setTotalPage(PageUtil.getTotalPage(page.getEveryPage(), page.getTotalCount()));

        //设置每页显示多少个，设置多大结果。
        query.setMaxResults(page.getEveryPage());
        //设置起点
        query.setFirstResult((page.getCurrentPage() - 1) * page.getEveryPage());
        return query.list();
    }

    @Override
    public List<String> queryField(final String sql) {
        return getCurrentSession().createSQLQuery(sql).list();
    }

}
