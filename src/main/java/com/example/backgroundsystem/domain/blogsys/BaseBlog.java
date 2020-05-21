package com.example.backgroundsystem.domain.blogsys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "handler" })
public class BaseBlog {
    private int bId;
    private String title;   //博客标题
    private Date writeTime; //编写博客的时间
    private Tag tag;        //博客的标签

    public BaseBlog(int bId,String title,Date writeTime){
        this.title = title;
        this.writeTime = writeTime;
        this.bId = bId;
        tag = null;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
