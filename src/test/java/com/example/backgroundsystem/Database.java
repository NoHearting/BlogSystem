package com.example.backgroundsystem;

import com.example.backgroundsystem.domain.Blog;
import com.example.backgroundsystem.domain.Link;
import com.example.backgroundsystem.mapper.BlogMapper;
import com.example.backgroundsystem.mapper.LinkMapper;
import com.example.backgroundsystem.service.BlogService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Database {

    @Autowired
    LinkMapper linkMapper;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogService blogService;

    @Test
    public void testSearch(){
        System.out.println(blogMapper == null);
        List<Blog> blogsByKeyword = blogMapper.getBlogsByKeyword("1");
        if(blogsByKeyword != null) {
            System.out.println(blogsByKeyword);
        }
    }

    @Test
    public void isnertFriendLink(){
        Link link = new Link(0,"test","www.xl-zsj.top","/Blog/statics/images/8e5da64c712d2bb59235d8d746108cb1.jpeg");
        for(int i = 0;i<5;i++){
            linkMapper.insertLink(link);
        }
    }

    @Test
    public void listBlogNoContent(){
        System.out.println(blogMapper.listBlogNoContent(1,10));
        blogService.listBlogNoContent(1,10);
    }
}
