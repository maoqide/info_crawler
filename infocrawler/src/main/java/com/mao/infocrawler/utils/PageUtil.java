package com.mao.infocrawler.utils;

import com.mao.infocrawler.model.Page;

/**
 * Created by mao on 2016/5/4.
 */
public class PageUtil {

    public static final int TEN_EVERYPAGE = 10;

    public static final int TWENTY_EVERYPAGE = 20;

    /**
     *
     * @param everyPage
     * @param currentPage
     * @return
     */
    public static Page initPage(int everyPage,int currentPage) {
        everyPage = getEveryPage(everyPage);
        currentPage = getCurrentPage(currentPage);
        return new Page(everyPage, 0, 0, currentPage);
    }

    //设置每页显示记录数
    public static int getEveryPage(int everyPage) {
        return everyPage == 0 ? 10 : everyPage;
    }

    //设置当前页
    public static int getCurrentPage(int currentPage) {
        return currentPage == 0 ? 1 : currentPage;
    }

    //设置总页数,需要总记录数，每页显示多少
    public static int getTotalPage(int everyPage,int totalCount) {
        int totalPage = 0;
        if(totalCount % everyPage == 0) {
            totalPage = totalCount / everyPage;
        } else {
            totalPage = totalCount / everyPage + 1;
        }
        return totalPage;
    }

    //设置当前页
    public static Page validateCurPage(int curPage, Page page) {

        if (curPage < page.getTotalPage() && curPage > 0) {
            page.setCurrentPage(curPage);
        } else if (curPage < 1 || page.getTotalPage() == 0){
            page.setCurrentPage(1);
        } else {
            page.setCurrentPage(page.getTotalPage());
        }
        return page;
    }

}
