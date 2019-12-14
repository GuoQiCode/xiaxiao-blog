/**
 *
 * @type {null}
 */
let flag = 0

/**
 * 分页展示
 * @param currentPage
 */
function findAll(currentPage) {
    $.ajax("/admin/sort/find_all?page=" + currentPage, {
        async: false,
        dataType: "json",
        type: "get",
        timeout: 5000,
        success: (data) => {
            if(data.code == 20000){
                totalRows = data.data.totalRows
                totalPage = data.data.totalPages
                pageSize = data.data.pageSize
                $("#sorts").html("")
                Splicing(data)
            }
        }
    })
}

/**
 * 字符串拼接
 * @param data
 * @constructor
 */
function Splicing(data) {
    data.data.result.forEach((item) => {
        $("#sorts").append(`
                                <tr>
                                    <td>${item.sortId}</td>
                                    <td>${item.sortName}</td>
                                    <td>${item.sortAlias}</td>
                                    <td>${item.sortDescription}</td>
                                    <td>
                                        <a onclick="showPopup(${item.sortId},1)"  class="ui mini teal basic button">编辑</a>
                                        <a onclick="del(${item.sortId})" class="ui mini red basic button">删除</a>
                                        <a onclick="showPopup(null,2)" class="ui mini teal basic button">新增</a>
                                    </td>
                                </tr> `)
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
        $.ajax("/admin/sort/find_sort_by_id?sortId=" + data, {
            dataType: 'JSON',
            type: "GET",
            timeout: 5000,
            success: (data) => {
                $("#sortId").val(data.data.sortId)
                $("#sortName").val(data.data.sortName)
                $("#sortAlias").val(data.data.sortAlias)
                $("#sortDescription").val(data.data.sortDescription)
                $('.ui.modal.form')
                    .modal('show')
            }
        })
    } else {
        flag = 2
        $("#sortId").val("")
        $("#sortName").val("")
        $("#sortAlias").val("")
        $("#sortDescription").val("")
        $('.ui.modal.form')
            .modal('show')
    }
}

/**
 * 删除
 * @param sortId
 */
function del(sortId) {
    $.ajax("/admin/sort/delete?sortId=" + sortId, {
        dataType: "JSON",
        type: "GET",
        timeout: 5000,
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


/**
 * 表单的提交
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
            url: "/admin/sort/update",
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
        $.ajax("/admin/sort/insert", {
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





