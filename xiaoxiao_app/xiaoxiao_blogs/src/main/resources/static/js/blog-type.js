$(function () {
    /**
     * 获取指定分类文章的个数
     */
    find_article_sort_sum()
})

/**
 * 获取分类的文章
 * @param sortId
 * @param currentPage
 */
function find_article_by_sort(sortId,currentPage) {
    $.ajax("/frontline/article/find_blogs_by_sorts",{
        dataType: 'JSON',
        type:'POST',
        data:{'sortId':sortId,'page':currentPage},
        timeout:3000,
        success:(data)=>{
            totalRows = data.data.totalRows
            totalPage = data.data.totalPages
            pageSize = data.data.pageSize
            splice(data.data)
        }
    })
}


/**
 * 获取指定分类文章的个数
 */
function find_article_sort_sum() {
    $.ajax("/frontline/article/find_article_by_sort_sum",{
        dataType: 'JSON',
        type:'POST',
        data:{'sortId':sortId[0]},
        timeout:3000,
        success:(data)=>{
            $("#sortName").text(data.data.sortName)
            $("#sum").text(data.data.sum)
        }
    })
}