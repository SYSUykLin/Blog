package com.Blog.Entity;

import java.io.Serializable;

public class BlogType implements Serializable{
    private Integer id;
    private String typename;
    private Integer orderno;
    private Integer blogcount;

    public BlogType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getBlogcount() {
        return blogcount;
    }

    public void setBlogcount(Integer blogcount) {
        this.blogcount = blogcount;
    }
}
