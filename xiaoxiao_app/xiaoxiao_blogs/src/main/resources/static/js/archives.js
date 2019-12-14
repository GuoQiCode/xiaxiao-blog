$(function () {

    /**
     * 获取文章的个数
     */
    article_sum()

    /**
     * 获取归档的文章
     */
    find_article_archive()
})

/**
 * 获取全部文章的个数
 */
function article_sum() {
    $.ajax("/frontline/article/find_article_sum",
        {
            dataType: 'JSON',
            type: 'POST',
            timeout: 5000,
            success:(data)=>{
                $("#sum").text(data.data)
            }
        })
}


/**
 * 获取归档的文章
 */
function find_article_archive() {
    $.ajax("/frontline/article/find_article_archive",{
        dataType: 'JSON',
        type: 'POST',
        timeout: 5000,
        success:(data)=>{
            for(const key in data.data){
                $("#content").after(
                    `
                         <h3 class="ui center aligned header">${key}</h3>
                            <div class="ui fluid vertical menu" id="${key}">
             
                            </div>
                    `)
                /**
                 * 遍历二级文章
                 */
                data.data[key].forEach((item)=>{
                    $("#"+key+"").append(
                        `
                              <a href="/blog_details/${item.articleId}" target="_blank" class="item">
                                  <span>
                                    <i class="mini teal circle icon"></i>${item.articleTitle}
                                    <div class="ui teal basic left pointing label m-padded-mini">${item.year}</div>
                                  </span>
                                <div class="ui orange basic left pointing label m-padded-mini ">${item.articleType}</div>
                             </a>
                        `)
                })
            }
        }
    })
}