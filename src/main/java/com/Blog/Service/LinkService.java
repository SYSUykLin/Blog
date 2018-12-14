package com.Blog.Service;

import com.Blog.Entity.Link;

import java.util.List;
import java.util.Map;

public interface LinkService {
    /**
     * 得到全部友情链接
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
