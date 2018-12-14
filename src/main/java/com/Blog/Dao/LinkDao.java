package com.Blog.Dao;

import com.Blog.Entity.Link;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LinkDao {
    /**
     * 获得友情链接
     * @return
     */
    public List<Link> getLink();

    /**
     * 得到友情链接数量
     * @return
     */
    public Integer getTotal();

    /**
     * 得到分页信息
     * @param map
     * @return
     */
    public List<Link> list(Map<String,Object> map);
}
