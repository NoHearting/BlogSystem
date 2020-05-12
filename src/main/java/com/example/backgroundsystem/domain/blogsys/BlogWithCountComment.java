package com.example.backgroundsystem.domain.blogsys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
public class BlogWithCountComment extends Blog {

    @Getter
    @Setter
    private int countComments;

    public BlogWithCountComment(int bId, String title, String content, Date writeTime, int readTimes,int countComments){
        super(bId, title, content, writeTime, readTimes);
        this.countComments = countComments;
    }

    public BlogWithCountComment(Blog blog,int countComments){
        super(blog);
        this.countComments = countComments;
    }

    @Override
    public String toString() {
        return "BlogWithCountComment{" +
                super.toString()+
                "countComments=" + countComments +
                '}';
    }
}
