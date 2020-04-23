package com.example.backgroundsystem;


import com.example.backgroundsystem.mapper.CommentMapper;
import com.example.backgroundsystem.utils.LoggerUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTest {

    @Autowired
    CommentMapper commentMapper;

    @Test
    public void testSelect(){
        LoggerUtils.error(commentMapper.getAllComments().toString());
        LoggerUtils.warn(commentMapper.getAllCommentsForMessageAndPaging(0,10).toString());


    }
}
