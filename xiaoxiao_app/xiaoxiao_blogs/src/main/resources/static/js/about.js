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

