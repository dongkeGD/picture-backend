package com.demo.picturebackend.common;

import java.io.Serializable;

/**
 * ClassName:PageRequest
 * Package:com.demo.picturebackend.common
 * Description:
 *
 * @Author:Thesy
 * @Create:2025/6/17 - 19:48
 * @Version: v1.0
 */
public class PageRequest implements Serializable {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = "descend";

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    private static final long serialVersionUID = -2198953271240523523L;
}
