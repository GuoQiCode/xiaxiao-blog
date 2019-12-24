/**
 * 页面禁止右键和F12
 */
$(function () {
    $(document).bind("contextmenu", function () {
        return false;
    });//禁止右键
    document.oncontextmenu = function () {
        return false;
    };
    document.onkeydown = function () {
        if (window.event && window.event.keyCode == 123) {
            event.keyCode = 0;
            event.returnValue = false;
            return false;
        }
    };//禁止F12
})


/**
 * 弹窗显示
 * @param flag
 */
function showAndHide(flag) {
    if (flag == 1) {
        $('.ui.modal.form')
            .modal('show')
    } else {
        $('.ui.modal.form')
            .modal('hide')
    }
}


/**
 * 在分类分页查询中实现存储分类ID
 */
let sortId

/**
 * 在标签页面存储分类的数据
 */
let labelId;

/**
 * 页面之间传值
 * @type {{paramValues: UrlParam.paramValues, param: UrlParam.param, hasParam: (function(*): boolean), paramMap: (function())}}
 */
UrlParam = function () { // url参数
    var data, index;
    (function init() {
        data = []; //值，如[["1","2"],["zhangsan"],["lisi"]]
        index = {}; //键:索引，如{a:0,b:1,c:2}
        var u = window.location.search.substr(1);
        if (u != '') {
            var params = decodeURIComponent(u).split('&');
            for (var i = 0, len = params.length; i < len; i++) {
                if (params[i] != '') {
                    var p = params[i].split("=");
                    if (p.length == 1 || (p.length == 2 && p[1] == '')) {// p | p= | =
                        data.push(['']);
                        index[p[0]] = data.length - 1;
                    } else if (typeof (p[0]) == 'undefined' || p[0] == '') { // =c 舍弃
                        continue;
                    } else if (typeof (index[p[0]]) == 'undefined') { // c=aaa
                        data.push([p[1]]);
                        index[p[0]] = data.length - 1;
                    } else {// c=aaa
                        data[index[p[0]]].push(p[1]);
                    }
                }
            }
        }
    })();
    return {
        // 获得参数,类似request.getParameter()
        param: function (o) { // o: 参数名或者参数次序
            try {
                return (typeof (o) == 'number' ? data[o][0] : data[index[o]][0]);
            } catch (e) {
            }
        },
        //获得参数组, 类似request.getParameterValues()
        paramValues: function (o) { // o: 参数名或者参数次序
            try {
                return (typeof (o) == 'number' ? data[o] : data[index[o]]);
            } catch (e) {
            }
        },
        //是否含有paramName参数
        hasParam: function (paramName) {
            return typeof (paramName) == 'string' ? typeof (index[paramName]) != 'undefined' : false;
        },
        // 获得参数Map ,类似request.getParameterMap()
        paramMap: function () {
            var map = {};
            try {
                for (var p in index) {
                    map[p] = data[index[p]];
                }
            } catch (e) {
            }
            return map;
        }
    }
}();

/**
 * 拼接首页字符串
 */
function splice(data) {
    /**
     * 置空中间内容
     */
    $("#content").html("")
    data.result.forEach((item) => {
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


function page(curPage, totalPages, totalRows) {
    $("#page").html("")
    $("#page").append(`
                <div style="margin-top: 20px;margin-bottom: 30px">
                    <div style="float: left">
                        <div class="ui small  inverted blue button" id="up_page" onclick="up_page(${curPage})">上一页</div>

                        <div class="ui small  inverted blue button" id="next_page" onclick="next_page(${curPage},${totalPages})">下一页</div>
                    </div>
                    <div class="ui label" style="margin-left: 15px; margin-top: 5px">
                            <i class="icon inbox"></i> 第${curPage}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <i class="icon comment"></i> 共${totalPages}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <i class="icon mail"></i> 共${totalRows}条数据
                    </div>
                </div>
    `)
}

/**
 *
 * @param curPage
 * @param totalPages
 * @param totalRows
 * @param sortId
 */
function pageSort(curPage, totalPages, totalRows, sortId) {
    $("#page").html("")
    $("#page").append(`
           <div>
                    <div style="float: left">
                        <div class="ui small  inverted blue button" id="up_page" onclick="up_page(${curPage},${sortId})">上一页</div>

                        <div class="ui small  inverted blue button" id="next_page" onclick="next_page(${curPage},${totalPages},${sortId})">下一页</div>
                    </div>
                    <div class="ui label" style="margin-left: 15px; margin-top: 5px">
                            <i class="icon inbox"></i> 第${curPage}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <i class="icon comment"></i> 共${totalPages}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <i class="icon mail"></i> 共${totalRows}条数据
                    </div>
                </div>
    `)
}



