$(function () {
    aboutMe()
    user_hobby()
    find_all_label()
    find_all_hobby()
})


/**
 * 请求用户个人信息
 */
function aboutMe() {
    $.ajax("/admin/show_me",{
        dataType: 'json',
        timeout: 5000,
        type: 'get',
        success:(data)=>{
            $("#userSignature").text(data.data.userSignature)
            $("#userProfilePhoto").prop("src",data.data.userProfilePhoto)
            $("input[name = 'userNickname']").val(data.data.userNickname)
            $("input[name = 'userName']").val(data.data.userName)
            $("input[name = 'userEmail']").val(data.data.userEmail)
            $("input[name = 'userId']").val(data.data.userId)
            $("input[name = 'userTelephoneNumber']").val(data.data.userTelephoneNumber)
            $("input[name = 'userProfilePhoto']").val(data.data.userProfilePhoto)
            $("textarea[name = 'userSignature']").val(data.data.userSignature)

        }
    })
}


/**
 *  请求用户爱好
 */
function user_hobby() {
  let userId = sessionStorage.getItem("userId");
    $.ajax("/admin/hobby/find_user_hobby",{
        dataType: 'json',
        timeout: 5000,
        data:{'userId':userId},
        type: 'POST',
        success:(data)=>{
            $("#hobby").html("")
            joint(data)
        }
    })
}

/**
 * 拼接爱好
 * @param data
 */
function joint(data) {
    data.data.forEach((item)=>{
        $("#hobby").append(`
              <div class="ui orange basic left pointing label">${item.bobbyName}</div>
        `)
    })
}

/**
 * 查询全部的标签
 */
function find_all_label() {
    $.ajax("/admin/label/find_all_label",{
        dataType: 'json',
        timeout: 5000,
        type: 'get',
        success:(data)=>{
            jointLabel(data)
        }
    })
}

/**
 * 拼接标签
 * @param data
 */
function jointLabel(data) {
    data.data.result.forEach((item)=>{
        $("#label").before(`
             <div class="ui teal basic left pointing label m-margin-tb-tiny">${item.labelName}</div>
        `)
    })
}

/**
 * 用户修改
 */
function submit() {
    $.ajax("/admin/update",{
        dataType: 'json',
        timeout: 5000,
        type: 'POST',
        data:$("#form").serialize(),
        success:(data)=>{
           if(data.code == 20000){
               window.location.href = '/admin/me'
           }else {
               alert(data.message)
           }
        }
    })
}


function find_all_hobby() {
    $.ajax("/admin/hobby/find_all_hobby",{
        dataType: 'json',
        timeout: 5000,
        type: 'POST',
        success:(data)=>{
            jointHobby(data.data)
        }
    })
}

/**
 * 拼接的爱好
 * @param data
 */
function jointHobby(data) {
    data.forEach((item)=>{
        $("#hobby-list").append(`
                    <div class="ui checkbox" >
                        <input type="checkbox" name="userHobby" value="${item.hobbyId}">
                        <label data-value="Checkbox">${item.bobbyName}</label>
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        `)
    })
}