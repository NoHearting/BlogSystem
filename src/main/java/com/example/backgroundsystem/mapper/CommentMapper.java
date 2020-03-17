package com.example.backgroundsystem.mapper;

import com.example.backgroundsystem.domain.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作评论
 */
public interface CommentMapper {


    /**
     * 获取评论条目数量
     * @return
     */
    @Select("select count(*) from comment")
    int getTotalCount();

    /**
     * 查询所有评论，无论是评论博客或者留言
     * @return
     */
    @Select("select * from comment")
    List<Comment> getAllCommtents();




    /**
     * 查找所有留言
     *
     * @return
     */
    @Select("select * from comment where writePosition < 0")
    List<Comment> getAllCommentsForMessage();

    /**
     * 查找所有对博客的评论
     *
     * @return
     */
    @Select("select * from comment where writePosition >= 0")
    List<Comment> getAllCommentsForBlog();


    /**
     * 根据博客id查询博客下的评论
     * @param id
     * @return
     */
    @Select("select * from comment where writePosition = #{id}")
    List<Comment> getCommentsByBlogId(int id);


    /**
     * 分页查询博客下的评论
     * @param id  标志哪个博客
     * @param begin  从哪开始取
     * @param num   取多少条目
     * @return
     */
    @Select("select * from comment where writePosition = #{id} limit #{begin},#{num}")
    List<Comment> getCommentsByBlogIdAndPaging(@Param("id") int id, @Param("begin") int begin, @Param("num") int num);

    /**
     * 分页查询留言
     *
     * @param begin
     * @param num
     * @return
     */
    @Select("select * from comment where writePosition < 0 limit #{begin},#{num}")
    List<Comment> getAllCommentsForMessageAndPaging(@Param("begin") int begin, @Param("num") int num);

    /**
     * 插入评论
     * @param comment
     */
    @Select("insert into comment(userName,userEmail,userWebSite,userHeader,content,writeTime,writePosition,reply) values(#{userName},#{userEmail},#{userWebSite},#{userHeader},#{content},#{writeTime},#{writePosition},#{reply})")
    void insertComment(Comment comment);
}
