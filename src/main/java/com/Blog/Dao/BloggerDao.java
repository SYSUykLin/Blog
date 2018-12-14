package com.Blog.Dao;

import com.Blog.Entity.Blogger;
import com.Blog.Entity.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloggerDao {
    /**
     * 根据名字找到博主
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
