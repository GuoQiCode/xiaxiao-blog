/**
 * 留言提交
 */
function submit() {
    if(verifyEmail() && verifyContent() && verifyName()){
        $.ajax("/frontline/leave/message/insert",{
            dataType: 'JSON',
            type: 'POST',
            data:$("#from").serialize(),
            timeout: 5000,
            success:(data)=>{
                if(data.code == 20000){
                    alert(data.message)
                    $("#confirm").remove()
                    $("input[name = 'messageNickname']").val("")
                    $("input[name = 'messageEmail']").val("")
                    $("input[name = 'messageContent']").html("")
                    find_all_leave_message(1)
                }else{
                    alert(data.message)
                }
            }
        })
    }else {
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
    $.ajax("/frontline/leave/message/findAllLeaveMessage",{
        dataType: 'JSON',
        type: 'POST',
        data:{'page':currentPage},
        timeout: 5000,
        success:(data)=>{
            if(data.code == 20000){
                sessionStorage.setItem("totalRows",data.data.totalRows)
                sessionStorage.setItem("totalPage",data.data.totalPages)
                sessionStorage.setItem("pageSize",data.data.pageSize)
                split(data.data.result)
            }else{
                $("#comments").html("")
            }
        }
    })
}

/**
 * 拼接字符串
 * @param result
 */
function split(result) {
    $("#comments").html("")
    result.forEach((item)=>{
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
    if(messageContent.length > 255){
        $("input[name = 'messageContent']").after(`
             <div class="ui pointing left label" id="messageContent">
                    对不起名字不符合规则，留言不能超过255个字
                </div>
        `)
        return false
    }else
    {
        $("div.ui.pointing.left.label").remove()
        return true
    }

}
function verifyName() {
        const name = /[\u4e00-\u9fa5]+/
        let messageNickname =  $("input[name = 'messageNickname']").val()
        if(!(name.test(messageNickname)) || messageNickname =="" || messageNickname.length > 10){
            $("input[name = 'messageNickname']").after(`
                 <div class="ui pointing left label" id="name">
                    对不起名字不符合规则，只允许是汉字呢~~~而且不能超过10个字哦~
                </div>
            `)
            return false
        }else {
            $("div.ui.pointing.left.label").remove()
            return true
        }
}


/**
 * 校验邮箱
 */
function verifyEmail() {
    const email =  /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
    let messageEmail = $("input[name = 'messageEmail']").val()
    if(!(email.test(messageEmail))){
        $("input[name = 'messageEmail']").after(`
                 <div class="ui pointing left label" id="email">
                    对不起你的邮箱不对哦~~~
                </div>
            `)
        return false
    }else {
        $("div.ui.pointing.left.label").remove()
        return true
    }
}