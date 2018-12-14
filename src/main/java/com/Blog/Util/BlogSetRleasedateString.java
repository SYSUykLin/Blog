package com.Blog.Util;

import com.Blog.Entity.Blog;

import java.util.List;

public class BlogSetRleasedateString {
    public static void addReleasedateString(List<Blog> blogs) {
        for (Blog blog : blogs) {
            blog.setReleasedateString(blog.getReleasedate().toLocaleString());
        }
    }
}
