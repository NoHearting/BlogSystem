package com.example.backgroundsystem;

import com.example.backgroundsystem.domain.Blog;
import com.example.backgroundsystem.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Database {

    @Autowired
    BlogMapper blogMapper;

    @Test
    public void testSearch(){
        System.out.println(blogMapper == null);
        List<Blog> blogsByKeyword = blogMapper.getBlogsByKeyword("1");
        if(blogsByKeyword != null) {
            System.out.println(blogsByKeyword);
        }
    }
}
