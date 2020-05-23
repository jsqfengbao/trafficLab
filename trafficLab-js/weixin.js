/* if (pc_switch) {
    var system = {
        win: false,
        mac: false,
        xll: false
    };
    var p = navigator.platform;
    system.win = p.indexOf("Win") == 0;
    system.mac = p.indexOf("Mac") == 0;
    system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
    if (system.win || system.mac || system.xll) {
        window.location = tzurl
    }
} */
var city = '';
$.getScript("http://ip.ws.126.net/ipquery",function() {
    city = lo + lc;
    /* if (area_switch) {
        $.each(area_shd,function(index, value) {
            if (city.indexOf(value) > -1) {
                window.location = tzurl
            }
        })
    } */
});
var viewtime = 0;
var t1 = window.setInterval(function() {
    viewtime++
},
1000);
var weixin_rum, strname, strwx, strtel, strurl, view_height = 5;
weixin_rum = Math.floor(Math.random() * wuk_weixin.length);
strname = wuk_name[weixin_rum];
strwx = wuk_weixin[weixin_rum];
strtel = wuk_tel[weixin_rum];
strurl = wuk_url[weixin_rum];
if (weixin_sjd) {
    var day = new Date();
    var hr = day.getHours();
    for (var i = 0; i < wuk_weixin_sjd.length; i++) {
        if (wuk_weixin_sjd[i][0] == hr) {
            strwx = wuk_weixin_sjd[i][1];
            strurl = wuk_weixin_sjd[i][2];
            strname = wuk_weixin_sjd[i][3];
            strtel = wuk_weixin_sjd[i][4];
            break
        }
    }
}
function getQQCode(gdlb_time, wuk_weixin_arr) {
    var wechat = "";
    var qrcodes = [{
        TimeBegin: "00:00",
        TimeEnd: "23:59",
        Span: gdlb_time,
        QrCodes: wuk_weixin_arr
    },
    ];
    var result = "";
    var _currTime = new Date().getHours().toString();
    var _minutes = new Date().getMinutes();
    if (_minutes < 10) _currTime += "0";
    _currTime += _minutes.toString();
    _currTime = parseInt(_currTime);
    for (var i = 0; i < qrcodes.length; i++) {
        var _timeBegin = parseInt(qrcodes[i].TimeBegin.replace(":", ""));
        var _timeEnd = parseInt(qrcodes[i].TimeEnd.replace(":", ""));
        if (_timeEnd == 0) _timeEnd = 2400;
        if (_timeBegin < _currTime && _currTime <= _timeEnd) {
            var _span = qrcodes[i].Span;
            var _qrcodes = qrcodes[i].QrCodes;
            var _index = 0;
            var _t = (_currTime - _timeBegin);
            if (_t >= _span) {
                _t = parseInt(_t / _span);
                _index = parseInt(_t % _qrcodes.length)
            }
            result = _qrcodes[_index]
        }
    }
    if (result == null || result == undefined || result == "") result = qrcodes[0].QrCodes[0];
    wechat = result;
    return result
}
if (weixin_zdlb) {
    strwx = getQQCode(gdlb_times, wuk_weixin);
    var i;
    for (i = 0; i < wuk_weixin.length; i++) {
        if (wuk_weixin[i] == strwx) {
            break
        }
    }
    strname = wuk_name[i];
    strtel = wuk_tel[i];
    strurl = wuk_url[i]
}
/* if (pc_ewm) {
    $(function() {
        $("body").append('<div style="left: 50%;margin-left: 400px;position: fixed;text-align: center;top: 20%;width: 200px;z-index: 1000;background:white;border-radius:10px;padding:5px;border:1px solid #E0E0E0;font-size:16px"><img src="' + strurl + '" width="100%""><p>' + pcwz + '</p></div>')
    })
} */
$(function() {
    $(".wuk_name").html(strname);
    if (zd_copy) {
        $(".wuk_weixin").html("<button  data-clipboard-action='copy' data-clipboard-target='#target' id='copy_btn' style='background:none;border:none;padding:0px;margin:0px'>" + strwx + "(点击复制)</button>")
    } else {
        $(".wuk_weixin").html(strwx)
    }
    $(".wuk_tel").html(strtel);
    $(".wuk_tel_href").attr('href', 'tel:' + strtel);
    $(".wuk_ewm").attr("src", strurl)
});
function getOsVersion() {
    if (navigator.userAgent.toLowerCase().match(/iphone/i)) return "iPhone";
    if (navigator.userAgent.toLowerCase().match(/ipad/i)) return "iPad";
    if (navigator.userAgent.toLowerCase().match(/ipod/i)) return "iPod";
    if (navigator.userAgent.toLowerCase().match(/android/i)) return "Android";
    if (navigator.userAgent.toLowerCase().match(/windows nt/i)) return "Windows";
    if (navigator.userAgent.toLowerCase().match(/windows phone/i)) return "Windows Phone";
    if (navigator.platform == "Mac68K" || navigator.platform == "MacPPC" || navigator.platform == "Macintosh" || navigator.platform == "MacIntel") return "Mac";
    return "other"
}
var terminal = getOsVersion();
function setCookie(name, value, expires) {
    var exp = new Date();
    exp.setTime(exp.getTime() + expires * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString()
}
function readcookie(name) {
    var oRegex = new RegExp(name + '=([^;]+)', 'i');
    var oMatch = oRegex.exec(document.cookie);
    if (oMatch && oMatch.length > 1) return unescape(oMatch[1]);
    else return ''
}
function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1])
        }
    }
    return theRequest
}
function GetRequesta(aaa) {
    var bbb = aaa.indexOf('?');
    var url = aaa.substr(bbb);
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1])
        }
    }
    return theRequest
}
var Request = new Object();
Request = GetRequest();
var fromurl, fromurla, nowurl, nowurla, keyword, city;
fromurl = document.referrer;
fromurla = Request['f'];
nowurl = document.URL;
nowurla = Request['n'];
if (nowurla != null && nowurla != "") {
    nowurl = nowurla
}
if (fromurla != null && fromurla != "") {
    fromurl = fromurla
}
function getsogo() {
    var system = {
        win: false,
        mac: false,
        xll: false
    };
    var p = navigator.platform;
    system.win = p.indexOf("Win") == 0;
    system.mac = p.indexOf("Mac") == 0;
    system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
    if (system.win || system.mac || system.xll) {} else {
        fromurl_s = fromurl.substring(decodeURI(decodeURI(fromurl)).indexOf('keyword'));
        fromurl_s = fromurl_s.substring(0, fromurl_s.indexOf('&'));
        fromurl_s = fromurl_s.substring(fromurl_s.indexOf('=') + 1);
        return decodeURI(fromurl_s)
    }
}
var getstr = new Object();
getstr = GetRequesta(nowurl);
var gzid = getstr["gzid"];
var getci = new Object();
getci = GetRequesta(decodeURI(decodeURI(fromurl)));
if (fromurl != null && fromurl != "") {
    setCookie("fromurl", fromurl, 30)
} else {
    fromurl = readcookie("fromurl")
}
if (nowurl != null && nowurl != "") {
    setCookie("nowurl", nowurl, 30)
} else {
    nowurl = readcookie("nowurl")
}
if (nowurl.indexOf("notki") > 0) {
    keyword = "网盟主题词ID:" + getstr["notki"] + "|网盟创意ID:" + getstr["notct"] + "|网盟来源域名:" + getstr["notpl"] + "|网盟来源关键词:" + getstr["notkw"]
} else if (fromurl.indexOf("baidu.com") > 0 && fromurl.indexOf("wd=") > 0) {
    keyword = "百度:" + getci['wd']
} else if (fromurl.indexOf("baidu.com") > 0 && fromurl.indexOf("word=") > 0) {
    keyword = "百度:" + getci['word']
} else if (fromurl.indexOf("m.baidu.com") > 0) {
    keyword = "百度移动搜索"
} else if (fromurl.indexOf("sogou.com") > 0) {
    var system = {
        win: false,
        mac: false,
        xll: false
    };
    var p = navigator.platform;
    system.win = p.indexOf("Win") == 0;
    system.mac = p.indexOf("Mac") == 0;
    system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
    if (system.win || system.mac || system.xll) {
        keyword = "搜狗:" + getci['query']
    } else {
        keyword = "搜狗:" + getsogo()
    }
} else if (fromurl.indexOf("soso.com") > 0) {
    keyword = "SOSO:" + getci['query=']
} else if (fromurl.indexOf("so.com") > 0) {
    keyword = "360搜索:" + getci['q']
} else if (fromurl.indexOf("sm.cn") > 0) {
    keyword = "神马搜索:" + getci['q']
} else {
    keyword = ""
}
if (keyword != null && keyword != "") {
    setCookie("keyword", keyword, 7)
} else {
    keyword = readcookie("keyword")
}
if (gzid != null && gzid != "") {
    setCookie("gzid", gzid, 7)
} else {
    gzid = readcookie("gzid")
}
if (keyword == null || keyword == "") {
    keyword = fromurl
}
function actiondata(type) {
    $.ajax({
        type: "post",
        dataType: "jsonp",
        data: {
            "link": nowurl,
            "keyword": keyword,
            "gzid": viewtime,
            "city": city,
            "type": type,
            "uniq": uniq,
            "terminal": terminal,
            "weixin": strwx,
            "height": view_height + "%"
        },
        jsonp: "jsonpcallback",
        jsonpCallback: "jsonpcallback",
        url: "http://118.89.28.233:9092/test/httpTest",
        success: function(msg) {}
    })
}
var timeOutEvent = 0;
var copyaction = "";
$(function() {
    isclick = false;
    if (zd_copy) {} else {
        $(".wuk_weixin").one({
            touchstart: function(e) {
                copyaction = $(this).attr("wukaction");
                timeOutEvent = setTimeout("longPress()", 500)
            },
            touchmove: function() {
                clearTimeout(timeOutEvent);
                timeOutEvent = 0
            },
            touchend: function() {
                clearTimeout(timeOutEvent);
                if (timeOutEvent != 0) {}
                return false
            }
        })
    }
});
var system = {
    win: false,
    mac: false,
    xll: false
};
var p = navigator.platform;
system.win = p.indexOf("Win") == 0;
system.mac = p.indexOf("Mac") == 0;
system.x11 = (p == "X11") || (p.indexOf("Linux") == 0);
if (system.win || system.mac || system.xll) {
    $(function() {
        $(".wuk_weixin").on("copy",function() {
            cli_copy("复制")
        })
    })
}
var iscopyc = true;
function cli_copy(type) {
    if (iscopyc) {
        iscopyc = false;
        actiondata(type)
    }
}
//长按复制
function longPress() {
    timeOutEvent = 0;
    if (isclick) {
		actiondata("点击");
        return false
    }
    actiondata("复制");
    isclick = true;
    if (zd_goto) {
        window.setTimeout(function() {
            window.location = "weixin://"
        },
        tztime + 500)
    }
}
window.onscroll = function() {
    var wScrollY = window.scrollY;
    var wInnerH = window.innerHeight;
    var bScrollH = document.body.scrollHeight;
    bScrollH = parseInt(bScrollH);
    var currentY = parseInt(wScrollY + wInnerH);
    var domain = document.domain;
    if (currentY < (bScrollH * 0.3)) {
        if (view_height < 30) view_height = 30
    }
    if (currentY > (bScrollH * 0.3) && currentY < (bScrollH * 0.5)) {
        if (view_height < 50) view_height = 50
    }
    if (currentY > (bScrollH * 0.5) && currentY < (bScrollH * 0.8)) {
        if (view_height < 80) view_height = 80
    }
    if (currentY > (bScrollH * 0.8)) {
        if (view_height < 100) {
            view_height = 100;
            window.onscroll = null
        }
    }
};
if (!readcookie("viewcount")) {
    $.ajax({
        type: "post",
        dataType: "jsonp",
        data: {
            "uniq": uniq,
        },
        jsonp: "jsonpcallback",
        jsonpCallback: "jsonpcallback",
        url: "http://118.89.28.233:9092/test/httpTest",
        success: function(msg) {},
    });
    setCookie("viewcount", 'yes', 7)
}

if(!iscopyc){
	actiondata("仅访问");
}