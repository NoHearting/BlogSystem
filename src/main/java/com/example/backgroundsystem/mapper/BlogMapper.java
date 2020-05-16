package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.blogsys.BlogNoContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {

    /**
     * 查询所有博客
     * @return
     */
    @Select("select * from blog")
    List<Blog> getAllBlogs();


    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @Select("select * from blog where bId = #{id}")
    Blog getBlogById(Integer id);



    @Select("select * from blog where title like CONCAT('%',#{s},'%')")
    List<Blog> getBlogsByKeyword(String s);

    @Select("select * from blog where  title like CONCAT('%',#{s},'%') limit #{begin},#{num}")
    List<Blog> getBlogsByKeywordAndPage(@Param("s") String s, @Param("begin") int begin, @Param("num") int num);

    @Select("select count(*) from blog where title like CONCAT('%',#{s},'%')")
    int getBlogCountByKeyword(String s);

    @Select("select count(*) from blog")
    int countBlog();

    /**
     * 分页查询博客，但不封装博客内容
     * @param begin
     * @param num
     * @return
     */
    @Select("select bId,title,writeTime from blog limit #{begin},#{num}")
    List<BlogNoContent> listBlogNoContent(@Param("begin") int begin, @Param("num") int num);


    @Insert("insert into blog(title,writeTime,readTimes,content) values(#{title},#{writeTime},#{readTimes},#{content})")
    void insertBlog(Blog blog);

    @Select("select bId,title,writeTime,readTimes from blog order by writeTime desc")
    List<Blog> listAllBlog();

}
