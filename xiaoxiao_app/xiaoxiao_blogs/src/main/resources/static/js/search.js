
let query
$(function () {
    /**
     * 1.获取的浏览器上的关键字
     * 2.触发ajax
     * 3.实现上一页下一的功能
     */

     query = UrlParam.paramValues(`query`)
    if (query[0] != null && query[0] != "") {
        search_artcile(1, query[0])
    }
})


function search_artcile(currentPage, query) {
    $.ajax("/frontline/article/searchArticle", {
        dataType: 'JSON',
        type: 'POST',
        data: {'page': currentPage, "query": query},
        timeout: 5000,
        success: (data) => {
            if (data.code = 20000 && data.data != null && data.data.length > 0) {
                let pages

                if(data.data.length % 10 > 0)
                {
                    pages = String(data.data.length/10).substring(0,1)
                    parseInt(pages)
                    pages = ++pages
                }else if(data.data.length % 10 < 0){
                    pages = String(data.data.length/10).substring(0,1)
                    pages = parseInt(pages)
                }
                page(currentPage, pages, data.data.length)
                searchSplit(data)
            } else {
                $("#page").html("")
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
        search_artcile(page,query[0])
    }

}
/**
 * 上一页
 */
function up_page(page) {
    page = --page
    if(page >= 1){
        search_artcile(page,query[0])
    }
}


function searchSplit(data) {
    /**
     * 置空中间内容
     */
    $("#content").html("")
    data.data.forEach((item) => {
        $("#content").append(`
            <div class="ui padded vertical segment m-padded-tb-large">
                        <div class="ui mobile reversed stackable grid">
                            <div class="eleven wide column">
                                <h3 class="ui header">${item.article_title}</h3>
                                <p class="m-text">${item.article_desc}</p>
                                <div class="ui grid">
                                    <div class="eleven wide column">
                                        <div class="ui mini horizontal link list">
                                            <div class="item">
                                                <img src="${item.user_profile_photo}" alt=""
                                                     class="ui avatar image">
                                                <div class="content"><a href="#" class="header">${item.user_nickname}</a></div>
                                            </div>
                                            <div class="item">
                                                <i class="calendar icon"></i> ${item.article_date}
                                            </div>
                                            <div class="item">
                                                <i class="eye icon"></i> ${item.article_views}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="right aligned five wide column">
                                        <a href="/blog_details/${item.article_id}" target="_blank"
                                           class="ui teal basic label m-padded-tiny m-text-thin">详情查看</a>
                                    </div>
                                </div>
                            </div>

                            <div class="five wide column">
                                <a href="/blog_details/${item.article_id}" target="_blank">
                                    <img style="width: 150px;height: 150px" src="${item.article_bk_first_img}" alt="" class="ui rounded image">
                                </a>
                            </div>
                        </div>
                    </div>
    `)
    })
}