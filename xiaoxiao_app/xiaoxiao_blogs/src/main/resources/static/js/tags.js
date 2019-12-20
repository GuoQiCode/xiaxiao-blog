$(function () {
    /**
     * 获取标签的个数
     */
    find_sorts_sum()

    /**
     * 获取文章标签分类总数
     */
    find_label_sum_and_article()


    /**
     * 请求全部文章
     */
    find_all_article(1)
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
    $.ajax("/frontline/article/find_index_article",{
        dataType: "json",
        type: "POST",
        timeout: 5000,
        data:{'page':currentPage},
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
 * 根据标签查询的文章
 * @param labelId
 * @param currentPage
 */
function find_article_by_label(currentPage,id) {
    $.ajax("/frontline/article/find_article_by_label_id",{
        dataType: "json",
        type: "POST",
        data:{'labelId':id,'page':currentPage},
        timeout: 5000,
        success:(data)=>{
            if(data.code == 20000){
                pageSort(data.data.curPage, data.data.totalPages, data.data.totalRows,id)
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
    if(sortId != null && sortId != undefined){
        /**
         * 查询标签文章
         */
        if(page <= totalPages){
            find_article_by_label(page,sortId)
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
         * 查询标签文章
         */
        if(page >= 1){
            find_article_by_label(page,sortId)
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


