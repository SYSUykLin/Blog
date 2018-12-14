package com.Blog.Entity;

public class Link {
    private Integer id;
    private String linkname;
    private String linkurl;

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", linkname='" + linkname + '\'' +
                ", linkurl='" + linkurl + '\'' +
                '}';
    }
}
