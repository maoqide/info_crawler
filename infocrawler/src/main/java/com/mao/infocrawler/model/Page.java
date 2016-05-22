package com.mao.infocrawler.model;

import java.util.List;

/**
 * Created by mao on 2016/5/4.
 */

public class Page {

    // 1.每页显示数量(everyPage)
    private int everyPage;
    // 2.总记录数(totalCount)
    private int totalCount;
    // 3.总页数(totalPage)
    private int totalPage;
    // 4.当前页(currentPage)
    private int currentPage;

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    // 5.当前页内容
    private List<?> content;

    /**
     * 是否有下一页
     *
     * @return
     */
    public boolean isHasNextPage() {
        return currentPage == totalPage || totalPage == 0 ? false : true;
    }

    /**
     * 是否有上一页
     *
     * @return
     */
    public boolean isHasPrePage() {
        return this.currentPage == 1 ? false : true;
    }

    public int getEveryPage() {
        return everyPage;
    }

    public void setEveryPage(int everyPage) {
        this.everyPage = everyPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Page(int everyPage, int totalCount, int totalPage, int currentPage) {
        this.everyPage = everyPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.content = null;
    }

    //构造函数，默认
    public Page() {
    }
}
