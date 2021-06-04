package com.cl.gzshop.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型
 * @Author l
 * @Date 2020/9/21 20:27
 */
public class PageResult implements Serializable{
    private Integer pageIndex; //当前第几页
    private Long totalPage; //总页数
    private List result;    //结果集

    public PageResult(Integer pageIndex, Long totalPage, List result) {
        this.pageIndex = pageIndex;
        this.totalPage = totalPage;
        this.result = result;
    }

    public PageResult() {
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "pageIndex=" + pageIndex +
                ", totalPage=" + totalPage +
                ", result=" + result +
                '}';
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }
}
