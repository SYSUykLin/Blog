package com.Blog.Service;

import com.Blog.Entity.Blog;
import com.Blog.Entity.BlogType;

import java.util.List;
import java.util.Map;

public interface BlogTypeService {

    /**
     * 获得博客类型
     * @return
     */
     public List<BlogType> countList();

    /**
     * 分页参数
     * @param map
     * @return
     */
    public List<BlogType> list(Map<String,Object> map);

    /**
     * 总记录数
     * @param map
     * @return
     */
    public Integer getTotal(Map<String,Object> map);

    /**
     * 添加博客类型
     * @param blogType
     * @return
     */
    public Integer add(BlogType blogType);

    /**
     * 修改博客类型
     * @param
     * @return
     */
    public Integer update(BlogType blogType);

}
