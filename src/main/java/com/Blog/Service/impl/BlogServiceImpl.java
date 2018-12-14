package com.Blog.Service.impl;

import com.Blog.Dao.BlogDao;
import com.Blog.Entity.Blog;
import com.Blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    /**
     * 按照日期分组查询
     *
     * @return
     */
    public List<Blog> countList() {
        return blogDao.countList();
    }

    /**
     * 分页获得博客
     *
     * @param map
     * @return
     */
    public List<Blog> list(Map<String, Object> map) {
        return blogDao.list(map);
    }

    /**
     * 获得博客数量
     *
     * @param map
     * @return
     */
    public Integer getTotal(Map<String, Object> map) {
       return blogDao.getTotal(map);
    }

    /**
     * 通过id获取博客
     *
     * @param id
     * @return
     */
    public Blog getBlogById(Integer id) {
        return blogDao.getBlogById(id);
    }

    /**
     * 更新博客
     *
     * @param blog
     * @return
     */
    public Integer update(Blog blog) {
        return blogDao.update(blog);
    }

    /**
     * 得到上一篇博客
     *
     * @param id
     * @return
     */
    public Blog getLastBlog(Integer id) {
        return blogDao.getLastBlog(id);
    }

    /**
     * 得到下一篇博客
     *
     * @param id
     * @return
     */
    public Blog getNextBlog(Integer id) {
        return blogDao.getNextBlog(id);
    }

    /**
     * 添加博客
     *
     * @param blog
     * @return
     */
    @Override
    public Integer add(Blog blog) {
        return blogDao.add(blog);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(Integer id) {
        return blogDao.delete(id);
    }
}
