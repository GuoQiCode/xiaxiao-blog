$(function () {
    /**
     * 获取全部
     */
    findAll()
})

let flag;

/**
 * 显示数据的
 */
function showPopup(f,data){
    if(f == 1){
        flag = 1
        $.ajax("/admin/notice/findOne",{
            dataType: "json",
            type: "POST",
            timeout: 5000,
            data:{'id':data},
            success:(data)=>{
                if(data.code == 20000){
                    $("input[name = 'id']").val(data.data.id)
                    $("input[name = 'title']").val(data.data.title)
                    $("textarea[name = 'content']").val(data.data.content)
                    $("input[name = 'date']").val(data.data.date)
                    $("input[name = 'type']").val(data.data.type)
                    $("input[name = 'author']").val(data.data.author)
                    $('.ui.modal.form')
                        .modal('show')
                }
            }
        })
    }else {
        flag = 2
        $('.ui.modal.form')
            .modal('show')
    }
}


/**
 * 提交
 */
function submit() {
    if(flag == 1){
        $.ajax("/admin/notice/update",{
            dataType: "json",
            type: "POST",
            timeout: 5000,
            data:$("#form").serialize(),
            success:(data)=>{
                if(data.code == 20000){
                    $("input[name = 'id']").val("")
                    $("input[name = 'title']").val("")
                    $("textarea[name = 'content']").val("")
                    $("input[name = 'date']").val("")
                    $("input[name = 'type']").val("")
                    $("input[name = 'author']").val("")
                    findAll(1)
                    showAndHide(2)
                }
            }
        })
    }else {
        $.ajax("/admin/notice/insert",{
            dataType: "json",
            type: "POST",
            timeout: 5000,
            data:$("#form").serialize(),
            success:(data)=>{
                if(data.code == 20000){
                    findAll(1)
                    showAndHide(2)
                }
            }
        })
    }
}

/**
 *
 * @param id
 */
function del(id) {
    $.ajax("/admin/notice/delete",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data:{'id':id},
        success:(data)=>{
            if(data.code == 20000){
                findAll(1)
                alert(data.message)
            }else {
                alert(data.message)
            }
        }
    })
}

/**
 * 获取全部的数据
 */
function findAll(curPage) {
    $.ajax("/admin/notice/findAll",{
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
 * 拼接
 */
function joint(result) {
    $("#notice").html("")
        result.forEach((item)=>{
            let type
            if(item.type == '1'){
                type = "以启用"
            }else {
                type = "公告关闭"
            }
            $("#notice").append(`
                                <tr>
                                    <td>${item.title}</td>
                                    <td>${item.content}</td>
                                    <td>${item.date}</td>
                                    <td>${type}</td>
                                    <td>${item.author}</td>
                                    <td>
                                        <a onclick="showPopup(1,'${item.id}')"  class="ui mini teal basic button">修改</a>
                                        <a onclick="del('${item.id}')" class="ui mini red basic button">删除</a>
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