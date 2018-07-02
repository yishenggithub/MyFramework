package xyz.yisheng.blog.model;

import priv.framework.jdbc.Annotation.Table;

/**
 * Created by easom on 2017/8/14.
 * 没有控制类了
 */
@Table(pk="bid")
public class Article {

    private String content;
    private String title;
    private Integer bid;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title=title;}

}