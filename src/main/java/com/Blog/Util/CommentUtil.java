package com.Blog.Util;

import com.Blog.Entity.Comment;

import java.util.List;

public class CommentUtil {
    public static void setDateString(List<Comment> comments) {
        for (Comment comment : comments
                ) {
            setDateString(comment);
        }
    }

    public static void setDateString(Comment comment) {
        comment.setDateString(comment.getCommentDate().toLocaleString());
    }
}
