package com.cl.gzshop.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 首页展示时的数据模型
 * @Author l
 * @Date 2021/6/28 15:56
 */
public class CatResult implements Serializable {
    private List<?> data;

    public CatResult(){

    }

    public CatResult(List<?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CatResult{" +
                "data=" + data +
                '}';
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
