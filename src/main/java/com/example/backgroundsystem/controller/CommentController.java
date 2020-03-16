package com.example.backgroundsystem.controller;

import com.example.backgroundsystem.domain.CommentPage;
import com.example.backgroundsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 获取博客评论，分页获取
     * @param pos
     * @param currentPage
     * @param pageMaxItems
     * @return
     */
    @ResponseBody
    @RequestMapping("getComments")
    public CommentPage getComments(Integer pos, Integer currentPage, Integer pageMaxItems){
        CommentPage comments = commentService.getComments(pos, currentPage, pageMaxItems);
        return comments;
    }
}
