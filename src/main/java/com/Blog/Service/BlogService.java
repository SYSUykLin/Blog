package com.Blog.Service;

import com.Blog.Entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogService {

    /**
     * 按照日期分组查询
     * @return
     */
    public List<Blog> countList();

    /**
     * 分页获得博客
     * @param map
     * @return
     */
    public List<Blog> list(Map<String,Object> map);

    /**
     * 获得博客数量
     * @param map
     * @return
     */
    public Integer getTotal(Map<String,Object> map);

    /**
     * 通过id获取博客
     * @param id
     * @return
     */
    public Blog getBlogById(Integer id);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    public Integer update(Blog blog);

    /**
     * 得到上一篇博客
     * @param id
     * @return
     */
    public Blog getLastBlog(Integer id);

    /**
     * 得到下一篇博客
     * @param id
     * @return
     */
    public Blog getNextBlog(Integer id);

    /**
     * 添加博客
     * @param blog
     * @return
     */
    public Integer add(Blog blog);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public Integer delete(Integer id);
}
