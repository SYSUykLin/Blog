package com.Blog.Service.impl;

import com.Blog.Dao.BloggerDao;
import com.Blog.Entity.Blogger;
import com.Blog.Entity.Link;
import com.Blog.Service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloggerServiceImpl implements BloggerService {
    @Autowired
    private BloggerDao bloggerDao;

    /**
     * 得到博主信息
     *
     * @param username
     * @return
     */
    public Blogger getByName(String username) {
        return bloggerDao.getByName(username);
    }

    public Blogger find() {
        return bloggerDao.find();
    }

    /**
     * 更新博主信息
     *
     * @param blogger
     * @return
     */
    @Override
    public Integer update(Blogger blogger) {
        return bloggerDao.update(blogger);
    }

}
