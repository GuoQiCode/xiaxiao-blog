$(function () {
    /**
     * 获取标签文章的个数
     */
    find_article_label_sum()
})


/**
 * 获取指定标签的文章
 */
function find_article_by_label(labelId,currentPage) {
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

/**
 * 获取标签文章的个数
 */
function find_article_label_sum() {
    $.ajax("/frontline/article/find_article_label_sum",{
        dataType: "json",
        type: "POST",
        data:{'labelId':labelId[0]},
        timeout: 5000,
        success:(data)=>{
            $("#labelName").text(data.data.labelName)
            $("#sum").text(data.data.sum)
        }
    })
}