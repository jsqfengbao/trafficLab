var requestUrl; //请求的URL
var keyword;
var systemName;
var appName;  //浏览器的正式名称
var appVersion; //浏览器的版本号
var cookieEnabled; // 返回用户浏览器是否启用了cookie
var cpuClass; //返回用户计算机的cpu的型号，通常intel芯片返回"x86"（火狐没有）
var mimeType; // 浏览器支持的所有MIME类型的数组
var platform; // 浏览器正在运行的操作系统平台，包括Win16(windows3.x) 
var plugins; //  安装在浏览器上的所有插件的数组
var userLanguage; // 用户在自己的操作系统上设置的语言（火狐没有）
var userAgent; //包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform
var systemLanguage; // 用户操作系统支持的默认语言（火狐没有）
var browserType; //浏览器类型
var appCodeName; //与浏览器相关的内部代码名
var appMinorVersion; //辅版本号（通常应用于浏览器的补丁或服务包)
var language; //浏览器支持的语言 （IE没有）
var onLine; //返回浏览器是否处于在线模式（IE4以上版本）
var opsProfile; // 未定义   （IE、火狐没有）
var oscpu; //浏览器正在运行的操作系统，其中可能有CPU的信息（IE没有）
var product; // 浏览器的产品名（IE没有）
var productSub; //关于浏览器更多信息（IE没有）
var securityPolicy; // 浏览器支持的加密类型（IE没有）
var userProfile; //  返回一个UserProfile对象，它存储用户的个人信息（火狐没有）
var vender; // 浏览器厂商名称（IE、火狐没有）
var vendorSub; // 关于浏览器厂商更多的信息  
var fromurl, fromurla, nowurl, nowurla,strwx,weixin_rum,view_height = 5;
var visitIp = "",visitAddress = "";

