$(function () {
    /**
     * 获取指定分类文章的个数
     */
    find_article_sort_sum()

    /**
     * 获取分类文章
     */
    find_article_by_sort(UrlParam.param("sortId"),1)
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
        timeout:5000,
        success:(data)=>{
            if(data.code ==  20000){
                page(data.data.curPage, data.data.totalPages, data.data.totalRows)
                splice(data.data)
            }
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
        data:{'sortId':UrlParam.param("sortId")},
        timeout:5000,
        success:(data)=>{
            if(data.code == 20000){
                $("#sortName").text(data.data.sortName)
                $("#sum").text(data.data.sum)
            }
        }
    })
}


/**
 * 下一页
 */

function next_page(page,totalPages) {
    page = ++page
    if(page <= totalPages){
       find_article_by_sort(UrlParam.param("sortId"),page)
    }
}
/**
 * 上一页
 */
function up_page(page) {
    page = --page
    if(page >= 1){
        find_article_by_sort(UrlParam.param("sortId"),page)
    }
}