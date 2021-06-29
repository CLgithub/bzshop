package com.cl.gzshop.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 首页展示时的数据模型
 * @Author l
 * @Date 2021/6/28 15:42
 */
public class CatNode implements Serializable {

    @JsonProperty("n") // 该注解可以让对象转json时按指定名称转
    private String name;
    @JsonProperty("i")
    private List<CatNode> item;

    public CatNode(){
    }

    public CatNode(String name, List<CatNode> item) {
        this.name = name;
        this.item = item;
    }

    @Override
    public String toString() {
        return "CatNode{" +
                "name='" + name + '\'' +
                ", item=" + item +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CatNode> getItem() {
        return item;
    }

    public void setItem(List<CatNode> item) {
        this.item = item;
    }
}
