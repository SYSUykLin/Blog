package com.Blog.Controller;

import com.Blog.Entity.Blog;
import com.Blog.Entity.PageBean;
import com.Blog.Service.BlogService;
import com.Blog.Util.PageUtil;
import com.Blog.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页面控制器
 */
@Controller
@RequestMapping("/")
public class indexController {

    @Autowired
    private BlogService blogService;

    /**
     * 请求主页
     *
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page", required = false) String page, HttpServletRequest request, @RequestParam(value = "typeid", required = false) String typeid, @RequestParam(value = "releasedateString", required = false) String releasedateString) {
        ModelAndView modelAndView = new ModelAndView();
        if (StringUtil.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 5);
        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder param = new StringBuilder();
        if (StringUtil.isNotEmpty(typeid))
        {
           param.append("typeid="+typeid + "&");
        }
        if (StringUtil.isNotEmpty(releasedateString))
        {
            param.append("releasedateString="+releasedateString + "&");
        }
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeid",typeid);
        map.put("releasedateString",releasedateString);
        List<Blog> blogs = blogService.list(map);
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/index.html", blogService.getTotal(map), Integer.parseInt(page), 5, param.toString()));
        modelAndView.addObject("mainPage", "foreground/commen/list.jsp");
        modelAndView.setViewName("mainTemplate");
        return modelAndView;
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ModelAndView download()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle","资源下载");
        modelAndView.addObject("mainPage","foreground/commen/download.jsp");
        modelAndView.setViewName("mainTemplate");
        return modelAndView;
    }
}
