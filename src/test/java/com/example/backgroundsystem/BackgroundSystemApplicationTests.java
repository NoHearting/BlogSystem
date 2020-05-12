package com.example.backgroundsystem;

import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.mapper.TestMapper;
import com.example.backgroundsystem.utils.MyDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
class BackgroundSystemApplicationTests {

    @Autowired
    TestMapper test;

    @Test
    void contextLoads() {
        for(int i = 0;i<Datas.datas.length;i++){
            test.insertToBlog(new Blog(i,("标题("+i+")"),Datas.datas[i],new Date(),100));
        }
    }


    @Test
    public void testDate(){
        Date date = new Date();
        date = new MyDate(date.getTime());
        System.out.println(date);
    }
}
