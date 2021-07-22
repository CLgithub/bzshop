package com.cl.pojo;

import java.io.Serializable;

/**
 *  solr实体
 * @Author l
 * @Date 2021/7/8 22:49
 */
public class SolrItem implements Serializable{
    private Long id;
    private String title;
    private String sell_point;
    private Long price;
    private String image;
    private String name;
    private String item_desc;

    public SolrItem(){}

    public SolrItem(Long id, String title, String sell_point, Long price, String image, String name, String item_desc) {
        this.id = id;
        this.title = title;
        this.sell_point = sell_point;
        this.price = price;
        this.image = image;
        this.name = name;
        this.item_desc = item_desc;
    }

    @Override
    public String toString() {
        return "SolrItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sell_point='" + sell_point + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", item_desc='" + item_desc + '\'' +
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

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }
}
