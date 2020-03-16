package com.example.backgroundsystem.service.impl;

import com.example.backgroundsystem.domain.CommentPage;
import com.example.backgroundsystem.mapper.CommentMapper;
import com.example.backgroundsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public CommentPage getComments(Integer pos, Integer currentPage, Integer pageMaxItems) {
        CommentPage commentPage = new CommentPage();
        int totalItems = commentMapper.getTotalCount();  // 所有条目
        int totalPages = calTotalPages(totalItems,pageMaxItems);
        int begin = calBeginItemIndex(pageMaxItems,currentPage);

        commentPage.setCurrentPage(currentPage);
        commentPage.setPageMaxItems(pageMaxItems);
        commentPage.setTotalItems(totalItems);
        commentPage.setTotalPages(totalPages);
        if(pos<0){
            commentPage.setComments(commentMapper.getAllCommentsForMessageAndPaging(begin,pageMaxItems));
        }else{
            commentPage.setComments(commentMapper.getCommentsByBlogIdAndPaging(pos,begin,pageMaxItems));
        }
        return commentPage;
    }

    /**
     * 计算查询的起始位置
     * @param pageMaxItems
     * @param currentPage
     * @return
     */
    int calBeginItemIndex(int pageMaxItems,int currentPage){
        int begin = 0;
        begin = pageMaxItems*(currentPage-1);
        return begin;
    }

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
