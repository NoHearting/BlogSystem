package com.example.backgroundsystem.domain.blogsys;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class UpdateEvent {
    private int uId;
    private int flag;
    private Date execTime;
    private String content;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UpdateEvent{" +
                "uId=" + uId +
                ", flag=" + flag +
                ", execTime=" + execTime +
                ", content='" + content + '\'' +
                '}';
    }
}
