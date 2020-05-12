package com.example.backgroundsystem.domain.blogsys;

import java.util.Date;

/**
 * 博客类，没有内容和readTimes
 */
public class BlogNoContent extends BaseBlog{
    public BlogNoContent() {
    }

    public BlogNoContent(int bId, String title, Date writeTime) {
        super(bId, title, writeTime);
    }


}
