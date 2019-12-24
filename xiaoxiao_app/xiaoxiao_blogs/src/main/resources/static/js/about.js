/**
 * 前端页面展示我的个人信息
 */
$(function () {
    /**
     * 展示我的个人信息
     */
    show_me()

    /**
     * 展示我的爱好
     */
    find_me_hobby()


    /**
     * 展示全部的标签
     */
    find_all_label()


    /**
     * 获取公告
     */
    findAllNotice()

})

/**
 * 展示我的个人信息
 */
function show_me() {
    $.ajax("/frontline/users/show_me",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
            $("#userNickname").text(data.data.userNickname)
            $("#userSignature").text(data.data.userSignature)
            $("img[name = 'userProfilePhoto']").prop("src",data.data.userProfilePhoto)
        }
    })
}

/**
 * 查找我的爱好
 */
function find_me_hobby() {
    $.ajax("/frontline/hobby/find_me_hobby",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
           data.data.forEach((item)=>{
               $("#hobby").append(`
                 <div class="ui orange basic left pointing label">${item.bobbyName}</div>
            `)
           })
        }
    })
}


/**
 * 查询全部标签
 */
function find_all_label() {
    $.ajax("/frontline/label/find_all_label",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
            data.data.forEach((item)=>{
                $("#label").append(`
                       <div class="ui teal basic left pointing label m-margin-tb-tiny">${item.labelName}</div>
                `)
            })
        }
    })
}


/**
 * 获取公告信息
 */
function findAllNotice() {
    $.ajax("/frontline/notice/findAllNotice",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
            if(data.code == 20000){
                $("#notice").html("")
                jointNotice(data.data.result)
            }
        }
    })
}

/**
 * 拼接串
 */
function jointNotice(result) {
    result.forEach((item)=>{
        $("#notice").append(`
               <a class="item">
            <div class="line"></div>
            <div class="dot active"></div>
            <div class="box">
                <div class="cbox" style="left: 0px;">
                    <div class="date">${item.date}</div>
                    <div class="title">${item.title}</div>
                    <div class="types">
                        <p>${item.content}</p>
                    </div>
                </div>
            </div>
        </a>
        <div class="activeLine"></div>
            `)
    })
}