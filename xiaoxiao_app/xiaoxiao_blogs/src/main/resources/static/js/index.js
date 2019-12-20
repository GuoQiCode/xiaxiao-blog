$(function () {
    articleSum()
    findAllSorts()
    findArticleNewRecommend()
    findIndexLabelArticle()
    indexArticle(1)
})


/**
 * 获取全部的文章个数
 */
function articleSum() {
    $.ajax("/frontline/article/find_article_sum", {
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        success: (data) => {
            $("#articleSum").text(data.data)
        }
    })
}


/**
 * 获取分类文章
 */
function findAllSorts() {
    $.ajax("/frontline/sorts/find_all_sorts", {
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        success: (data) => {
            const result = data.data
            result.forEach((item) => {
                $("#sort").append(`<a href= '/blog_type?sortId=${item.sortId}' class='item'>${item.sortName}<div class='ui teal basic left pointing label'>${item.sum}</div></a>`)
            })
        }
    })
}


/**
 * 查询最新推荐
 */
function findArticleNewRecommend() {
    $.ajax("/frontline/article/find_article_new_recommend", {
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        success: (data) => {
            const result = data.data.result
            result.forEach((item) => {
                $("#recommend").after(`
                     <div class="ui segment">
                        <a href="/blog_details/${item.articleId}" target="_blank" class="m-black m-text-thin">${item.articleTitle}</a>
                    </div>
                `)
            })
        }
    })
}

/**
 * 获取首页博客列表
 */

function indexArticle(currentPage) {
    $.ajax("/frontline/article/find_index_article?page=" + currentPage, {
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        async:false,
        success: (data) => {
            page(1,1,data.data.totalRows)
            $("#content").html("")
            splice(data.data)
        }
    })
}

/**
 * 下一页
 */

function next_page(page,totalPages) {
    curPage = ++page
    if(curPage <= totalPages){
        indexArticle(curPage)
    }else {
        alert("当前已经是最后一页啦~~~")
    }
}

/**
 * 上一页
 */
function up_page(totalPages) {
    alert(curPage)
}


function page(curPage,totalPages,totalRows) {
    $("#content").after(`
           <div style="margin-top: 15px">
                    <div style="float: left">
                        <div class="ui inverted basic button" id="up_page" onclick="up_page(${curPage},${totalPages})">上一页</div>

                        <div class="ui inverted basic button" id="next_page" onclick="next_page(${totalPages})">下一页</div>
                    </div>
                    <div class="ui label" style="margin-left: 15px;height: 34.8px;text-align: center">
                        <span class="item">
                            <i class="icon mail"></i> 共${totalRows}条数据
                            <div class="floating ui red label">22</div>
                        </span>
                    </div>
                </div>
    `)
}

/**
 * 查询首页标签文章数据
 */
function findIndexLabelArticle() {
    $.ajax("/frontline/label/find_index_label_article", {
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        success: (data) => {
            data.data.result.forEach((item) => {
                $("#labelArticle").append(`
                         <a href="/blogLabel?labelId=${item.labelId}" target="_blank" class="ui teal basic left pointing label m-margin-tb-tiny">
                            ${item.labelName}
                            <div class="detail">${item.sum}</div>
                         </a>
              `)
            })
        }
    })
}

