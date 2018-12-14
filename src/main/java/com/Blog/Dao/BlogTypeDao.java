package com.Blog.Dao;

import com.Blog.Entity.BlogType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 博客类型接口
 */
@Repository
public interface BlogTypeDao {

    /**
     * 取得所有博客类型
     * @return
     */
    public List<BlogType> countList();

    /**
     * 根据id查找博客类型实体
     * @param id
     * @return
     */
    public BlogType findById(Integer id);

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
