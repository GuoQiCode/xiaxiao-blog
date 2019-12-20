$(function () {
    /**
     * 请求留言个数
     */
    getLeaveMessageSum()

    /**
     *
     */
    find_all_leave_message(1)

})


/**
 * 获取留言个数
 */
function getLeaveMessageSum() {
    $.ajax("/frontline/leave/message/getLeaveMessageSum", {
        dataType: 'JSON',
        type: 'POST',
        data: $("#from").serialize(),
        timeout: 5000,
        success: (data) => {
            $("#message-sum").text(data.data.sum)
        }
    })
}


/**
 * 留言提交
 */
function submit() {
    if (verifyEmail() && verifyContent() && verifyName()) {
        $.ajax("/frontline/leave/message/insert", {
            dataType: 'JSON',
            type: 'POST',
            data: $("#from").serialize(),
            timeout: 5000,
            success: (data) => {
                if (data.code == 20000) {
                    alert(data.message)
                    $("#confirm").remove()
                    $("input[name = 'messageNickname']").val("")
                    $("input[name = 'messageEmail']").val("")
                    $("input[name = 'messageContent']").html("")
                    find_all_leave_message(1)
                } else {
                    alert(data.message)
                }
            }
        })
    } else {
        $("#submit").after(`
             <div class="ui pointing left label" id="confirm">
                    对不起不符合规则，留言不能超过255个字或者为空~~~
                </div>
        `)
    }
}

/**
 * 查找全部的留言信息
 */
function find_all_leave_message(currentPage) {

    $.ajax("/frontline/leave/message/findAllLeaveMessage", {
        dataType: 'JSON',
        type: 'POST',
        data: {'page': currentPage},
        timeout: 5000,
        async: false,
        success: (data) => {
            if (data.code == 20000) {
                split(data.data.result)
                page(data.data.curPage, data.data.totalPages, data.data.totalRows)
            } else {
                $("#comments").html("")
            }
        }
    })

}

/**
 * 拼接的分页
 * @param curPage
 * @param totalPages
 * @param totalRows
 */
function page(curPage, totalPages, totalRows) {
    $("#page").html("")
    $("#page").append(`
           <div>
                    <div style="float: left">
                        <div class="ui inverted blue button" id="up_page" onclick="up_page(${curPage})">上一页</div>

                        <div class="ui inverted blue button" id="next_page" onclick="next_page(${curPage},${totalPages})">下一页</div>
                    </div>
                    <div class="ui label" style="margin-left: 15px;height: 34.8px;text-align: center">
                        <span class="item">
                            <i class="icon mail"></i> 共${totalRows}条数据
                            <div class="floating ui red label">22</div>
                        </span>
                    </div>
                </div>
    `)
}


/**
 * 下一页
 */

function next_page(page, totalPages) {
    page = ++page
    if (page <= totalPages) {
        find_all_leave_message(page)
    } else {
        alert("当前已经是最后一页啦~~~")
    }
}

/**
 * 上一页
 */
function up_page(page) {
    page = --page
    if(page >= 1){
        find_all_leave_message(page)
    }else{
        alert("已经是第一页了哦~~~")
    }
}


/**
 * 拼接字符串
 * @param result
 */
function split(result) {
    $("#comments").html("")
    result.forEach((item) => {
        $("#comments").append(`
             <div class="comment">
                    <a class="avatar">
                        <img src="${item.messageHeadPortrait}">
                    </a>
                    <div class="content">
                        <a class="author">${item.messageNickname}</a>
                        <div class="metadata">
                            <div class="date">${item.messageDate}</div>
                        </div>
                        <div class="text">
                            <p>${item.messageContent}</p>
                        </div>
                    </div>
                </div>
                <hr/>
                <br/>
        `)
    })
}


/**
 * 验证
 */
function verifyContent() {
    let messageContent = $("input[name = 'messageContent']").text()
    if (messageContent.length > 255) {
        $("input[name = 'messageContent']").after(`
             <div class="ui pointing left label" id="messageContent">
                    对不起名字不符合规则，留言不能超过255个字
                </div>
        `)
        return false
    } else {
        $("div.ui.pointing.left.label").remove()
        return true
    }

}

function verifyName() {
    const name = /[\u4e00-\u9fa5]+/
    let messageNickname = $("input[name = 'messageNickname']").val()
    if (!(name.test(messageNickname)) || messageNickname == "" || messageNickname.length > 10) {
        $("input[name = 'messageNickname']").after(`
                 <div class="ui pointing left label" id="name">
                    对不起名字不符合规则，只允许是汉字呢~~~而且不能超过10个字哦~
                </div>
            `)
        return false
    } else {
        $("div.ui.pointing.left.label").remove()
        return true
    }
}


/**
 * 校验邮箱
 */
function verifyEmail() {
    const email = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
    let messageEmail = $("input[name = 'messageEmail']").val()
    if (!(email.test(messageEmail))) {
        $("input[name = 'messageEmail']").after(`
                 <div class="ui pointing left label" id="email">
                    对不起你的邮箱不对哦~~~
                </div>
            `)
        return false
    } else {
        $("div.ui.pointing.left.label").remove()
        return true
    }
}