package com.cl.gzshop.utils;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 购物车商品模型
 * @Author l
 * @Date 2021/8/19 22:57
 */
public class CartItem implements Serializable {
    private Long id;
    private String title;
    private String sellPoint;
    private String image;
    private int num;
    private Long price;

    public CartItem(){
    }

    public CartItem(Long id, String title, String sellPoint, String image, int num, Long price) {
        this.id = id;
        this.title = title;
        this.sellPoint = sellPoint;
        this.image = image;
        this.num = num;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", image='" + image + '\'' +
                ", num=" + num +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }



}
