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