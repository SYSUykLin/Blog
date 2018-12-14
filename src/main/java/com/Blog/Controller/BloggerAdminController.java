package com.Blog.Controller;

import com.Blog.Entity.Blogger;
import com.Blog.Service.BloggerService;
import com.Blog.Util.DateUtil;
import com.Blog.Util.PasswordUtil;
import com.Blog.Util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@Transactional
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    @Autowired
    private BloggerService bloggerService;

    @RequestMapping("/find")
    @ResponseBody
    public Blogger find()
    {
        Blogger blogger = bloggerService.find();
        return blogger;
    }

    @RequestMapping("/save")
    public String save(HttpSession session,@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger, HttpServletRequest request, HttpServletResponse response)throws Exception{
        if(!imageFile.isEmpty()){
            String filePath=request.getServletContext().getRealPath("/");
            String imageName= DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath+"static/images/"+imageName));
            blogger.setImage(imageName);
        }
        int resultTotal=bloggerService.update(blogger);
        StringBuffer result=new StringBuffer();
        session.setAttribute("currentUser",blogger);
        if(resultTotal > 0){
            result.append("<script language='javascript'>alert('修改成功！');</script>");
        }else{
            result.append("<script language='javascript'>$.messager.alert('修改失败！');</script>");
        }
        ResponseUtil.write(response, result);
        return null;
    }


    @RequestMapping(value = "/modifyPassword",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> updatePassword(String newPassword, HttpSession session)
    {
         Map<String,Object> result = new HashMap<>();
         Blogger blogger = (Blogger) session.getAttribute("currentUser");
         blogger.setPassword(PasswordUtil.md5(newPassword, blogger.getUsername()));
         if (bloggerService.update(blogger) > 0)
         {
             result.put("success",true);
         }
         else
         {
             result.put("success",false);
         }
         return result;
    }

    @RequestMapping("/logout")
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
