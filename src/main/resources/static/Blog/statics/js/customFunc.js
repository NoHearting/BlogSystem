var file_name = 'customFunc.js';

function getBlogComment(id,currentPage,pageMaxItems) {

}

function log(file,line,info) {
    var str = "["+file+" : "+line+"] "+info;
    console.log(str);
}

/**
 * 评论回复按钮的响应操作
 * @param id
 * @param userName
 */
function replyTheComment(id,userName) {
    $("#reply").attr("value",id);
    $("#comment").attr("placeholder","回复 "+userName).focus();



    // 定位到回复的输入框
    var height = $("#comment").offset().top;
    log("customFunc.js","21",$("#comment").offset().top);
    $body = (window.opera) ? (document.compatMode == "CSS1Compat" ? $('html') : $('body')) : $('html,body');
    $body.animate({scrollTop: height - 65}, 800);
}


/**
 * 初始化评论下方的回复按钮的响应操作
 * 初始化第一次的thymeleaf设置时的点击事件
 * 已无用，时间：2020年4月22日
 */
function addClickToReplyATags() {
    $(".comment-reply-link").click(function () {
        log("customFunc.js","32","开始注册点击事件。。。。");
        var cId = $(this).attr("data-cId");
        var userName = $(this).attr("data-userName");
        replyTheComment(cId,userName);
    })
}


/**
 * 更新评论下面的翻页按钮
 * 重新设置该按钮的样式和给按钮加上响应操作
 * @param pos
 * @param currentPage
 * @param totalPages
 */
function updateNavTheATags(pos,currentPage,totalPages) {
    console.log("开始更新！！！")
    var container = $("#comments-navi");
    var aTags = '';
    var url = '';
    var begin = 1;
    var end = 0;
    var currPage = currentPage;
    var totalPages = totalPages;
    console.log("总页面： "+totalPages);
    if(totalPages <= 4){
        begin = 1;
        end = totalPages	;
    }else{
        if(currPage==0||currPage-1<0){
            begin = 1;
            end = 4;
        }else if(currPage+2 >= totalPages){
            end = totalPages;
            begin = totalPages - 3;
        }else{
            begin = currPage - 1;
            end = currPage + 2;
        }
    }



    aTags += '<a  class="prev page-numbers" href="javascript:updateCommentsWithATag('+pos+','+(begin-1)+',10)" >< </a>'
    currPage = currPage < 1  ? 1 : currPage;  //页码小于1的情况将页码设置为1
    for(var i = begin;i<=end;i++){
        if(i == currPage){
            aTags += '<span aria-current="page" class="page-numbers current">'+i+'</span>';
        }else{
            aTags += '<a class="page-numbers" href="javascript:updateCommentsWithATag('+pos+','+i+',10)" >'+i+'</a>'
        }
    }
    container.empty();
    container.append(aTags);
}

/**
 * 更新博客下面的翻页按钮
 * @param s 关键字
 * @param currentPage  当前页码
 * @param totalPages  总的页面数
 */
function updateNavTheATagsForBlog(s,currentPage,totalPages) {
    console.log("开始更新！！！")
    var container = $("#comments-navi");
    var aTags = '';
    var url = '';
    var begin = 1;
    var end = 0;
    var currPage = currentPage;
    var totalPages = totalPages;
    console.log("总页面： "+totalPages);
    if(totalPages <= 4){
        begin = 1;
        end = totalPages;
    }else{
        if(currPage==0||currPage-1<0){
            begin = 1;
            end = 4;
        }else if(currPage+2 >= totalPages){
            end = totalPages;
            begin = totalPages - 3;
        }else{
            begin = currPage - 1;
            end = currPage + 2;
        }
    }



    aTags += '<a  class="prev page-numbers" href="javascript:updateBlogsWithATag('+s+','+(begin-1)+')" >< </a>'
    for(var i = begin;i<=end;i++){
        if(i == currPage){
            aTags += '<span aria-current="page" class="page-numbers current">'+i+'</span>';
        }else{
            aTags += '<a class="page-numbers" href="javascript:updateBlogsWithATag('+s+','+i+')" >'+i+'</a>'
        }
    }
    container.empty();
    container.append(aTags);
}





/**
 * 作为评论翻页按钮的响应操作
 * 获取评论并更新在页面上同时更新按钮标签
 * @param pos   pos>0,表示是某一个博客下的评论，否则为留言
 * @param currentPage  当前页码
 * @param pageMaxItems
 */
