package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.blogsys.Blog;
import com.example.backgroundsystem.domain.blogsys.BlogNoContent;
import com.example.backgroundsystem.domain.blogsys.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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

    /**
     * 插入一条博客并且返回主键
     * @param blog
     * @param tag
     */
    @Options(useGeneratedKeys = true, keyProperty = "blog.bId", keyColumn = "bId")  //返回主键的值
    @Insert("insert into blog(title,writeTime,readTimes,content,tId) values(#{blog.title},#{blog.writeTime},#{blog.readTimes},#{blog.content},#{tId})")
    void insertBlog(@Param("blog") Blog blog,@Param("tId") int tag);

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


    /**
     * 删除一篇博客
     * @param id
     */
    @Delete("delete from blog where bId = #{id}")
    void deleteBlog(int id);

    /**
     * 查询所有的标签
     * @return
     */
    @Select("select * from tag")
    List<Tag> listBlogTags();

    /**
     *  根据博客ID查询标签
     * @param bId
     * @return
     */
    @Select("select tag.* from blog,tag where blog.bId = #{bId} and  blog.tId = tag.tId")
    Tag getTagByBlogId(int bId);

    /**
     * 根据主键查询标签
     * @param tId
     * @return
     */
    @Select("select * from tag where tId = #{tId}")
    Tag getTagById(int tId);

    /**
     * 添加博客和标签之间的对应关系，即给博客加上标签
     * @param tId
     * @param bId
     */
    @Insert("insert into blog_tag(bId,tId) values(#{bId},#{tId})")
    void insertTagWithBlog(@Param("tId") int tId, @Param("bId") int bId);


    @Results(id = "blog",value = {
            @Result(property = "tag",column = "tId",javaType = com.example.backgroundsystem.domain.blogsys.Tag.class,
                    one = @One(select = "com.example.backgroundsystem.mapper.BlogMapper.getTagById"
                            ,fetchType = FetchType.LAZY
                           ))
    })
    @Select("select bId,title,tId,writeTime from blog limit #{begin},#{num}")
    List<Blog> listBlog(@Param("begin") int begin, @Param("num") int num);

}
