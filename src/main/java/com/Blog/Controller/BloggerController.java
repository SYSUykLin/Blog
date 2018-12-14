package com.Blog.Controller;

import com.Blog.Entity.Blog;
import com.Blog.Entity.Blogger;
import com.Blog.Service.BloggerService;
import com.Blog.Util.PasswordUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;

    @RequestMapping("/login")
    public String login(Model model, Blogger blogger) {
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(blogger.getUsername(), PasswordUtil.md5(blogger.getPassword(), blogger.getUsername()));
        try {
            subject.login(usernamePasswordToken);
            return "redirect:/admin/main.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("blogger", blogger);
            model.addAttribute("errorInfo", "用户名或密码错误");
            return "/login";
        }
    }

    @RequestMapping(value = "/aboutme", method = RequestMethod.GET)
    public ModelAndView aboutme() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "关于博主");
        modelAndView.addObject("mainPage", "foreground/commen/info.jsp");
        modelAndView.setViewName("mainTemplate");
        return modelAndView;
    }

}
