$(function () {
    /**
     * 查询全部的数据
     */
    findAll(1)
})


let f;

/**
 * 获取全部的数据
 */
function findAll(curPage) {
    $.ajax("/admin/head/photo/findAll",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data:{'page':curPage},
        success:(data)=>{
            $("#head-photo").html("")
            $("#page").html("")
            if(data.code == 20000){
                page(data.data.curPage, data.data.totalPages, data.data.totalRows)
                joint(data.data.result)
            }
        }
    })
}

/**
 * 拼接
 */
function joint(result) {
    result.forEach((item)=>{
        $("#head-photo").append(`
             <tr>
                               
                                    <td><img src="${item.headUrl}" style="width: 50px;height: 40px"/></td>
                                     <td>${item.headType}</td>
                                    <td>${item.headDate}</td>
                                    <td>
                                        <a onclick="showPopup('${item.headId}',1)"  class="ui mini teal basic button">修改</a>
                                        <a onclick="del('${item.headId}')" class="ui mini red basic button">删除</a>
                                    </td>
        `)
    })
}

/**
 * 显示数据
 */
function showPopup(headId,flag) {
    if(flag == 1){
        f = flag
        $.ajax("/admin/head/photo/findOne",{
            dataType: "json",
            type: "POST",
            timeout: 5000,
            data:{'headId':headId},
            success:(data)=>{
                if(data.code == 20000){
                    $("input[name = 'headId']").val(data.data.headId)
                    $("input[name = 'headUrl']").val(data.data.headUrl)
                    $("input[name = 'headType']").val(data.data.headType)
                    $('.ui.modal.form')
                        .modal('show')
                }
            }
        })
    }else {
        f = 2
        $('.ui.modal.form')
            .modal('show')
    }
}


/**
 * 提交
 */
function submit() {
    if(f == 1){
        $.ajax("/admin/head/photo/update",{
            dataType: "json",
            type: "POST",
            timeout: 5000,
            data:$("#form").serialize(),
            success:(data)=>{
                if(data.code == 20000){
                    $("input[name = 'headUrl']").val("")
                    $("input[name = 'headId']").val("")
                    findAll(1)
                    showAndHide(2)
                }else {
                    $("input[name = 'headUrl']").val("")
                    $("input[name = 'headId']").val("")
                    alert(data.message)
                }
            }
        })
    }else {
        $.ajax("/admin/head/photo/insert",{
            dataType: "json",
            type: "POST",
            timeout: 5000,
            data:$("#form").serialize(),
            success:(data)=>{
                if(data.code == 20000){
                    $("input[name = 'headUrl']").val("")
                    findAll(1)
                    showAndHide(2)
                }else {
                    $("input[name = 'headUrl']").val("")
                    alert(data.message)
                }
            }
        })
    }
}

/**
 * 删除的
 */
function del(headId) {
    $.ajax("/admin/head/photo/delete",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data:{'headId':headId},
        success:(data)=>{
            if(data.code == 20000){
                findAll(1)
                alert(data.message)
            }else{
                alert(data.message)
            }
        }
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