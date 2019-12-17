
let  flag
/**
 * 查询全部
 */
function find_all(currentPage) {
    $.ajax("/admin/advertising/findAll",{
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        data:{'page':currentPage},
        success:(data)=>{
            $("#advertising").html("")
            if(data.code == 20000){
                joint(data.data)
            }
        }
    })
}


/**
 * 删除
 */
function del(advertisingId) {
    $.ajax("/admin/advertising/deleteAdvertising",{
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        data:{'advertisingId':advertisingId},
        success:(data)=>{
            if(data.code == 20000){
                find_all(1)
                alert(data.message)
            }else{
                alert(data.message)
            }
        }
    })
}

function showPopup(data,f) {
    if(f == 1){
        flag = f
        $.ajax("/admin/advertising/findAdvertisingById",{
            dataType: 'JSON',
            type: 'POST',
            timeout: 5000,
            data:{'advertisingId':data},
            success:(data)=>{
                $("input[name = 'advertisingId']").val(data.data.advertisingId)
                $("input[name = 'advertisingImg']").val(data.data.advertisingImg)
                $("input[name = 'advertisingAlt']").val(data.data.advertisingAlt)
                $("input[name = 'advertisingUrl']").val(data.data.advertisingUrl)
                $("input[name = 'advertisingLevel']").val(data.data.advertisingLevel)
                $("input[name = 'advertisingRemark']").val(data.data.advertisingRemark)
                $('.ui.modal.form')
                    .modal('show')
            }
        })
    }else{
        flag = 0
        $("input[name = 'advertisingId']").val("")
        $("input[name = 'advertisingImg']").val("")
        $("input[name = 'advertisingAlt']").val("")
        $("input[name = 'advertisingUrl']").val("")
        $("input[name = 'advertisingLevel']").val("")
        $("input[name = 'advertisingRemark']").val("")
        $('.ui.modal.form')
            .modal('show')
    }
}

/**
 * 提交
 */
function submit() {
    if(flag == 1){
        $.ajax({
            dataType: 'json',
            data: $("#form").serialize(),
            timeout: 5000,
            type: 'POST',
            url: "/admin/advertising/update",
            success: function (data) {
                if (data.code == 20000) {
                    find_all(1)
                    showAndHide(2)
                } else {
                    alert(data.message)
                }
            }
        })
    }else{
        /**
         * 新增
         */
        $.ajax("/admin/advertising/insert", {
            dataType: 'json',
            data: $("#form").serialize(),
            timeout: 5000,
            type: 'POST',
            success: (data) => {
                if (data.code == 20000) {
                    find_all(1)
                    showAndHide(2)
                } else {
                    alert(data.message)
                }
            }
        })
    }
}


/**
 * 数据拼接
 */
function joint(data) {
    data.result.forEach((item)=>{
        $("#advertising").append(`
                                <tr>
                                    <td>${item.advertisingId}</td>
                                    <td>${item.advertisingAlt}</td>
                                    <td>${item.advertisingUrl}</td>
                                    <td>${item.advertisingLevel}</td>
                                    <td>${item.advertisingRemark}</td>
                                    <td>
                                        <a onclick="showPopup(${item.advertisingId},1)"  class="ui mini teal basic button">编辑</a>
                                        <a onclick="showPopup(null,2)" class="ui mini teal basic button">新增</a>
                                        <a onclick="del(${item.advertisingId})" class="ui mini red basic button">删除</a>
                                        <a onclick="onto(${item.advertisingId})" class="ui mini red basic button">推上大广告位置</a>
                                    </td>
                                </tr> `)
    })
}

/**
 * 推上广告位值
 */
function onto(advertisingId) {
    $.ajax("/admin/advertising/onto",{
        dataType:'json',
        data:{'advertisingId':advertisingId},
        timeout:5000,
        type:'post',
        success:(data)=>{
            if(data.code == 20000){
                alert(data.message)
                find_all(1)
            }else {
                alert(data.message)
            }
        }
    })
}


