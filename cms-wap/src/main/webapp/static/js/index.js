$(document).ready(function () {
    try {
        onDocumentReady();
    } catch (e) {
    }
});
var isNavFiexed;
function onDocumentReady() {
    //滚动事件
    $(document).bind("scroll", function (event) {

        //懒加载
        lazyDownload("#mainListContainer");

        //滚动条加载更多
/*        if (!isRequesting && updateCount != 0) {
            if ($(document).scrollTop() > $(document).height() - $(window).height() * 2) {
                ajaxRequest('/Home/IndexPartial', { channelId: cid, time: nextPage, f: from, dp: dp }, {
                    selector: '#mainListContainer',
                    callback2: function () {
                        if (updateCount != 0) {
                            resetImage();
                            lazyDownload("#mainListContainer");
                            showtip("又为您推荐了" + updateCount + "篇文章");
                        } else {
                            showtip("已经没有最新文章了！");
                        }
                    }
                });
            };
        }*/

        //顶部导航条锁定
        if (isNavFiexed && $(document).scrollTop() < 44) {
            $("#nav_container").removeClass("nav-fixed");
            $("#box_nav_position_holder").hide();
            isNavFiexed = false;
        }
        else if (!isNavFiexed && $(document).scrollTop() >= 44) {
            $("#nav_container").addClass("nav-fixed");
            $("#box_nav_position_holder").show();
            isNavFiexed = true;
        }
    });

    //懒加载
    lazyDownload("#mainListContainer");

    //初始化图片尺寸
    resetImage();
}

function resetImage() {
    var h = $(".images-list-box").width() * 0.3333 * 0.65;
    var imgs = $(".images-list-box").find("img:not([style])");
    imgs.height(h);
}

var tipNode = null;
var intervalId = -1;
function showtip(txt) {
    if (intervalId == -1) {
        tipNode = $("<div class='global-tip'></div>");
        //tipNode.css("top", $(".header").length == 0 ? 0 : $(".header").height());
        tipNode.hide();
        tipNode.fadeIn("fast");
        $("body").prepend(tipNode);
    } else {
        clearInterval(intervalId);
        intervalId = -1;
    }

    tipNode.text(txt);

    intervalId = setTimeout(function() {
        tipNode.fadeOut("fast", function () {
            tipNode.remove();
            tipNode = null;
            intervalId = -1;
        });
    }, 4000);
}


function lazyDownload(selector) {
    var sh = $(window).height();
    var imgs = $(selector).find("img[data-src]");

    imgs.each(function () {
        var self = $(this);
        var a = self.offset().top;
        var h = sh + $(document).scrollTop() - a;
        
        if (h >= -100) {
            self.attr("src", self.attr("data-src"));
            self.removeAttr("data-src");
            self.removeAttr("height");
        }
    });
}