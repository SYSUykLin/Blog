package com.Blog.Dao;

import com.Blog.Entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 评论CRUD操作
 */
@Repository
public interface CommentDao {

    /**
     * 得到用户评论信息
     * @param comment
     * @return
     */
      public List<Comment> list(Comment comment);

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public Integer add(Comment comment);

    /**
     * 按照id删除
     * @param id
     * @return
     */
    public Integer delete(Integer id);

    /**
     * 用于分页查找
     * @param map
     * @return
     */
    public List<Comment> getListByPage(Map<String,Object> map);

    /**
     * 用于动态查找数量
     * @param map
     * @return
     */
    public Integer getTotal(Map<String,Object> map);

    /**
     * 审核已经被通过
     * @param comment
     * @return
     */
    public Integer updateComment(Comment comment);

    /**
     * 根据id查找
     * @param comment
     * @return
     */
    public Integer deleteById(Comment comment);

}
