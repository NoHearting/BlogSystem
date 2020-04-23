package com.example.backgroundsystem.controller;

import com.alibaba.fastjson.JSON;
import com.example.backgroundsystem.domain.Comment;
import com.example.backgroundsystem.domain.CommentPage;
import com.example.backgroundsystem.service.CommentService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Hashtable;
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
        try{
            CommentPage comments = commentService.getComments(pos, currentPage, pageMaxItems);
            return comments;
        }catch (Exception e){
            return new CommentPage();
        }


    }


    /**
     * 添加评论。
     * @param comment 字段中writeTime没有获取，在service层添加
     */
    @ResponseBody
    @RequestMapping("insertComment")
    public String insertComment(Comment comment){
        System.out.println(comment);
        commentService.insertComment(comment);

        // 获取评论所在的地方【博客】或者【留言】
        int writePosition = comment.getWritePosition();
        Map<String,Object> json = new HashMap<>();
        json.put("status","success");
        json.put("writePosition",writePosition);
        String s = JSON.toJSONString(json);
        return s;
    }
}
