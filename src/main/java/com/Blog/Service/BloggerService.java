package com.Blog.Service;

import com.Blog.Entity.Blogger;
import com.Blog.Entity.Link;

import java.util.List;

/**
 * 博主业务逻辑层
 */
public interface BloggerService {
    /**
     * 得到博主信息
     * @param username
     * @return
     */
    public Blogger getByName(String username);

    /**
     * 获得初始化博主信息
     * @return
     */
    public Blogger find();

    /**
     * 更新博主信息
     * @param blogger
     * @return
     */
    public Integer update(Blogger blogger);
}
