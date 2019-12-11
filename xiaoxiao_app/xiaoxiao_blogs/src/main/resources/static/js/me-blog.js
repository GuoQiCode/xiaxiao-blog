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

})


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
 * 拼接首页字符串
 */
function splice(data) {
    /**
     * 最大条数
     */
    $("#articleSum").text(data.totalRows)
    /**
     * 置空中间内容
     */
    $("#content").html("")
    data.result.forEach((item)=>{
        $("#content").append(`
            <div class="ui padded vertical segment m-padded-tb-large">
                        <div class="ui mobile reversed stackable grid">
                            <div class="eleven wide column">
                                <h3 class="ui header">${item.articleTitle}</h3>
                                <p class="m-text">${item.articleDesc}</p>
                                <div class="ui grid">
                                    <div class="eleven wide column">
                                        <div class="ui mini horizontal link list">
                                            <div class="item">
                                                <img src="${item.userProfilePhoto}" alt=""
                                                     class="ui avatar image">
                                                <div class="content"><a href="#" class="header">${item.userNickname}</a></div>
                                            </div>
                                            <div class="item">
                                                <i class="calendar icon"></i> ${item.articleDate}
                                            </div>
                                            <div class="item">
                                                <i class="eye icon"></i> ${item.articleViews}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="right aligned five wide column">
                                        <a href="/blog_details/${item.articleId}" target="_blank"
                                           class="ui teal basic label m-padded-tiny m-text-thin">详情查看</a>
                                    </div>
                                </div>
                            </div>

                            <div class="five wide column">
                                <a href="/blog_details/${item.articleId}" target="_blank">
                                    <img style="width: 150px;height: 100px" src="${item.articleBkFirstImg}" alt="" class="ui rounded image">
                                </a>
                            </div>
                        </div>
                    </div>
    `)
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
