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
 * 查询推荐文章
 */
function find_index_article() {
    $.ajax("/frontline/article/find_index_article",{
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
function find_article_by_sorts(currentPage,sortID) {
    /**
     * 赋值sortID给common.js中的变量
     */
    sortId = sortID
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
