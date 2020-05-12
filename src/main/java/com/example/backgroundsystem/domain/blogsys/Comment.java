package com.example.backgroundsystem.domain.blogsys;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 评论对象，存储评论
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    private int cId;
    private String userName;    //评论用户的昵称
    private String userEmail;   // 评论用户的邮箱
    private String userWebSite; // 评论用户网站
    private String userHeader; // 用户头像路径
    private String content;  // 评论内容
    private Date writeTime;  // 评论日期
    private int writePosition; // 写的地方，流言还是评论博客
    private int reply; // 是否为一个回复

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserWebSite() {
        return userWebSite;
    }

    public void setUserWebSite(String userWebSite) {
        this.userWebSite = userWebSite;
    }

    public String getUserHeader() {
        return userHeader;
    }

    public void setUserHeader(String userHeader) {
        this.userHeader = userHeader;
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

    public int getWritePosition() {
        return writePosition;
    }

    public void setWritePosition(int writePosition) {
        this.writePosition = writePosition;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }
}
