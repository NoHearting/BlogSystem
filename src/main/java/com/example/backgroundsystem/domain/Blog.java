package com.example.backgroundsystem.domain;

import com.example.backgroundsystem.utils.MyDate;
import lombok.AllArgsConstructor;

import java.util.Date;


@AllArgsConstructor   // 添加全参构造器
public class Blog {
    private int bId;
    private String title;
    private String content;
    private Date writeTime;
    private int readTimes;

    public int getBId() {
        return bId;
    }

    public void setBId(int bId) {
        this.bId = bId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
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
                "bId=" + bId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writeTime=" + writeTime +
                ", readTimes=" + readTimes +
                '}';
    }
}
