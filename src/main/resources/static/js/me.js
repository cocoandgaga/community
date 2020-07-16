//添加评论
function post() {
    const questionId = $("#question_id").val();
    const content = $("#comment_content").val();
    comment2target(questionId,1,content);
}
//添加回复
function comment(e) {
    var commentId=e.getAttribute("data-id");
    var content=$("#input-" + commentId).val();
    comment2target(commentId,2,content);
}



/**
 * 点击 展开/折叠二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        //子元素不止一个则展开
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                //reverse 使得原先降序排列 拼接的时候降序失效 此时reverse反转回去
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}






//post评论或回复的数据 并且成功就更新页面
function comment2target(targetId,type,content) {
    if(!content){
        alert("不能回复该内容..");
        return ;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId":targetId,
            "content":content,
            "type":type
        }),
        success: [function (response) {
            if (response.code == 200) {
                // $("#comment_section").hide();
                window.location.reload();
            } else {
                if (response.code = 2004) {
                    //confirm 显示带有一段消息以及确认按钮和取消按钮的对话框。
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        if (response.code == 2004) {
                            // open() 方法用于打开一个新的浏览器窗口或查找一个已命名的窗口
                            window.open("https://gitee.com/oauth/authorize?client_id=b5c4d592d825388caa8b6b47a1501c0f875cf2182c5fe6463bd54faa14e047b0&redirect_uri=http://localhost:8888/callback&response_type=code&state=1");

                            //在浏览器中存储 k-v对。没有过期时间。
                            window.localStorage.setItem("closable", true);
                        }
                    } else {
                        alert(response.message);
                    }
                }
            }
        }]
    });
}


function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}