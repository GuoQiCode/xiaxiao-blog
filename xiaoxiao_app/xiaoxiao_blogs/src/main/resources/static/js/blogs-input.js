/**
 * 获取全部的文章分类
 */

let flag_sort = 1
let flag_label = 1

function findAllSort() {
    if (flag_sort == 1) {
        $.ajax("/admin/sort/find_all", {
            dataType: "json",
            type: "get",
            timeout: 5000,
            success: (data) => {
                append(data.data.result)
            }
        })
    }
    flag_sort = 0
}

/**
 * 分类的数据填充
 * @param data
 */
function append(data) {
    $("#sorts").empty()
    data.forEach((item) => {
        $("#sorts").append(`
         <div class="item" data-value="${item.sortId}">${item.sortName}</div>
       `)
    })
}


/**
 * 全部的标签
 */
function findAllLabel() {
    if (flag_label == 1) {
        $.ajax("/admin/label/find_all_label", {
            dataType: "json",
            type: "get",
            timeout: 5000,
            success: (data) => {
                appendLabel(data.data.result)
            }
        })
    }
    flag_label = 0
}

/**
 * 拼接标签
 * @param data
 */
function appendLabel(data) {
    data.forEach((item) => {
        $("#label").append(`<div class="item" data-value="${item.labelId}">${item.labelName}</div>`)
    })
}

/**
 *  填充的修改文章页面的数据
 * @param data
 */
function appendArticle(data) {
    $("#articleId").val(data.articleId)
    $("#articleTitle").val(data.articleTitle)
    $("#articleContent").text(data.articleContent)
    $("#articleDesc").text(data.articleDesc)
    $("#articleBkFirstImg").val(data.articleBkFirstImg)
}


/**
 *提交数据
 */
function submit() {
    if ($("#articleId").val() == undefined || $("#articleId").val() == null || $("#articleId").val() == "") {
        /**
         * 插入
         */
        $.ajax({
            dataType: 'json',
            data: $("#from").serialize(),
            timeout: 3000,
            type: 'POST',
            url: "/admin/article/insert",
            success: function (data) {
                if (data.code == 20000) {
                    window.location.href = "/admin/blogs"
                } else {
                    alert(data.message)
                }
            }
        })
    } else {
        /**
         * 修改
         */
        $.ajax({
            dataType: 'json',
            data: $("#from").serialize(),
            timeout: 3000,
            type: 'POST',
            url: "/admin/article/update",
            success: function (data) {
                if (data.code == 20000) {
                    window.location.href = "/admin/blogs"
                } else {
                    alert(data.message)
                }
            }
        })
    }
}