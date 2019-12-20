$(function () {

    /**
     * 获取我的个人信息
     */
    show_me()

    /**
     * 请求信息展示
     */
    find_comments(1,window.location.href.split("/")[4])
})


/**
 * 展示我的个人信息
 */
function show_me() {
    $.ajax("/frontline/users/show_me", {
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success: (data) => {
            $("#userNickname").text(data.data.userNickname)
            $("img[name = 'userProfilePhoto']").prop("src", data.data.userProfilePhoto)
        }
    })
}

/**
 * 请求评论信息
 */
function find_comments(currentPage,articleId) {
    $.ajax("/frontline/comments/findComments", {
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data: {'articleId':articleId,'page':currentPage},
        success: (data) => {
            if(data.code == 20000){
                $("#comments").html("")
                page(data.data.curPage, data.data.totalPages, data.data.totalRows)
                splict(data)
            }
        }
    })
}

/**
 * 评论拼接
 * @param data
 */
function splict(data) {
    data.data.result.forEach((item) => {
        if (item.list.length > 0) {
            html(item)
            let commentId = item.commentId
            let nickname = item.nickname
            item.list.forEach((item) => {
                child(commentId, item,nickname)
            })
        } else {
            html(item)
        }
    })
}


/**
 * 拼接父评论
 * @param item
 */
function html(item) {
    $("#comments").append(`
                <div class="comment" id="${item.commentId}">
                        <a class="avatar">
                            <i class=' spy icon'></i>
                        </a>
                        <div class="content">
                            <a class="author">${item.nickname}</a>
                            <div class="metadata">
                                <span class="date">${item.commentDate}</span>
                            </div>
                            <div class="text">
                                ${item.commentContent}
                            </div>
                            <div class="actions">
                                <a class="reply" data-commentid="${item.commentId}" data-commentnickname="${item.nickname}"  onclick="reply(this)">回复</a>
                            </div>
                        </div>
                    </div>
            `)
}

/**
 * 拼接子品论
 */
function child(commentId, item) {
    $("#" + commentId).append(`
                              <div class="comments" id="${item.commentId}">
                            <div class="comment">
                                <a class="avatar">
                                    <i class=' spy icon'></i>
                                </a>
                                <div class="content">
                                    <a class="author">${item.nickname}</a>
                                    <div class="metadata">
                                        <span class="date">${item.commentDate} &nbsp;&nbsp;&nbsp;&nbsp;<span>回复</span>&nbsp;&nbsp;&nbsp;&nbsp;${item.commentsNickname}</span>
                                    </div>
                                    <div class="text">
                                        ${item.commentContent}
                                    </div>
                                    <div class="actions">
                                     <a class="reply" data-commentid="${commentId}" data-commentnickname="${item.nickname}"  onclick="reply(this)">回复</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                `)
}

/**
 * 下一页
 */

function next_page(page,totalPages) {
    page = ++page
    if(page <= totalPages){
        find_comments(page,window.location.href.split("/")[4])
    }else {
        alert(11)
    }
}
/**
 * 上一页
 */
function up_page(page) {
    page = --page
    if(page >= 1){
        find_comments(page,window.location.href.split("/")[4])
    }else {
        alert(11)
    }
}