function updateCommentsWithATag(pos,currentPage,pageMaxItems) {
    $.ajax({
        url:'/getComments',
        data:{
            "pos":pos,
            "currentPage":currentPage,
            "pageMaxItems":pageMaxItems
        },
        dataType:"json",
        beforeSend:function () {
            $('#comments-navi').hide();
            $('ul.commentwrap').hide();
            $('#loading-comments').slideDown();
        },
        success:function (commentPage) {
            //更新评论条数
            $("#comment-counts").text(commentPage.totalItems);
            log("customFunc.js","updateCommentsWithATag",commentPage.totalItems);

            // 填充评论内容  totalItems
            var comment_list = $("#comments-list");
            comment_list.empty();
            for(var i = 0;i<commentPage.comments.length;i++){
                var li = '<li class="comment even thread-even depth-1" id="li-comment-">\n' +
                    '                        <div id="comment-969" class="comment_body contents">\n' +
                    '                            <div class="profile">\n' +
                    '                                <a href=""><img src="'+commentPage.comments[i].userHeader+'" class="gravatar" alt="'+commentPage.comments[i].userName+'"></a>\n' +
                    '                            </div>\n' +
                    '                            <div class="main shadow">\n' +
                    '                                <div class="commentinfo">\n' +
                    '                                    <section class="commeta">\n' +
                    '                                        <div class="shang">\n' +
                    '                                            <h4 class="author"><a href="" target="_blank"><img src="'+commentPage.comments[i].userHeader+'" class="gravatarsmall" alt="'+commentPage.comments[i].userName+'">'+commentPage.comments[i].userName+'</a></h4>\n' +
                    '                                        </div>\n' +
                    '                                    </section>\n' +
                    '                                </div>\n' +
                    '                                <div class="body">\n';
                log("customFunc.js","updateCommentsWithATag",commentPage.comments[i].reply);
                if(commentPage.comments[i].reply > -1){
                    li += '                                    <p  class="reply-comment">'+commentPage.comments[i].content+'</p>\n';
                }else{
                    li += '                                    <p>'+commentPage.comments[i].content+'</p>\n';
                }
                li +=
                    '                                </div>\n' +
                    '                                <div class="xia info">\n' +
                    '                                    <span><time datetime="'+commentPage.comments[i].writeTime+'">2018年3月9日</time></span>\n' +
                    '                                    <span><a rel=\'nofollow\' class=\'comment-reply-link\' href="javascript:void(0);" onclick="javascript:replyTheComment('+commentPage.comments[i].cId+',\''+commentPage.comments[i].userName+'\')" aria-label=\'回复给小布丁\'>回复</a></span>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </li>';
                comment_list.append(li);
            }

            updateNavTheATags(pos,currentPage,commentPage.totalPages);

            $('#loading-comments').slideUp("fast");
            $('#comments-navi').show();
            $('ul.commentwrap').show();

        }
    })
}

/**
 * 作为博客翻页按钮的响应操作
 * 获取博客并更新在页面上同时更新按钮标签
 * @param s 关键字
 * @param currentPage 当前页码
 */
function updateBlogsWithATag(s,currentPage){
    log("customFunc.js","updateBlogsWithATag","开始："+currentPage);
    $.post("/searchPage",{s:s,currentPage:currentPage},function (info) {
        var container = $("#search-blogs");
        container.empty();
        var obj = JSON.parse(info);
        var blogs = obj.blogPage.blogs;
        var div  = '';
        for(var i = 0;i<blogs.length;i++){
            div += '<div class="archive-post" >\n' +
                '\n' +
                '<div class="type">\n' +
                '<div class="mask"><i class="iconfont">&#xe603;</i></div>\n' +
                '</div>\n' +
                '<h2 class="archive-title" style="color: #">\n' +
                '<span>\n' +
                '<a href="/detailDefault/'+blogs[i].bId+'"  class="search-title" >'+blogs[i].title+'</a>\n' +
                '</span>\n' +
                '<div class="post-time" >'+blogs[i].writeTime+'</div>\n' +
                '</h2>\n' +
                '<div class="post-category">\n' +
                '<a href="/detailDefault/'+blogs[i].bId+'"  rel="category tag">Happen</a>\n' +
                '</div>\n' +
                '</div>';
        }
        container.append(div);
        updateNavTheATagsForBlog(s,obj.blogPage.totalPages);


    });
}

