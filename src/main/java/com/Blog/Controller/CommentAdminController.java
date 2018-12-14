package com.Blog.Controller;

import com.Blog.Entity.Comment;
import com.Blog.Entity.PageBean;
import com.Blog.Service.CommentService;
import com.Blog.Util.CommentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员评论
 */
@Transactional
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Autowired
    private CommentService commentService;

    /**
     * 分页查询评论信息
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "state", required = false) String state, @RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows) {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<>();
        map.put("state", state);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());

        List<Comment> comments = commentService.getListByPage(map);
        CommentUtil.setDateString(comments);
        Integer total = commentService.getTotal(map);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", comments);
        result.put("total", total);
        return result;
    }

    @RequestMapping(value = "/review", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> review(@RequestParam(value = "ids", required = false) String ids, @RequestParam(value = "state", required = false) String state) {
        Map<String, Object> result = new HashMap<>();
        String[] id = ids.split(",");

        //评论不通过删除
        if ("0".equals(state)) {
            for (String string : id) {
                Comment comment = new Comment();
                comment.setId(Integer.parseInt(string));
                if (commentService.deleteById(comment) > 0) {
                    result.put("success", true);
                } else {
                    result.put("success", false);
                    return result;
                }
            }
            return result;
        }

        for (String string : id) {
            Comment comment = new Comment();
            comment.setId(Integer.parseInt(string));
            comment.setState(Integer.parseInt(state));
            if (commentService.updateComment(comment) > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
                return result;
            }
        }
        return result;
    }
}