weixin_rum = Math.floor(Math.random() * wuk_weixin.length);
strwx =  $(".wuk_weixin").first().text();// wuk_weixin[weixin_rum]; 
//var temp=$("wuk_weixin");
//console.log($(".wuk_weixin").first().text());
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
var getci = new Object();
getci = GetRequesta(decodeURI(decodeURI(fromurl)));
function getKeyWord() {
    if (nowurl.indexOf("notki") > 0) {
		keyword = "网盟主题词ID:" + getstr["notki"] + "|网盟创意ID:" + getstr["notct"] + "|网盟来源域名:" + getstr["notpl"] + "|网盟来源关键词:" + getstr["notkw"]
	} else if (fromurl.indexOf("baidu.com") > 0 && fromurl.indexOf("wd=") > 0) {
		keyword = getci['wd']
	} else if (fromurl.indexOf("baidu.com") > 0 && fromurl.indexOf("word=") > 0) {
		keyword = getci['word']
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
			keyword = getci['query']
		} else {
			keyword = getsogo()
		}
	} else if (fromurl.indexOf("soso.com") > 0) {
		keyword = getci['query=']
	} else if (fromurl.indexOf("so.com") > 0) {
		keyword = getci['q']
	} else if (fromurl.indexOf("sm.cn") > 0) {
		keyword = getci['q']
	} else {
		keyword = ""
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
var iscopyc = true;
function cli_copy() {
    if (iscopyc) {
        iscopyc = false;
        submitData('update')
    }
}
$(function() {
    $(".wuk_weixin").html("<button  data-clipboard-action='copy' data-clipboard-target='#target' id='copy_btn' style='background:none;border:none;padding:0px;margin:0px'>" + strwx + "</button>")
    $(".wuk_tel").html("电话号");
    $(".wuk_tel_href").attr('href', 'tel:' + "电话号码");
    $(".wuk_ewm").attr("src", "二维码连接")
});
$(function() {
    isclick = false;
});
function getSystemName() { // 获取当前操作系统
    if (navigator.userAgent.indexOf('Android') > -1 || navigator.userAgent.indexOf('Linux') > -1) {
        systemName = 'Android';
    } else if (navigator.userAgent.indexOf('iPhone') > -1||navigator.userAgent.indexOf('iPad') > -1) {
        systemName = 'iOS';
    } else if (navigator.userAgent.indexOf('Windows Phone') > -1) {
        systemName = 'WP';
    } else {
        systemName = 'Others';
    }
    return systemName;
}
//判断是否IE
function isIe() {
	var i = navigator.userAgent.toLowerCase().indexOf("msie");
	return i >= 0;
}
//判断是否firefox
function isFireFox() {
	var i = navigator.userAgent.toLowerCase().indexOf("firefox");
	return i >= 0;
}

function getAllinfo() {
	appName = navigator.appName; //浏览器的正式名称
	appVersion = navigator.appVersion; //浏览器的版本号
	cookieEnabled = navigator.cookieEnabled; // 返回用户浏览器是否启用了cookie
	cpuClass = navigator.cpuClass; //返回用户计算机的cpu的型号，通常intel芯片返回"x86"（火狐没有）

	mimeType = navigator.mimeTypes; // 浏览器支持的所有MIME类型的数组
	platform = navigator.platform; // 浏览器正在运行的操作系统平台，包括Win16(windows3.x)  
	//   Win32(windows98,Me,NT,2000,xp),Mac68K(Macintosh 680x0)
	//     和ＭacPPC(Macintosh PowerPC)
	plugins = navigator.plugins; //  安装在浏览器上的所有插件的数组
	userLanguage = navigator.userLanguage; // 用户在自己的操作系统上设置的语言（火狐没有）
	userAgent = navigator.userAgent; //包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform
	systemLanguage = navigator.systemLanguage; // 用户操作系统支持的默认语言（火狐没有）
	browserType = "";
	if (isIe()) {
		browserType = "IE浏览器";
	} else if (isFireFox()) {
		browserType = "火狐浏览器";
	}
	appCodeName = navigator.appCodeName; //与浏览器相关的内部代码名
	appMinorVersion = navigator.appMinorVersion; //辅版本号（通常应用于浏览器的补丁或服务包)
	language = navigator.language; //浏览器支持的语言 （IE没有）
	onLine = navigator.onLine; //返回浏览器是否处于在线模式（IE4以上版本）
	opsProfile = navigator.opsProfile; // 未定义   （IE、火狐没有）
	oscpu = navigator.oscpu; //浏览器正在运行的操作系统，其中可能有CPU的信息（IE没有）
	product = navigator.product; // 浏览器的产品名（IE没有）
	productSub = navigator.productSub; //关于浏览器更多信息（IE没有）
	securityPolicy = navigator.securityPolicy; // 浏览器支持的加密类型（IE没有）
	userProfile = navigator.userProfile; //  返回一个UserProfile对象，它存储用户的个人信息（火狐没有）
	vender = navigator.vender; // 浏览器厂商名称（IE、火狐没有）
	vendorSub = navigator.vendorSub; // 关于浏览器厂商更多的信息  
}

getKeyWord();
getSystemName();
getAllinfo();
var url = "http://www.mobilesec110.com/manager/jingjia/jjgetinfo/save";
data = {
	"wechat":strwx,
	"visitIp":visitIp,
	"visitAddress":visitAddress,
	"operateType":1,
	"productId":uniq,
	"viewPercent": view_height + "%",
	"keyword":keyword,
	"visitUrl":nowurl,
	"systemName":systemName,
	"appName":appName,
	"appVersion":appVersion,
	"cookieEnabled":cookieEnabled,
	"cpuClass":cpuClass,
	"platform":platform,
	"userLanguage":userLanguage,
	"userAgent":userAgent,
	"systemLanguage":systemLanguage,
	"browserType":browserType,
	"appCodeName":appCodeName,
	"appMinorVersion":appMinorVersion,
	"language":language,
	"onLine":onLine,
	"opsProfile":opsProfile,
	"oscpu":oscpu,
	"product":product,
	"productSub":productSub,
	"securityPolicy":securityPolicy,
	"userProfile":userProfile,
	"vender":vender,
	"vendorSub":vendorSub
}
function submitData(clickType){
	if(clickType == 'update'){
		url = "http://www.mobilesec110.com/manager/jingjia/jjgetinfo/updateInfo";
		data.operateType = 2;
	}
	$.ajax({
	  url: "http://pv.sohu.com/cityjson?ie=utf-8",
	  dataType: "script",
	  success: function(){
		 data.visitIp = returnCitySN["cip"];
		 data.visitAddress = returnCitySN["cname"];
		 $.ajax({
			type:"post",
			dataType: "json",
			jsonp: "jsonpcallback",
       			jsonpCallback: "jsonpcallback",
			url:url,
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
			success: function (message) {
				
			},
			error: function (message) {
				
			}
		});	
	  }
	});
}

submitData('仅点击');
