package com.Blog.Entity;

import java.io.Serializable;
import java.util.Date;

public class Blog implements Serializable{
    private Integer id;
    private String title;
    private String summary;
    private Date releasedate;
    private Integer clickhie;
    private Integer replyhit;
    private String context;
    private BlogType blogType;//博客类别
    private String keyword;
    private Integer blogcount;//博客数量 非属性字段
    private String releasedateString;//发布日期字符串
    private String contentNoTag;//没有HTML标签的文本
    private Integer bloggerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Integer getClickhie() {
        return clickhie;
    }

    public void setClickhie(Integer clickhie) {
        this.clickhie = clickhie;
    }

    public Integer getReplyhit() {
        return replyhit;
    }

    public void setReplyhit(Integer replyhit) {
        this.replyhit = replyhit;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public Integer getBlogcount() {
        return blogcount;
    }

    public void setBlogcount(Integer blogcount) {
        this.blogcount = blogcount;
    }

    public String getReleasedateString() {
        return releasedateString;
    }

    public void setReleasedateString(String releasedateString) {
        this.releasedateString = releasedateString;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releasedate=" + releasedate +
                ", clickhie=" + clickhie +
                ", replyhit=" + replyhit +
                ", context='" + context + '\'' +
                ", blogType=" + blogType +
                ", keyword='" + keyword + '\'' +
                ", blogcount=" + blogcount +
                ", releasedateString='" + releasedateString + '\'' +
                '}';
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public Integer getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Integer bloggerId) {
        this.bloggerId = bloggerId;
    }
}
