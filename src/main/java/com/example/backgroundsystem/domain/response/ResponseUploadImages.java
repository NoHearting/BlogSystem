package com.example.backgroundsystem.domain.response;

import java.io.Serializable;

/**
 * 写博客时上传图片成功后的返回信息
 */
public class ResponseUploadImages implements Serializable {
    private int success;   //成功与否
    private String message;  //信息
    private String url;    //地址

    public ResponseUploadImages(int success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public ResponseUploadImages() {
    }

    @Override
    public String toString() {
        return "ResponseUploadImages{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
