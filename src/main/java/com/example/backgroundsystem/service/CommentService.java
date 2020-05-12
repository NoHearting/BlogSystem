package com.example.backgroundsystem.service;

import com.example.backgroundsystem.domain.blogsys.Comment;
import com.example.backgroundsystem.domain.page.CommentPage;

public interface CommentService {

    CommentPage getComments(Integer pos, Integer currentPage, Integer pageMaxItems);


    void insertComment(Comment comment);

    int countBlogComments(int id);
}
