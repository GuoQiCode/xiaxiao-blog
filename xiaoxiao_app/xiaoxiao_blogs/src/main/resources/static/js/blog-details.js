$(function () {

    /**
     * 获取我的个人信息
     */
    show_me()

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
             $("img[name = 'userProfilePhoto']").prop("src",data.data.userProfilePhoto)
        }
    })
}