/**
 * 查询全部后台管理信息
 * @param currentPage
 */
function find_manager_all(currentPage) {
    $.ajax("/admin/message/find_manager_all", {
        dataType: "JSON",
        type: "POST",
        timeout: 5000,
        success: (data) => {
            totalRows = data.data.totalRows
            totalPage = data.data.totalPages
            pageSize = data.data.pageSize
            $("#admin-manager-list").html("");
            Splicing(data)
        }
    })
}



/**
 * 字符拼接
 * @constructor
 */
function Splicing(data) {
    let result = data.data.result
    result.forEach((item) => {
        $("#admin-manager-list").append(
            `
                                <tr>
                                    <td>${item.adminId}</td>
                                    <td>${item.adminName}</td>
                                    <td>${item.adminUrl}</td>
                                    <td>
                                        <a onclick="showAndHide(1)" class="ui mini teal basic button">新增</a>
                                        <a onclick="deleteAdminManager('${item.adminId}')" class="ui mini red basic button">删除</a>
                                    </td>
                                </tr> `
        )
    })
}


/**
 * 删除
 * @param adminId
 */
function deleteAdminManager(adminId) {
    $.ajax("/admin/message/delete", {
        dataType: "JSON",
        type: "POST",
        data:{'adminId':adminId},
        timeout: 5000,
        success: (data) => {
            if (data.code == 20000) {
                find_manager_all(1)
            } else {
                alert(data.message)
            }
        }
    })
}



/**
 *
 */
function submit() {
    $.ajax({
        dataType: 'json',
        data: $("#form").serialize(),
        type: "post",
        timeout: 5000,
        url: "/admin/message/insert",
        success: (data) => {
            if (data.code == 20000) {
                find_manager_all(1)
                showAndHide(2)
            } else {
                alert(data.message)
            }
        }
    })
}