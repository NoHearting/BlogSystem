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
            begin = totalPages - 4;
        }else{
            begin = currPage - 1;
            end = currPage + 2;
        }
    }



    aTags += '<a  class="prev page-numbers" href="javascript:updateCommentsWithATag('+pos+','+(begin-1)+',10)" >< </a>'
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
 * 作为评论翻页按钮的响应操作
 * 获取评论并更新在页面上同时更新按钮标签
 * @param pos
 * @param currentPage
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
                    '                                <div class="body">\n' +
                    '                                    <p>'+commentPage.comments[i].content+'</p>\n' +
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
 * 设置评论提交表单的操作,留言和评论都统一使用这个函数
 * 提交一次评论后会刷新所有评论
 * 刷新评论需要确定刷新的是【博客】下的评论还是【留言】下的评论
 * 根据info返回的值来确定刷新评论的位置
 */
function addActionForTheForm() {
    $("#commentForm").submit(function (data) {
        $.post("/insertComment",$(this).serialize(data),function (info) {
            log("customFunc.js",160,info);
            var data = JSON.parse(info);
            log("customFunc.js","addActionForTheForm",data);
            if(data!=null){

                log("customFunc.js",164,(data.writePosition > 0));
                if(data.status == "success"){
                    if(data.writePosition >= 0) {
                        updateCommentsWithATag(data.writePosition, 1, 10);
                    }else{
                        updateCommentsWithATag(-1, 1, 10);
                    }
                }


            }
        });
    });
}

