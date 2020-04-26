package com.example.backgroundsystem.domain;

import com.example.backgroundsystem.utils.MyDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
public class Blog extends BaseBlog{
    private String content;
    private int readTimes;

    public Blog(int bId,String title,String content,Date writeTime,int readTimes){
        super(bId, title, writeTime);
        this.content = content;
        this.readTimes = readTimes;
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
                ", writeTime=" + getWriteTime() +
                ", readTimes=" + readTimes +
                '}';
    }
}
