
function search_artcile(currentPage,query) {
    $.ajax("/frontline/article/searchArticle", {
        dataType: 'JSON',
        type: 'POST',
        data:{'page':currentPage,"query":query},
        timeout: 5000,
        success: (data) => {
            totalRows = data.data.totalRows
            totalPage = data.data.totalPages
            pageSize = data.data.pageSize
            if(data.code = 20000 && data.data !=null && data.data.length > 0){
                searchSplit(data)
            }else {
                $("#page").html("")
            }
        }
    })
}


function searchSplit(data) {
    /**
     * 置空中间内容
     */
    $("#content").html("")
    data.data.forEach((item)=>{
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