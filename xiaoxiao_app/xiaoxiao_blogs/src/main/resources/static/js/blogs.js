$(function () {
    /**
     * 请求文章
     */
    findAllArticle(1)
})

/**
 * 获取全部的文章
 * @param currentPage
 */
function findAllArticle(currentPage) {
    $.ajax("/admin/article/find_all_article?page=" + currentPage, {
        async: false,
        dataType: "json",
        type: "get",
        timeout: 5000,
        success: (data) => {
            if (data.code == 20000) {
              if(data.code == 20000){
                  $("#blogs").empty()
                  page(data.data.curPage, data.data.totalPages, data.data.totalRows)
                  Splicing(data)
              }
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
        findAllArticle(page)
    }
}
/**
 * 上一页
 */
function up_page(page) {
    page = --page
    if(page >= 1){
        findAllArticle(page)
    }
}

/**
 * 页面数据拼接
 * @param data
 * @constructor
 */
function Splicing(data) {
    let result = data.data.result
    result.forEach((item) => {
        $("#blogs").append(`<tr>
                 <td>${item.articleId}</td>
                 <td>${item.articleTitle}</td>
                 <td>${item.sortName}</td>
                  <td>${item.articleCommentCount}</td>
                 <td>${item.articleViews}</td>
                 <td>${item.articleLikeCount}</td>
                 <td>${item.articleDate}</td>
                 <td><a onclick="updateRecommend(${item.articleId},'${item.articleRecommend}')" class="ui  mini teal basic button">${item.articleRecommend}</a></td>
                 <td>
                    <a onclick="pageJump(${item.articleId})" class="ui mini teal basic button">编辑</a>
                    <a onclick="del(${item.articleId})" class="ui mini red basic button">删除</a>
                 </td>
            </tr>`)
    })
}

/**
 * 页面的跳转到修改博客的页面上
 * @param articleId
 */
function pageJump(articleId) {
    /**
     * 设置文章的ID到浏览器的数据库中并且在编写博客的页面进行获取
     */
    sessionStorage.setItem("articleId", articleId)
    $.ajax("/admin/article/flowchart", {
        dataType: 'JSON',
        type: "GET",
        timeout: 5000,
        success: (data) => {
            window.location.replace(data.message)
        }
    })
}


/**
 * 修改是否推荐
 * @param articleId
 * @param articleRecommend
 */
function updateRecommend(articleId, articleRecommend) {
    if(articleRecommend == "on"){
        articleRecommend = "off"
    }else{
        articleRecommend = "on"
    }
    $.ajax("/admin/article/update_recommend",{
        dataType: 'JSON',
        type: "GET",
        timeout: 5000,
        data:{"articleId":articleId,'articleRecommend':articleRecommend},
        success:(data)=>{
            if(data.code == 20000){
                findAllArticle(1)
            }else{
                alert(data.message)
            }
        }
    })
}


/**
 * 删除博客文章
 * @param articleId
 */
function del(articleId) {
    $.ajax("/admin/article/delete?articleId=" + articleId, {
        dataType: "json",
        type: "get",
        timeout: 5000,
        success: (data) => {
            if (data.code == 20000) {
                findAllArticle(1)
            } else {
                alert(data.message)
            }
        }
    })
}


/**
 * 获取一个文章信息
 * @param articleId
 */
function findArticleById(articleId) {
    $.ajax("/admin/article/find_article_by_id?articleId=" + articleId, {
        dataType: "json",
        type: "POST",
        timeout: 5000,
        success: (data) => {
            if (data.code == 20000) {
                appendArticle(data.data)
            } else {
                alert(data.message)
            }
        }
    })
}







