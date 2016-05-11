package com.mao.infocrawler.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mao on 2016/3/12.
 */

@Entity(name = "item")
public class Item implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    //标题
    @Column
    private String title;

    //正文
    @Column
    private String content;

    //url
    @Column
    private String url;

    //来源
    @Column
    private String resource;

    //时间 h
    @Column
    private String time;

    public String toString() {
        return "Item [id=" + id + ", title="+title +"]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
