package com.Blog.Controller;

import com.Blog.Entity.Blog;
import com.Blog.Entity.PageBean;
import com.Blog.Luence.BlogIndex;
import com.Blog.Service.BlogService;
import com.Blog.Service.CommentService;
import com.Blog.Util.BlogSetRleasedateString;
import com.Blog.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogIndex blogIndex;

    @Autowired
    private CommentService commentService;

    /**
     * 添加或者修改
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Blog blog) {
        Integer tatol = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(blog.getId());
        //如果为空就是新增
        if (blog.getId() == null) {
            tatol = blogService.add(blog);
            blogIndex.addIndex(blog);//添加lucence索引
            if (tatol > 0) {
                map.put("success", true);
            } else {
                map.put("success", false);
                map.put("errorInfo", "服务器炸了!");
            }
        } else {
            if (blogService.update(blog) <= 0) {
                map.put("success", false);
                map.put("errorInfo", "服务器炸了，更新失败！");
            } else {
                blogIndex.update(blog);
                map.put("success", true);
            }
        }
        return map;
    }


    /**
     * 分页查询
     *
     * @param
     * @param page
     * @param rows
     * @param blog
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Blog blog) {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();
        map.put("title", StringUtil.formatLike(blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> blogs = blogService.list(map);
        Integer total = blogService.getTotal(map);
        BlogSetRleasedateString.addReleasedateString(blogs);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", blogs);
        result.put("total", total);
        return result;
    }

    @RequestMapping(value = "/deleteBlog", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids", required = false) String ids) {
        Map<String, Object> result = new HashMap<>();
        if (ids == null || "".equals(ids)) {
            result.put("success", false);
            result.put("error", "搜索字段不得为空");
            return result;
        } else {
            String[] idsString = ids.split(",");
            for (String string : idsString) {
                commentService.delete(Integer.parseInt(string));
                blogService.delete(Integer.parseInt(string));
                blogIndex.delete(string);
            }
            result.put("success", true);
            return result;
        }
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Blog FindById(@RequestParam("id") String id) {
        Blog blog = blogService.getBlogById(Integer.parseInt(id));
        return blog;
    }
}