/**
 * 设置评论提交表单的操作,留言和评论都统一使用这个函数
 * 提交一次评论后会刷新所有评论
 * 刷新评论需要确定刷新的是【博客】下的评论还是【留言】下的评论
 * 根据info返回的值来确定刷新评论的位置
 */
function addActionForTheForm() {
    $("#commentForm").submit(function (data) {


        //进行表单数据合法性的验证
        if(!checkData()){
            log(file_name,"addActionForTheForm","Refuse Commit!");
            return;
        }
        $.post("/insertComment",$(this).serialize(data),function (info) {
            log("customFunc.js",160,info);
            var data = JSON.parse(info);
            log("customFunc.js","addActionForTheForm",data);
            if(data!=null){

                log("customFunc.js",164,(data.writePosition > 0));
                if(data.status == "success"){
                    // successCommit();  //显示提示信息  未完成
                    clearFormData();  //成功提交之后清除表单中的数据
                    if(data.writePosition >= 0) {
                        updateCommentsWithATag(data.writePosition, 1, 10);
                    }else{
                        updateCommentsWithATag(-1, 1, 10);
                    }
                }else{
                    // failedCommit("提交失败，未知错误");  //未完成
                }


            }else{
                // failedCommit("提交失败，未知错误");   //未完成
            }
        });
    });
}

/**
 * 检查提交的数据的合法性
 * @param data
 */
function checkData() {

    //检车昵称是否填写
    var nickname = $("#author").val();
    if(null == nickname || '' == nickname){
        $("#author").css("border","1px solid red");
        return false;
    }else{
        $("#author").css("border","1px solid #0ed145");
    }


    //验证邮箱合法性
    var email = $("#email").val();
    if(!isEmail(email)){  //不合法设置输入框边框为red
        $("#email").css("border","1px solid red");
        return false;
    }else{
        $("#email").css("border","1px solid #0ed145")
    }

    //验证网址是否合法
    var url = $("#url").val();
    if(!isURL(url)){
        $("#url").css("border","1px solid red");
        return false;
    }else{
        $("#url").css("border","1px solid #0ed145")
    }

    //检测评论是否有内容
    var comment = $("#comment").val();
    if(null == comment || "" == comment){
        $("#comment").css("border","1px solid red");
        return false;
    }else{
        $("#comment").css("border","1px solid #0ed145")
    }

    return true;
}

/**
 * 判断邮箱格式是否正确
 * @param str_email
 * @returns {boolean}
 */
function isEmail(str_email) {
    var reg = /^([a-zA-Z0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    var re = new RegExp(reg);
    if(re.test(str_email)){
        return true;
    }else{
        return false;
    }
}

/**
 * 判断网址的格式是否正确
 * @param str_url
 * @returns {boolean}
 */
function isURL(str_url){
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
        + "[a-z]{2,6})" // first level domain- .com or .museum
        + "(:[0-9]{1,4})?" // 端口- :80
        + "((/?)|" // a slash isn't required if there is no file name
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var re=new RegExp(strRegex);
    //re.test()
    if (re.test(str_url)){
        return (true);
    }else{
        return (false);
    }
}

/**
 * 添加评论成功之后清除表单中的数据
 */
function clearFormData() {
    $("#author").val("");
    $("#email").val("");
    $("#url").val("");
    $("#comment").val("");
}

/**
 * 成功提交评论信息
 */
function successCommit() {
    $("#msg-box").html("评论提交成功").show(300).delay(3000).hide(300);
}

/**
 * 提交评论失败
 */
function failedCommit(errorInfo) {
    $("#msg-box").html(errorInfo).show(300).delay(3000).hide(300);
}

/**
 * 给搜索页面的列表添加一个响应事件，使其能够正确的转到相应的页面显示博客
 * 此函数在搜索页面被调用
 */
function addIntoArticleOnclickEvent() {
    $(".search-title").click(function () {
        var thisObj = $(this);
        var id = thisObj.attr("data-bId");
        log("customFunc.js","addIntoArticleOnclickEvent",id);
        $.post("/detail",{"id":id,"currentPage":1,"pageMaxItems":10},function (info) {

        });
    })
}




function setIframeHeight(iframe) {
    if (iframe) {
        var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
        if (iframeWin.document.body) {
            iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
        }
    }
    console.log(iframe.height);
};




