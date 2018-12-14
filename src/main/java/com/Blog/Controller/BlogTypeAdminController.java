package com.Blog.Controller;

import com.Blog.Entity.BlogType;
import com.Blog.Entity.PageBean;
import com.Blog.Service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Transactional
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false, defaultValue = "1") String page, @RequestParam(value = "rows", required = false, defaultValue = "5") String rows) {
        Map<String, Object> result = new HashMap<>();
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        result.put("start", pageBean.getStart());
        result.put("size", pageBean.getPageSize());
        List<BlogType> blogTypes = blogTypeService.list(result);
        Integer total = blogTypeService.getTotal(result);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", blogTypes);
        map.put("total", total);
        return map;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(BlogType blogType) {
        Map<String, Object> result = new HashMap<>();
        //属于新增
        if (blogType.getId() == null) {
            if (blogTypeService.add(blogType) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        }
        //修改
        else {
            if (blogTypeService.update(blogType) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        }
        return result;
    }
}
