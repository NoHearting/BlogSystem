package com.example.backgroundsystem.service;

import com.example.backgroundsystem.domain.Comment;
import com.example.backgroundsystem.domain.CommentPage;

public interface CommentService {

    CommentPage getComments(Integer pos, Integer currentPage, Integer pageMaxItems);


    void insertComment(Comment comment);
}
