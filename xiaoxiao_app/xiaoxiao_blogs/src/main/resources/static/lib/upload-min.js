$.fn.bindUpload = function () {
    var f = {
        url: arguments[0].url || "",
        type: arguments[0].type || "",
        num: arguments[0].num || "",
        callbackPath: arguments[0].callbackPath || "#callbackPath",
        size: arguments[0].size || 5
    };
    var g = this;
    var c = this.find("input.add");
    var e = 0;
    var a = null;
    var b = [];
    var d = /[\u4e00-\u9fa5]/g;
    if (f.num == 1) {
        c.removeAttr("multiple")
    } else {
        c.attr("multiple")
    }
    this.openWindow = function (k) {
        var i = 0;
        for (var h = 0; h < k.length; h++) {
            if (f.num && h < f.num || !f.num) {
                i += k[h].size;
                b.push({name: k[h].name, state: 0})
            } else {
                b.push({name: k[h].name, state: 1})
            }
        }
        var j = '<div id="zwb_upload_status"><div class="box">        <div class="title">            共' + k.length + "个文件&nbsp;" + ((i / 1048576).toFixed(3) == 0 ? i / 1048576 : (i / 1048576).toFixed(3)) + 'mb        </div>        <div class="list"><ul class="listBox"></ul></div>        <div class="foot">            <span class="ok">一键上传</span>            <span class="off">关闭</span>        </div></div>    </div>';
        g.append(j);
        g.find(".off").bind("click", function () {
            c.val("");
            g.find("#zwb_upload_status").fadeOut(100, function () {
                $(this).remove()
            });
            b = [];
            k = null
        });
        g.getImgSrc(k)
    };
    this.getImgSrc = function (l) {
        var h = new FileReader();
        var j = l[e].size;
        var i = l[e].name;
        var n = i.lastIndexOf(".");
        var k = i.substring(n + 1, i.length);
        var m = "";
        if (f.type != "" && f.type.indexOf(k) == -1) {
            m = "文件格式非法";
            b[e].state = 1
        }
        if (d.test(i)) {
            m = "文件名不能含有中文";
            b[e].state = 1
        }
        if (f.size != "" && (j / 1048576) > f.size) {
            m = "文件过大";
            b[e].state = 1
        }
        if (k == "png" || k == "jpg" || k == "gif" || k == "svg") {
            h.readAsDataURL(l[e]);
            h.onload = function () {
                var o = h.result;
                if (e < f.num || f.num == "") {
                    g.find("#zwb_upload_status ul.listBox").append('<li><img  src="' + o + '"/><a><p class="name">' + i + '</p><p class="size">大小:&nbsp;' + ((j / 1048576).toFixed(3) == 0 ? j / 1048576 : (j / 1048576).toFixed(3)) + 'b</p><p>状态:&nbsp;<b class="state">未上传</b></p></a><span class="success"></span><span class="delete"></span><div class="err"' + (m ? 'style="width:100%;"' : "") + ">" + m + '</div><div class="progressBox"><div class="progress"></div></div></li>')
                }
                e++;
                if (e == l.length) {
                    e = 0;
                    g.find(g.find("#zwb_upload_status").fadeIn(100));
                    a = g.find("#zwb_upload_status ul.listBox li");
                    g.find(".ok").bind("click", function () {
                        var q = l.length;
                        for (var p = 0; p < l.length; p++) {
                            if (b[p].state == 0) {
                                g.upload(q, p, l[p])
                            }
                        }
                    });
                    g.find(".delete").bind("click", function () {
                        var p = $(this).parent().index();
                        b[p].state = 1;
                        $(this).parent().hide(100)
                    })
                } else {
                    return g.getImgSrc(l)
                }
            }
        } else {
            if (k == "zip" || k == "rar" || k == "jar" || k == "7z" || k == "cab" || k == "iso") {
                imgPath = "img/zip.png"
            } else {
                if (k == "text") {
                    imgPath = "img/text.png"
                } else {
                    if (k == "doc") {
                        imgPath = "img/word.png"
                    } else {
                        if (k == "html" || k == "htm" || k == "url") {
                            imgPath = "img/html.png"
                        } else {
                            if (k == "xls") {
                                imgPath = "img/excal.png"
                            } else {
                                if (k == "exe") {
                                    imgPath = "img/exe.png"
                                } else {
                                    if (k == "css" || k == "less" || k == "sass") {
                                        imgPath = "img/css.jpg"
                                    } else {
                                        if (k == "js") {
                                            imgPath = "img/js.jpg"
                                        } else {
                                            if (k == "php") {
                                                imgPath = "img/php.png"
                                            } else {
                                                if (k == "sql") {
                                                    imgPath = "img/sql.png"
                                                } else {
                                                    if (k == "ttf" || k == "otf" || k == "ttc" || k == "woff" || k == "woff2") {
                                                        imgPath = "img/font.png"
                                                    } else {
                                                        imgPath = "img/unknown.png"
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (e < f.num || f.num == "") {
                g.find("#zwb_upload_status ul.listBox").append('<li><img  src="' + imgPath + '"/><a><p class="name">' + i + '</p><p class="size">大小:&nbsp;' + ((j / 1048576).toFixed(3) == 0 ? j / 1048576 : (j / 1048576).toFixed(3)) + 'b</p><p>状态:&nbsp;<b class="state">未上传</b></p></a><span class="success"></span><span class="delete"></span><div class="err"' + (m ? 'style="width:100%;"' : "") + ">" + m + '</div><div class="progressBox"><div class="progress"></div></div></li>')
            }
            e++;
            if (e == l.length) {
                e = 0;
                g.find(g.find("#zwb_upload_status").fadeIn(100));
                a = g.find("#zwb_upload_status ul.listBox li");
                g.find(".ok").bind("click", function () {
                    var p = l.length;
                    for (var o = 0; o < l.length; o++) {
                        if (b[o].state == 0) {
                            g.upload(p, o, l[o])
                        }
                    }
                });
                g.find(".delete").bind("click", function () {
                    var o = $(this).parent().index();
                    b[o].state = 1;
                    $(this).parent().hide(100)
                })
            } else {
                return g.getImgSrc(l)
            }
        }
    };
    this.upload = function (h, j, k) {
        var l = new FormData();
        l.append("file", k);
        $.ajax({
            url: f.url,
            type: "post",
            data: l,
            dataType: "json",
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function () {
                a.eq(j).find(".progressBox").show()
            },
            xhr: function () {
                myXhr = $.ajaxSettings.xhr();
                if (myXhr.upload) {
                    myXhr.upload.addEventListener("progress", function (m) {
                        var i = (m.loaded / m.total * 100).toFixed(2);
                        a.eq(j).find(".progress").width(i + "%")
                    }, false)
                }
                return myXhr
            },
            success: function (m) {
                if (m.state == 1) {
                    b[j].state = 1;
                    a.eq(j).find(".state").html("上传成功");
                    a.eq(j).find(".success").addClass("success2");
                    a.eq(j).find(".delete").remove();
                    var i = $(f.callbackPath);
                    var n = i.val();
                    if (f.num && j == (f.num - 1) || j == (h - 1)) {
                        i.val(n + m.path)
                    } else {
                        i.val(n + k.name + "," + m.path + "|")
                    }
                } else {
                    a.eq(j).find(".state").html(m.errmsg);
                    a.eq(j).find(".err").width("100%").html(m.errmsg)
                }
                a.eq(j).find(".progressBox").hide()
            },
            error: function () {
                a.eq(j).find(".progressBox").hide();
                a.eq(j).find(".state").html("请求失败");
                a.eq(j).find(".err").width("100%").html("请求失败")
            }
        })
    };
    c.bind("change", function () {
        if ($(this).val() != "") {
            g.openWindow(this.files)
        }
    })
};