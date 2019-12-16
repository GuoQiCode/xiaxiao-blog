$(function () {
    /**
     * 请求首页推荐文章文章
     */
    gainTechniqueSharingArticle()
})



/**
 * 请求首页推荐文章文章
 */
function gainTechniqueSharingArticle() {
    $.ajax("/frontline/technique/sharing/gainTechniqueSharingArticle",{
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        success:(data)=>{
            joint(data.data)
        }
    })
}

/**
 * 拼接
 */
function joint(data) {
    data.result.forEach((item)=>{
        $("#recommend").append(`
               <div class="card">
                    <div class="ui card">
                        <div class="img">
                            <img class="img" src="${item.articleBkFirstImg}">
                        </div>
                        <div class="content">
                            <a class="header" target="_blank" href="/blog_details/${item.articleId}">${item.articleTitle}</a>
                            <div class="meta" style="margin-top: 30px">
                                <span class="date">${item.articleDate}</span>
                            </div>
                            <div class="description" style="margin-top: 20px">
                               ${item.articleDesc}
                            </div>
                        </div>
                    </div>
                </div>
        `)
    })
}