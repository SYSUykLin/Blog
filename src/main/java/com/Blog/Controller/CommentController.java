package com.Blog.Controller;

import com.Blog.Entity.Blog;
import com.Blog.Entity.Comment;
import com.Blog.Service.BlogService;
import com.Blog.Service.CommentService;
import com.Blog.Util.ResponseUtil;
import org.apache.shiro.web.session.HttpServletSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/save", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> save(HttpServletRequest request, Comment comment, @RequestParam("imageCode") String imageCode, HttpSession session, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        String srand = (String) session.getAttribute("sRand");
        if (!imageCode.equals(srand)) {
            map.put("success", false);
            map.put("errorInfo", "验证码错误");
        } else {
            String userIp = request.getRemoteAddr();
            if (comment.getId() == null) {//评论没有id  新评论增加
                comment.setUserIp(userIp);
                if (commentService.add(comment) <= 0) {
                    map.put("errorInfo", "系统操作错误");
                }
                //博客回复次数加一
                Blog blog = blogService.getBlogById(comment.getBlogId());
                blog.setReplyhit(blog.getReplyhit()+1);
                blogService.update(blog);
                map.put("success",true);
            } else {//评论有id是会修改

            }
        }
        return map;
    }


}
