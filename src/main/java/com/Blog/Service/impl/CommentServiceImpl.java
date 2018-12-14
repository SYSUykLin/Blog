package com.Blog.Service.impl;

import com.Blog.Dao.CommentDao;
import com.Blog.Entity.Comment;
import com.Blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    /**
     * 得到用户评论信息
     *
     * @param comment
     * @return
     */
    public List<Comment> list(Comment comment) {
        return commentDao.list(comment);
    }

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    public Integer add(Comment comment) {
        return commentDao.add(comment);
    }

    /**
     * 按照id删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(Integer id) {
        return commentDao.delete(id);
    }

    /**
     * 用于分页查找
     *
     * @param map
     * @return
     */
    @Override
    public List<Comment> getListByPage(Map<String, Object> map) {
        return commentDao.getListByPage(map);
    }

    /**
     * 用于动态查找数量
     *
     * @param map
     * @return
     */
    @Override
    public Integer getTotal(Map<String, Object> map) {
        return commentDao.getTotal(map);
    }

    /**
     * 审核已经被通过
     *
     * @param comment
     * @return
     */
    @Override
    public Integer updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    /**
     * 根据id删除
     *
     * @param comment
     * @return
     */
    @Override
    public Integer deleteById(Comment comment) {
        return commentDao.deleteById(comment);
    }

}
