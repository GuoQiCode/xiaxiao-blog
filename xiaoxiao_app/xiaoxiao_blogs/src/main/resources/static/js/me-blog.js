/**
 * 我的博文页面的数据请求
 */
$(function () {
    /**
     * 请求分类文章信息
     */

    find_all_sort();
    /**
     * 查询默认显示在我的博客页面的文章
     */
    find_index_article()

    /**
     *
     */
    find_article_sum()

})
function find_article_sum() {
    $.ajax("/frontline/article/find_article_sum",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
            $("#sum").text(data.data)
        }
    })
}

/**
 * 查询首页文章
 */
function find_index_article(curPage) {
    $.ajax("/frontline/article/find_index_article",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data:{'page':curPage},
        async:false,
        success:(data)=>{
            if(data.code == 20000){
                page(data.data.curPage, data.data.totalPages, data.data.totalRows)
                splice(data.data)
            }
        }
    })
}

/**
 * 请求全部的分类
 */
function find_all_sort() {
    $.ajax("/frontline/sorts/find_all_sorts",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success:(data)=>{
            jointArticleSorts(data.data)
        }
    })
}

/**
 * 拼接的我的博客页面的分类文章数据
 * @param data
 */
function jointArticleSorts(data) {
    data.forEach((item)=>{
        $("#article_sorts").append(`
           <a onclick="find_article_by_sorts(null,${item.sortId})" target="_blank" class="ui teal basic left pointing large label m-margin-tb-tiny">
              ${item.sortName} <div class="detail">${item.sum}</div>
            </a>
         `)
    })
}



/**
 * 根据指定分类获取博客
 * @param sortId
 */
function find_article_by_sorts(currentPage,sortId) {
    $.ajax("/frontline/article/find_blogs_by_sorts",{
        dataType: 'JSON',
        type:'POST',
        data:{'sortId':sortId,'page':currentPage},
        timeout:5000,
        success:(data)=>{
            if(data.code == 20000){
                pageSort(data.data.curPage, data.data.totalPages, data.data.totalRows,sortId)
                splice(data.data)
            }
        }
    })
}



/**
 * 下一页
 */

function next_page(page,totalPages,sortId) {
    page = ++page
    if(sortId != null){
        /**
         * 查询分类文章
         */
        if(page <= totalPages){
            find_article_by_sorts(page,sortId)
        }
    }else
    {
        /**
         * 执行请求首页缓存
         */
        if(page <= totalPages){
            find_index_article(page)
        }
    }

}
/**
 * 上一页
 */
function up_page(page,sortId) {
    page = --page
    if(sortId !=null && sortId != undefined){
        /**
         * 查询分类文章
         */
        if(page >= 1){
            find_article_by_sorts(page,sortId)
        }
    }else {
        /**
         * 请求首页文章
         */
        if(page >= 1){
            find_index_article(page)
        }
    }
}

