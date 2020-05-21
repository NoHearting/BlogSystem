package com.example.backgroundsystem.domain.blogsys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@JsonIgnoreProperties(value = { "handler" })
public class Blog extends BaseBlog{
    private String content;
    private int readTimes;


    public Blog(int bId,String title,String content,Date writeTime,int readTimes){
        super(bId, title, writeTime);
        this.content = content;
        this.readTimes = readTimes;
    }

    public Blog(Blog blog){
        super(blog.getbId(),blog.getTitle(),blog.getWriteTime());
        this.content = blog.getContent();
        this.readTimes = blog.getReadTimes();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(int readTimes) {
        this.readTimes = readTimes;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "bId=" + getbId() +
                ", title='" + getTitle() + '\'' +
                ", content='" + content + '\'' +
                ",tag="+getTag()+"\'"+
                ", writeTime=" + getWriteTime() +
                ", readTimes=" + readTimes +
                '}';
    }
}
