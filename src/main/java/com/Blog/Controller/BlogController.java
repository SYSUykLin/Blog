package com.Blog.Controller;

import com.Blog.Entity.Blog;
import com.Blog.Entity.Comment;
import com.Blog.Luence.BlogIndex;
import com.Blog.Service.BlogService;
import com.Blog.Service.CommentService;
import com.Blog.Util.StringUtil;
import com.sun.webkit.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogIndex blogIndex;

    @RequestMapping(value = "/articles/{id}")
    public ModelAndView detailblog(@PathVariable("id") Integer id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Blog blog = blogService.getBlogById(id);
        String keywords = blog.getKeyword();
        if (StringUtil.isNotEmpty(keywords)) {
            String[] arr = keywords.split(" ");
            List<String> list = StringUtil.filterWhite(Arrays.asList(arr));
            modelAndView.addObject("keywords", list);
        }
        Comment comment = new Comment();
        comment.setBlogId(blog.getId());
        comment.setState(1);
        List<Comment> comments = commentService.list(comment);
        modelAndView.addObject("commentList", comments);
        modelAndView.addObject("pageCode", getUpAndDownPageCode(blogService.getLastBlog(blog.getId()), blogService.getNextBlog(blog.getId()), request.getContextPath()));
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("mainPage", "foreground/blog/view.jsp");
        blog.setClickhie(blog.getClickhie() + 1);
        blogService.update(blog);
        modelAndView.setViewName("mainTemplate");
        return modelAndView;
    }

    private String getUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
        StringBuffer pageCode = new StringBuffer();
        if (lastBlog == null || lastBlog.getId() == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>" + lastBlog.getTitle() + "</a></p>");
        }

        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>" + nextBlog.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }

    /**
     * 根据关键字查询博客信息
     *
     * @param p
     * @return
     */
    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "p", required = false) String p, @RequestParam(value = "page", required = false, defaultValue = "1") String page, HttpServletRequest request) throws UnsupportedEncodingException {
            p = new String(p.getBytes("iso-8859-1"), "utf-8");
        Integer pagesize = 8;
        Integer pageNow = Integer.parseInt(page);
        ModelAndView modelAndView = new ModelAndView();
        List<Blog> blogs = blogIndex.searchBlog(p);
        Integer toindex = blogs.size() >= pageNow * pagesize ? pageNow * pagesize : blogs.size();
        modelAndView.addObject("pageTitle", "搜索关键字" + p + "的结果");
        modelAndView.addObject("mainPage", "foreground/blog/result.jsp");
        modelAndView.addObject("blogList", blogs.subList((pageNow - 1) * pagesize, toindex));

        modelAndView.addObject("p", p);
        modelAndView.addObject("pageCode", getUpandDownPage(pageNow, blogs.size(), p, pagesize, request.getContextPath()));
        modelAndView.addObject("resultTotal", blogs.size());
        modelAndView.setViewName("mainTemplate");
        return modelAndView;
    }

    /**
     * 获取上一页下一页代码
     *
     * @param page
     * @param totalNum
     * @param p
     * @param pagesize
     * @param pageContext
     * @return
     */
    public String getUpandDownPage(Integer page, Integer totalNum, String p, Integer pagesize, String pageContext) {
        Integer totalPage = totalNum % pagesize == 0 ? totalNum / pagesize : totalNum / pagesize + 1;
        StringBuilder pageCode = new StringBuilder();
        if (totalPage == 0) {
            return "";
        }
        pageCode.append("<nav>");

        pageCode.append("<ul class = 'pager' >");

        //不是第一页可以点
        if (page > 1) {
            pageCode.append("<li><a href='" + pageContext + "/blog/search.do?page=" + (page - 1) + "&p=" + p + "'>上一页</a></li>");
        } else {
            pageCode.append("<li class = 'disabled'><a href = '#'>上一页</a></li>");
        }
        if (page < totalPage) {
            pageCode.append("<li><a href='" + pageContext + "/blog/search.do?page=" + (page + 1) + "&p=" + p + "'>下一页</a></li>");
        } else {
            pageCode.append("<li class = 'disabled'><a href = '#'>下一页</a></li>");
        }
        pageCode.append("</ul>");

        pageCode.append("</nav>");
        return pageCode.toString();
    }
}
