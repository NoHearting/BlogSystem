package com.example.backgroundsystem;

import com.example.backgroundsystem.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogImplTest {

    @Autowired
    BlogService blogService;

    @Test
    public void calBlogNoContentByDate(){
        blogService.calBlogByDate();
    }
}
