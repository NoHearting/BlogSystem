package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.blogsys.BlogNoContent;
import org.apache.ibatis.annotations.*;

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


    /**
     * 查询当前id的blog的前一篇blog
     * @param id
     * @return
     */
    @Select("select bId,title from blog where bId < #{id} order by bId desc limit 0,1")
    Blog getPreBlog(int id);

    /**
     * 查询当前id的blog的后一篇blog
     * @param id
     * @return
     */
    @Select("select bId,title from blog where bId > #{id} order by bId limit 0,1")
    Blog getAfterBlog(int id);

    /**
     * 获取当前id的blog，避免无法获取前一篇博客和后一篇博客而出现异常
     * 如果当前博客前面面没有博客或者后面也没有博客，就返回当前博客
     * @param id
     * @return
     */
    @Select("select bId,title from blog where bId = #{id}")
    Blog getCurrentBlog(int id);


    /**
     * 每获取一次博客，就将博客的阅读次数加一
     * @param id
     */
    @Update("update blog set readTimes = readTimes + 1 where bId = #{id}")
    void addBlogReadTimes(int id);


    @Delete("delete from blog where bId = #{id}")
    void deleteBolg(int id);
}
