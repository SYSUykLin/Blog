package com.Blog.Service.impl;

import com.Blog.Dao.BlogTypeDao;
import com.Blog.Entity.BlogType;
import com.Blog.Service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlogTypeServiceImpl implements BlogTypeService {

    @Autowired
    private BlogTypeDao blogTypeDao;

    /**
     * 获得博客类型
     *
     * @return
     */
    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }

    /**
     * 分页参数
     *
     * @param map
     * @return
     */
    @Override
    public List<BlogType> list(Map<String, Object> map) {
        return blogTypeDao.list(map);
    }

    /**
     * 总记录数
     *
     * @param map
     * @return
     */
    @Override
    public Integer getTotal(Map<String, Object> map) {
        return blogTypeDao.getTotal(map);
    }

    /**
     * 添加博客类型
     *
     * @param blogType
     * @return
     */
    @Override
    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }

    /**
     * 修改博客类型m
     *
     * @param blogType@return
     */
    @Override
    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }
}
