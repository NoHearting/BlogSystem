package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.domain.Comment;
import com.example.backgroundsystem.domain.CommentPage;
import com.example.backgroundsystem.mapper.CommentMapper;
import com.example.backgroundsystem.service.CommentService;
import com.example.backgroundsystem.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    /**
     * 获取评论。
     * 从【留言】获取评论，或者获取某一个【博客】下的评论。
     * @param pos 为负数，则获取【留言】下的评论；为正数，则获取pos标识的【博客】下的评论
     * @param currentPage 当前页，评论有多页，标志现在获取的那一页
     * @param pageMaxItems  每页的最大评论条数。
     * @return
     */
    @Override
    public CommentPage getComments(Integer pos, Integer currentPage, Integer pageMaxItems) {
        CommentPage commentPage = new CommentPage();

        int totalItems = pos >= 0 ? commentMapper.getTotalCountById(pos):commentMapper.getTotalCount();
        int totalPages = calTotalPages(totalItems,pageMaxItems);
        int begin = calBeginItemIndex(pageMaxItems,currentPage);

        commentPage.setTotalItems(totalItems);
        commentPage.setCurrentPage(currentPage);
        commentPage.setPageMaxItems(pageMaxItems);
        commentPage.setTotalPages(totalPages);
        if(pos<0){
            commentPage.setComments(commentMapper.getAllCommentsForMessageAndPaging(begin,pageMaxItems));

        }else{
            commentPage.setComments(commentMapper.getCommentsByBlogIdAndPaging(pos,begin,pageMaxItems));

        }
        return commentPage;
    }

    @Override
    public void insertComment(Comment comment) {
        comment.setWriteTime(new Date());  // 设置comment的日期
        LoggerUtils.info(comment.toString());
        commentMapper.insertComment(comment);
    }

    /**
     * 计算查询的起始位置
     * @param pageMaxItems  页面最大评论条数
     * @param currentPage   当前页面页码
     * @return
     */
    int calBeginItemIndex(int pageMaxItems,int currentPage){
        int begin = 0;
        if(currentPage<1){
            return begin;
        }
        begin = pageMaxItems*(currentPage-1);
        return begin;
    }

    /**
     * 计算总页面条数
     * @param totalItems   总条目数
     * @param pageMaxItems  页面最大条目数
     * @return
     */
    int calTotalPages(int totalItems,int pageMaxItems){
        if(totalItems < pageMaxItems){
            return 1;
        }else if(totalItems%pageMaxItems==0){
            return totalItems/pageMaxItems;
        }else{
            return totalItems/pageMaxItems+1;
        }
    }
}
