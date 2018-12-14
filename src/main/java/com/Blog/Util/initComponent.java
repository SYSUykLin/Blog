package com.Blog.Util;

import com.Blog.Entity.Blog;
import com.Blog.Entity.BlogType;
import com.Blog.Entity.Blogger;
import com.Blog.Entity.Link;
import com.Blog.Service.BlogService;
import com.Blog.Service.BlogTypeService;
import com.Blog.Service.BloggerService;
import com.Blog.Service.LinkService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

@Component
public class initComponent implements ServletContextListener,ApplicationContextAware{

    private static ApplicationContext applicationContext;

    public void contextInitialized(ServletContextEvent sce) {
        //放入博主信息
        ServletContext application = sce.getServletContext();
        BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerServiceImpl");
        Blogger blogger = bloggerService.find();
        blogger.setPassword(null);
        application.setAttribute("blogger",blogger);

        //放入友情链接信息
        LinkService linkService = (LinkService) applicationContext.getBean("linkServiceImpl");
        List<Link> links = linkService.getLink();
        application.setAttribute("linklist",links);

        //日志类别
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeServiceImpl");
        List<BlogType> blogTypes = blogTypeService.countList();
        application.setAttribute("blogTypes",blogTypes);

        //日志日期排序
        BlogService blogService = (BlogService) applicationContext.getBean("blogServiceImpl");
        List<Blog> blogs = blogService.countList();
        application.setAttribute("blogdate",blogs);

    }
    public void contextDestroyed(ServletContextEvent sce) {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
