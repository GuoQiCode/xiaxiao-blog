$(function () {
    /**
     * 获取标签文章的个数
     */
    find_article_label_sum()

    /**
     *
     */
    find_article_by_label(UrlParam.param("labelId"),1)
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
            if(data.code == 20000){
                page(data.data.curPage, data.data.totalPages, data.data.totalRows)
                splice(data.data)
            }
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
        data:{'labelId':UrlParam.param("labelId")},
        timeout: 5000,
        success:(data)=>{
            $("#labelName").text(data.data.labelName)
            $("#sum").text(data.data.sum)
        }
    })
}


/**
 * 下一页
 */

function next_page(page,totalPages) {
    page = ++page
    if(page <= totalPages){
        find_article_by_label(UrlParam.param("labelId"),page);
    }
}
/**
 * 上一页
 */
function up_page(page) {
    page = --page
    if(page >= 1){
        find_article_by_label(UrlParam.param("labelId"),page);
    }
}