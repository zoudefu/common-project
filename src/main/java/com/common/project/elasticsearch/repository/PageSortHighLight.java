package com.common.project.elasticsearch.repository;

/**
 * program: esdemo
 * description: 分页+排序+高亮对象封装
 * author: X-Pacific zhang
 * create: 2019-01-21 17:09
 **/
public class PageSortHighLight {
    private int currentPage = 1;
    private int pageSize = 10;
    /**
     * 查询的起始位置，默认值为0，相当于mysql的limit的第一个参数
     */
    private Integer startPosition = 0;

    Sort sort = new Sort();
    private HighLight highLight = new HighLight();

    public PageSortHighLight(int startPosition, int pageSize) {
        this.startPosition = startPosition;
        this.pageSize = pageSize;
    }

    public PageSortHighLight(int currentPage, int pageSize, Sort sort) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public HighLight getHighLight() {
        return highLight;
    }

    public void setHighLight(HighLight highLight) {
        this.highLight = highLight;
    }

    public Integer getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Integer startPosition) {
        this.startPosition = startPosition;
    }

}
