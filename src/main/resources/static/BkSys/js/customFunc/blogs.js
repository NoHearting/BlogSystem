//blogs 页面
$(function () {
    $("#delete-all").click(function () {
        $("input[type='checkbox']").prop("checked",this.checked);
    });

    registerDeleteCheckboxClick();
    registerDeleteButtonsClick();
});

/**
 * 注册点击事件，为删除博客按钮
 */
function registerDeleteCheckboxClick() {
    $(".delete-blog").click(function () {
        if(confirm("确定删除此博客？")){
            var bId = $(this).attr("data-delete");
            console.log(bId);
            // var obj = $(this).parents("tr");
            $.post("/admin/deleteBlog",{bId:bId},function (info) {
                console.log(info);
                if(info!=null && info != "" && info != "undefined"){
                    if(info.status == 1){
                        alert("删除成功");
                        // obj.remove();
                        var index = $("ul a[class='active']").text();  //删除之后更新博客列表
                        updateBlogsWithPage(index);
                    }else{
                        alert("删除失败"+info.info);
                    }

                }else{
                    alert("删除失败");
                }
            });
        }


    });
}

function registerDeleteButtonsClick() {
    $("#delete-all-button").click(function () {
        if(confirm("确定删除选中的博客吗？")){
            var checkboxs = $("tbody input[type='checkbox']");
            console.log(checkboxs.length);
            var bIds = '';
            for(var i = 0;i < checkboxs.length;i++){
                if($(checkboxs[i]).is(":checked")){
                   bIds += $(checkboxs[i]).val()+" ";

                }
            }
            console.log(bIds);
            $.post("/admin/deleteBlogs",{bIds:bIds},function (response) {
                alert(response.info);
                var index = $("ul a[class='active']").text();  //删除之后更新博客列表
                updateBlogsWithPage(index);
            })
        }
    })

}

/**
 * 根据当前页码更新博客
 * @param currentPage
 * @param totalPages
 */
function updateNavForPage(currentPage,totalPages) {
    console.log("开始更新！！！")
    var container = $("#blogs-nav");
    var aTags = '';
    var url = '';
    var begin = 1;
    var end = 0;
    var currPage = currentPage;
    console.log("总页面： "+totalPages);
    if(totalPages <= 6){
        begin = 1;
        end = totalPages;
    }else{
        if(currPage==0||currPage-1<0){
            begin = 1;
            end = 6;
        }else if(currPage+2 >= totalPages){
            end = totalPages;
            begin = totalPages - 3;
        }else{
            begin = currPage - 1;
            end = currPage + 2;
        }
    }

    var prePage = (currPage-1) <= 1 ? 1 : (currPage-1);
    var after = (currPage + 1) >= totalPages ? totalPages : (currPage + 1);

    aTags += '<li><a href="javascript:updateBlogsWithPage('+prePage+')" >« </a></li>';
    for(var i = begin;i<=end;i++){
        if(i == currPage){
            aTags += ' <li><a class="active" href="javascript:void(0);">'+i+'</a></li>';
        }else{
            aTags += '<li><a href="javascript:updateBlogsWithPage('+i+')" >'+i+'</a></li>';
        }
    }
    aTags += '<li><a href="javascript:updateBlogsWithPage('+after+')" >» </a></li>';
    container.empty();
    container.append(aTags);
}


/**
 * 更新博客列表，根据当前页面
 * @param currPage
 */
function updateBlogsWithPage(currPage){
    $.get("/admin/updateBlogListBk",{currentPage:currPage},function (blogPage) {
        //更新评论条数
        $("#blog-counts").html("共"+blogPage.totalItems+"条");
        //清除全选checkbox的状态
        $("#delete-all").prop("checked",false);

        // 填充评论内容  totalItems
        var blog_table = $("#table-content");
        blog_table.empty();
        for(var i = 0;i<blogPage.blogs.length;i++){
            var blog = blogPage.blogs[i];
            var li = '<tr>\n' +
                '                                <td>\n' +
                '                                    <input type="checkbox" value="'+blog.bId+'">\n' +
                '                                </td>\n' +
                '                                <td>'+((blogPage.currentPage-1) * blogPage.pageMaxItems+i+1)+'</td>\n' +
                '                                <td>'+blog.title+'</td>\n' +
                '                                <td>'+blog.tag.label+'</td>\n' +
                '                                <td>'+blog.writeTime.substring(0,10)+'</td>\n' +
                '                                <td>'+blog.readTimes+'</td>\n' +
                '                                <td>\n' +
                '                                    <a href="#" data-delete="'+blog.bId+'" class="delete-blog" title="删除"><i class="mdi mdi-delete-forever text-success"></i></a>\n' +
                '                                    <a href="#"  title="编辑"><i class="mdi mdi-lead-pencil menu-icon text-success"></i></a>\n' +
                '                                </td>\n' +
                '                            </tr>';
            blog_table.append(li);
        }

        registerDeleteCheckboxClick();
        updateNavForPage(blogPage.currentPage,blogPage.totalPages);
    });

}


function jumpPage(url) {
    window.location.href = ""+url;
}

