$(function () {
    /**
     * 获取全部的数据
     */
    findAll(1)


})


/**
 * 获取全部的数据
 */
function findAll(curPage) {
    $.ajax("/admin/leave/message/findAll",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data:{'page':curPage},
        success:(data)=>{
            if(data.code == 20000){
                page(data.data.curPage, data.data.totalPages, data.data.totalRows)
                joint(data.data.result)
            }
        }
    })
}

/**
 * 拼接数据
 * @param data
 */
function joint(result) {
    $("#leave-message").html("")
    result.forEach((item)=>{
        $("#leave-message").append(`
             <tr>
                                    <td>${item.messageNickname}</td>
                                    <td><img src="${item.messageHeadPortrait}" style="width: 50px;height: 40px"/></td>
                                    <td>${item.messageContent}</td>
                                    <td>${item.messageDate}</td>
                                    <td>${item.messageIp}</td>
                                    <td>${item.messageEmail}</td>
                                    <td>${item.messagemessageParentIdId}</td>
                                    <td>
                                        <a onclick="reply('${item.messageId}')"  class="ui mini teal basic button">回复</a>
                                        <a onclick="del('${item.messageId}')" class="ui mini red basic button">删除</a>
                                    </td>
                                </tr>
        `)
    })
}

/**
 * 下一页
 */

function next_page(page,totalPages) {
    page = ++page
    if(page <= totalPages){
        findAll(page)
    }
}
/**
 * 上一页
 */
function up_page(page) {
    page = --page
    if(page >= 1){
        findAll(page)
    }
}


/**
 *回复
 */
function reply(messageId) {
    $("input[name = 'messageParentId']").val(messageId)
    $('.ui.modal.form')
        .modal('show')
}

/**
 * 删除
 */
function del(messageId) {
    $.ajax("/admin/leave/message/delete",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data:{'messageId':messageId},
        success:(data)=>{
            if(data.code == 20000){
                alert(data.message)
            }else {
                alert(data.message)
            }
        }
    })
}

/**
 * 提交数据
 */
function submit() {
    if(verify()){
        $.ajax("/admin/leave/message/insert",{
            dataType: "json",
            type: "POST",
            timeout: 5000,
            data:$("#form").serialize(),
            success:(data)=>{
                if(data.code == 20000){
                    alert(data.message)
                    showAndHide(2)
                    $("textarea[name = 'messageContent']").val("")
                }else {
                    alert(data.message)
                }
            }
        })
    }
}

/**
 * 校验
 */
function verify() {
    let  content= $("textarea[name = 'messageContent']").val()
    console.log(content);
      if(content !=null && content.length > 0){
          return true
      }
      alert("对不起,请填写内容在提交回复~~~~^_^")
      return false
}