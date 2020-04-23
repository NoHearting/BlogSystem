package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.Blog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogMapper {

    /**
     * 查询所有博客
     * @return
     */
    @Select("select * from blog")
    public List<Blog> getAllBlogs();


    /**
     * 根据id查询博客
     * @param id
     * @return
     */
    @Select("select * from blog where bId = #{id}")
    public Blog getBlogById(Integer id);


    @Select("select * from blog where title like CONCAT('%',#{s},'%')")
    public List<Blog> getBlogsByKeyword(String s);

    @Select("select * from blog where  title like CONCAT('%',#{s},'%') limit #{begin},#{num}")
    public List<Blog> getBlogsByKeywordAndPage(@Param("s") String s, @Param("begin") int begin, @Param("num") int num);

    @Select("select count(*) from blog where title like CONCAT('%',#{s},'%')")
    public int getBlogCountByKeyword(String s);
}
