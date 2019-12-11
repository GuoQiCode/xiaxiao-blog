$(function () {
    /**
     * 获取标签的个数
     */
    find_sorts_sum()

    /**
     * 获取文章标签分类总数
     */
    find_label_sum_and_article()
})


/**
 * 获取标签的个数
 */
function find_sorts_sum() {
    $.ajax("/frontline/label/count",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
            $("#sum").text(data.data.sum)
        }
    })
}


/**
 * 查询首页标签文章数据
 */
function find_label_sum_and_article() {
    $.ajax("/frontline/label/find_index_label_article",{
        dataType: 'JSON',
        type: 'POST',
        timeout: 3000,
        success:(data)=>{
            data.data.result.forEach((item)=>{
                $("#label").append(`
                          <a onclick="find_article_by_label(null,${item.labelId})" target="_blank" class="ui teal basic left pointing large label m-margin-tb-tiny">
                             ${item.labelName}<div class="detail">${item.sum}</div>
                </a>
              `)
            })
        }
    })
}

/**
 * 请求标签页文章
 * @param currentPage
 */
function find_all_article(currentPage) {
    $.ajax("/frontline/article/find_index_article?page="+currentPage,{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
            totalRows = data.data.totalRows
            totalPage = data.data.totalPages
            pageSize = data.data.pageSize
            splice(data.data)
        }
    })
}

/**
 * 根据标签查询的文章
 * @param labelId
 * @param currentPage
 */
function find_article_by_label(currentPage,id) {
    labelId = id
    $.ajax("/frontline/article/find_article_by_label_id",{
        dataType: "json",
        type: "POST",
        data:{'labelId':labelId,'page':currentPage},
        timeout: 5000,
        success:(data)=>{
            totalRows = data.data.totalRows
            totalPage = data.data.totalPages
            pageSize = data.data.pageSize
            splice(data.data)
        }
    })
}



