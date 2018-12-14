package com.Blog.Service.impl;

import com.Blog.Dao.LinkDao;
import com.Blog.Entity.Link;
import com.Blog.Service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    /**
     * 得到全部友情链接
     *
     * @return
     */
    public List<Link> getLink() {
       return linkDao.getLink();
    }

    /**
     * 得到友情链接数量
     *
     * @return
     */
    @Override
    public Integer getTotal() {
        return linkDao.getTotal();
    }

    /**
     * 得到分页信息
     *
     * @param map
     * @return
     */
    @Override
    public List<Link> list(Map<String, Object> map) {
        return linkDao.list(map);
    }
}
