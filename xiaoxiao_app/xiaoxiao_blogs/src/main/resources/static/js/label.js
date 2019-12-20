$(function () {
    /**
     * 获取所有的标签
     */
    findAll(1)

})


/**
 * 查询全部的标签
 */
let flag = 0
function findAll(currentPage) {
    $.ajax("/admin/label/find_all_label?page="+currentPage,{
        dataType:"json",
        type:'get',
        timeout:5000,
        success:(data) =>{
            if(data.code == 20000){
                page(data.data.curPage, data.data.totalPages,data.data.totalRows)
                $("#labels").empty()
                Splicing(data)
            }
        }
    })
}

/**
 * 字符拼接
 * @constructor
 */
function Splicing(data) {
    let result =data.data.result
    result.forEach((item) => {
        $("#labels").append(
            `
                                <tr>
                                    <td>${item.labelId}</td>
                                    <td>${item.labelName}</td>
                                    <td>${item.labelAlias}</td>
                                    <td>${item.labelDescription}</td>
                                    <td>
                                        <a onclick="showPopup(${item.labelId},1)"  class="ui mini teal basic button">编辑</a>
                                          <a onclick="showPopup(null,2)" class="ui mini teal basic button">新增</a>
                                        <a onclick="del(${item.labelId})" class="ui mini red basic button">删除</a>
                                       
                                    </td>
                                </tr> `
        )
    })
}



/**
 * 显示弹窗
 * @param data
 * @param flag
 */
function showPopup(data, f) {
    /**
     * 修改弹窗
     */
    if (f == 1) {
        flag = f
        $.ajax("/admin/label/find_label_by_id?labelId=" + data, {
            dataType: 'JSON',
            type: "GET",
            timeout: 5000,
            success: (data) => {
                $("#labelId").val(data.data.labelId)
                $("#labelName").val(data.data.labelName)
                $("#labelAlias").val(data.data.labelAlias)
                $("#labelDescription").val(data.data.labelDescription)
                $('.ui.modal.form')
                    .modal('show')
            }
        })
    } else {
        flag = 2
        $("#labelId").val("")
        $("#labelName").val("")
        $("#labelAlias").val("")
        $("#labelDescription").val("")
        $('.ui.modal.form')
            .modal('show')
    }
}

/**
 * 删除
 * @param labelId
 */
function del(labelId) {
    $.ajax("/admin/label/delete?labelId="+labelId,{
        dataType: 'JSON',
        type: "GET",
        timeout: 5000,
        success:(data) =>{
            if (data.code == 20000) {
                findAll(1)
                showAndHide(2)
            } else {
                alert(data.message)
            }
        }
    })
}


/**
 * 提交
 */
function submit() {
    if (flag == 1) {
        /**
         * 修改
         */
        $.ajax({
            dataType: 'json',
            data: $("#form").serialize(),
            timeout: 5000,
            type: 'POST',
            url: "/admin/label/update",
            success: function (data) {
                if (data.code == 20000) {
                    findAll(1)
                    showAndHide(2)
                } else {
                    alert(data.message)
                }
            }
        })
    } else {
        /**
         * 新增
         */
        $.ajax("/admin/label/insert", {
            dataType: 'json',
            data: $("#form").serialize(),
            timeout: 5000,
            type: 'POST',
            success: (data) => {
                if (data.code == 20000) {
                    findAll(1)
                    showAndHide(2)
                } else {
                    alert(data.message)
                }
            }
        })
    }
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
