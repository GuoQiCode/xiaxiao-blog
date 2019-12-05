$(function () {
    articleSum()
    findAllSorts()
    findArticleNewRecommend()
    findIndexLabelArticle()
})


/**
 * 获取全部的文章
 */
function articleSum() {
    $.ajax("/frontline/article/find_article_sum",{
        dataType: 'JSON',
        type:'POST',
        timeout:3000,
        success:(data)=>{
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
        timeout: 3000,
        success: (data) => {
            const result = data.data
            result.forEach((item) => {
                const {sortName} = item;
                $("#sort").append(`<a href= '' class='item'>${sortName}<div class='ui teal basic left pointing label'>${item.sum}</div></a>`)
            })
        }
    })
}



/**
 * 查询最新推荐
 */
function findArticleNewRecommend() {
    $.ajax("/frontline/article/find_article_new_recommend",{
        dataType: 'JSON',
        type: 'POST',
        timeout: 3000,
        success:(data)=>{
           const result = data.data.result
            result.forEach((item)=>{
                $("#recommend").after(`
                     <div class="ui segment">
                        <a href="#" target="_blank" class="m-black m-text-thin">${item.articleTitle}</a>
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
    $.ajax("/frontline/article/find_index_article?page="+currentPage,{
        dataType: 'JSON',
        type: 'POST',
        timeout: 3000,
        success:(data)=>{
            totalRows = data.data.totalRows
            totalPage = data.data.totalPages
            pageSize = data.data.pageSize
            $("#content").html("")
            splice(data.data.result)
        }
    })
}


/**
 * 查询首页标签文章数据
 */
function findIndexLabelArticle() {
    $.ajax("/frontline/label/find_index_label_article",{
        dataType: 'JSON',
        type: 'POST',
        timeout: 3000,
        success:(data)=>{
          data.data.result.forEach((item)=>{
              $("#labelArticle").append(`
                         <a href="#" target="_blank" class="ui teal basic left pointing label m-margin-tb-tiny">
                            ${item.labelName}
                            <div class="detail">${item.sum}</div>
                         </a>
              `)
          })
        }
    })
}





/**
 * 拼接首页字符串
 */
function splice(data) {
    data.forEach((item)=>{
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
                                        <a href="#" target="_blank"
                                           class="ui teal basic label m-padded-tiny m-text-thin">详情查看</a>
                                    </div>
                                </div>
                            </div>

                            <div class="five wide column">
                                <a href="#" target="_blank">
                                    <img src="${item.userProfilePhoto}" alt="" class="ui rounded image">
                                </a>
                            </div>
                        </div>
                    </div>
    `)
    })
}


