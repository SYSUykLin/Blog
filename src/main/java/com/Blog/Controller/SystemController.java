package com.Blog.Controller;

import com.Blog.Entity.Blog;
import com.Blog.Entity.BlogType;
import com.Blog.Entity.Blogger;
import com.Blog.Entity.Link;
import com.Blog.Service.BlogService;
import com.Blog.Service.BlogTypeService;
import com.Blog.Service.BloggerService;
import com.Blog.Service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.ServletContextPropertyUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/admin/system")
public class SystemController {
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping("/refresh")
    @ResponseBody
    public String refresh(HttpServletRequest request)
    {
        ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();

        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        application.setAttribute("blogger",blogger);

        List<Link> links = linkService.getLink();
        application.setAttribute("linklist",links);

        List<BlogType> blogTypes = blogTypeService.countList();
        application.setAttribute("blogTypes",blogTypes);

        List<Blog> blogs = blogService.countList();
        application.setAttribute("blogdate",blogs);

        return "true";
    }
}
