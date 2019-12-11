$(function () {
    /**
     * 获取标签的个数
     */
    find_sorts_sum()

    /**
     * 获取文章标签分类总数
     */
    find_label_sum_and_article()
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
                          <a href="#" target="_blank" class="ui teal basic left pointing large label m-margin-tb-tiny">
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
