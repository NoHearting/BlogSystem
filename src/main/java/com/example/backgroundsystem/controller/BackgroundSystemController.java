package com.example.backgroundsystem.controller;


import com.example.backgroundsystem.mapper.BlogMapper;
import com.example.backgroundsystem.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


/**
 * 这要进行后台系统的页面映射
 */
@Controller
@RequestMapping("/admin")
public class BackgroundSystemController {

    @Autowired
    private BlogService blogService;

    /**
     * 首页
     * @return
     */
    @GetMapping({"/","index.html"})
    public String index(){
        return "BkSys/index";
    }

    /**
     * 欢迎界面  welcome.html
     * @return
     */
    @GetMapping("welcome")
    public String welcome(){
        return "BkSys/welcome";
    }

    /**
     * 栏目列表  cate-list.html
     * @return
     */
    @GetMapping("catelist")
    public String cateList(){
        return "BkSys/cate-list";
    }

    /**
     * 权限分类 admin-cate.html
     * @return
     */
    @GetMapping("admincate")
    public String adminCate(){
        return "BkSys/admin-cate";
    }

    /**
     * 管理员列表 admin-list.html
     * @return
     */
    @GetMapping("adminlist")
    public String adminList(){
        return "BkSys/admin-list";
    }

    /**
     * 角色管理 admin-role.html
     * @return
     */
    @GetMapping("adminrole")
    public String adminRole(){
        return "BkSys/admin-role";
    }

    /**
     * 权限管理 admin-rule.html
     * @return
     */
    @GetMapping("adminrule")
    public String adminRule(){
        return "BkSys/admin-rule";
    }

    /**
     *
     * 文章列表 article-list.html
     * 获取当前页码的博客，默认当前页码为1，页码最大条目数为10
     * @return
     */
    @GetMapping("articlelist")
    public String articleList(Map<String,Object> map){
        map.put("blogPage",blogService.listBlogNoContent(1,10));
        return "BkSys/article-list";
    }

    /**
     * 文章类型列表 articletype-list.html
     * @return
     */
    @GetMapping("articletypelist")
    public String articleTypeList(){
        return "BkSys/articletype-list";
    }

    /**
     * 添加文章
     */
    @GetMapping("articleadd")
    public String articleAdd(){return "BkSys/article-add";}


    @GetMapping("writeArticle")
    public String writeArticle(){
        return "BkSys/write-article";
    }

    /**
     * 轮播列表 banner-list
     * @return
     */
    @GetMapping("bannerlist")
    public String bannerList(){
        return "BkSys/banner-list";
    }

    /**
     * 分类列表  category.html
     * @return
     */
    @GetMapping("category")
    public String category(){
        return "BkSys/category";
    }

    /**
     * 评论列表 comment-list.html
     * @return
     */
    @GetMapping("commentlist")
    public String commentList(){
        return "BkSys/comment-list";
    }

    /**
     * 统计报表 echart.html
     * @return
     */
    @GetMapping("echart")
    public String echart(){
        return "BkSys/echart";
    }

    /**
     * 意见反馈 feedback-list.html
     * @return
     */
    @GetMapping("feedbacklist")
    public String feedbackList(){
        return "BkSys/feedback-list";
    }

    /**
     * 删除会员 member-del.html
     * @return
     */
    @GetMapping("memberdel")
    public String memberDel(){
        return "BkSys/member-del";
    }

    /**
     * 积分管理 member-kiss.html
     * @return
     */
    @GetMapping("memberkiss")
    public String memberKiss(){
        return "BkSys/member-kiss";
    }

    /**
     * 等级管理 member-level.html
     * @return
     */
    @GetMapping("memberlevel")
    public String memberLevel(){
        return "BkSys/member-level";
    }

    /**
     * 会员列表 member-list.html
     * @return
     */
    @GetMapping("memberlist")
    public String memberList(){
        return "BkSys/member-list";
    }

    /**
     * 显示会员 member-show.html
     * @return
     */
    @RequestMapping("membershow")
    public String memberShow(){
        return "BkSys/member-show";
    }

    /**
     * 浏览/分享记录 member-view.html
     * @return
     */
    @GetMapping("memberview")
    public String memberView(){
        return "BkSys/member-view";
    }

    /**
     * 公告列表 notice-list.html
     * @return
     */
    @GetMapping("noticelist")
    public String noticeList(){
        return "BkSys/notice-list";
    }

    /**
     * 公告类型列表 noticetype-list.html
     * @return
     */
    @GetMapping("noticetypelist")
    public String noticeTypeList(){
        return "BkSys/noticetype-list";
    }

    /**
     * 数字字典 sys-data.html
     * @return
     */
    @GetMapping("sysdata")
    public String sysData(){
        return "BkSys/sys-data";
    }

    /**
     * 友情链接 sys-link.html
     * @return
     */
    @GetMapping("syslink")
    public String sysLink(){
        return "BkSys/sys-link";
    }

    /**
     * 系统日志 sys-log.html
     * @return
     */
    @GetMapping("syslog")
    public String sysLog(){
        return "BkSys/sys-log";
    }

    /**
     * 第三方登录 sys-qq.html
     * @return
     */
    @GetMapping("sysqq")
    public String sysQq(){
        return "BkSys/sys-qq";
    }

    /**
     * 系统设置 sys-set.html
     * @return
     */
    @GetMapping("sysset")
    public String sysSet(){
        return "BkSys/sys-set";
    }

    /**
     * 系统屏蔽词 sys-shield.html
     * @return
     */
    @GetMapping("sysshield")
    public String sysShield(){
        return "BkSys/sys-shield";
    }

}
