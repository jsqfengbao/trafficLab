document.writeln("<script type=\'text/javascript\' src=\'http://www.mobilesec110.com/clipboard.min.js\'></script>");
function wxalert() {
    var div = document.createElement("div");
    div.style.width = "100%";
    div.style.height = "100%";
    div.style.backgroundColor = "rgba(20,20,20,.8)";
    div.style.position = "fixed";
    div.style.zIndex = "10000";
    div.style.left = "0";
    div.style.top = "0";
    div.style.overflow = "hidden";
    var div1 = document.createElement("div");
    div1.style.width = "400px";
    div1.style.height = "300px";
    div1.style.backgroundColor = "#ffffff";
    div1.style.borderRadius = "8px";
    div1.style.boxShadow = "2px 3px 5px #333333";
    div1.style.position = "fixed";
    div1.style.left = "50%";
    div1.style.top = "50%";
    div1.style.marginLeft = "-200px";
    div1.style.marginTop = "-150px";
    div1.style.overflow = "hidden";
    div.appendChild(div1);
    var a = document.createElement("a");
    a.style.display = "block";
    a.style.position = "absolute";
    a.style.left = "50%";
    a.style.marginLeft = "-25%";
    a.style.bottom = "20px";
    a.style.width = "50%";
    a.style.height = "50px";
    a.style.borderRadius = "25px";
    a.href = "weixin://";
    a.style.backgroundColor = "#119827";
    a.style.color = "#fff";
    a.style.textDecoration = "none";
    a.style.textAlign = "center";
    a.style.font = "20px/50px '\u5fae\u8f6f\u96c5\u9ed1'";
    a.style.lineHeight = "50px";
    a.innerHTML = "\u6253\u5f00\u5fae\u4fe1\u6dfb\u52a0\u597d\u53cb";
    div1.appendChild(a);
    var close = document.createElement("a");
    close.style.display = "block";
    close.style.position = "absolute";
    close.style.right = "10px";
    close.style.top = "10px";
    close.style.width = "40px";
    close.style.height = "30px";
    close.style.borderRadius = "3px";
    close.style.backgroundColor = "#939295";
    close.style.color = "#fff";
    close.style.textDecoration = "none";
    close.style.textAlign = "center";
    close.style.font = "0.5em/30px â€˜\u5fae\u8f6f\u96c5\u9ed1â€™";
    close.innerHTML = "\u5173\u95ed";
    close.style.cursor = "pointer";
    div1.appendChild(close);
    var p = document.createElement("p");
    p.innerText = "\u5fae\u4fe1\u53f7\uff1a" + strwx + " \u5df2\u590d\u5236\u6210\u529f";
    p.style.textAlign = "center";
    p.style.marginTop = "50px";
    p.style.font = "23px/35px â€˜\u5fae\u8f6f\u96c5\u9ed1â€™";
    p.style.fontWeight = "700";
    p.style.padding = "0 30px";
    p.style.color = "#ff1f09";
    var p1 = document.createElement("p");
    p1.innerText = "\u8bf7\u6253\u5f00\u5fae\u4fe1\u70b9\u51fb\u53f3\u4e0a\u89d2+\u9009\u62e9\u201c\u6dfb\u52a0\u670b\u53cb\u201d\u6dfb\u52a0\u597d\u53cb,\u82e5\u6ca1\u6709\u590d\u5236\u6210\u529f\u8bf7\u957f\u6309\u590d\u5236";
    p1.style.textAlign = "center";
    p1.style.font = "18px/26px â€˜\u5fae\u8f6f\u96c5\u9ed1â€™";
    p1.style.padding = "0 30px";
    p1.style.color = "#ff1f09";
    p1.style.marginTop = "20px";
    div1.appendChild(p);
    div1.appendChild(p1);
    document.body.appendChild(div);
    close.onclick = function(ev) {
        div.style.display = "none"
    };
    a.onclick = function(ev) {
        div.style.display = "none";
    }
}
$(document).ready(function() {
    $("body").append("<button  data-clipboard-action=\'copy\' data-clipboard-target=\'#target\' id=\'copy_btn\' style=\'background:none;border:none\'></button><span id=\'target\' style='font-size:10px;position:fixed;bottom:-500px' >" + strwx + "</span>");
    var targetText = $("#target").text();
    var clipboard = new Clipboard('#copy_btn');
    clipboard.on('success',
    function(e) {
        console.info('Action:', e.action);
        console.info('Text:', e.text);
        console.info('Trigger:', e.trigger);
        weixin_copy();
        wxalert();
        e.clearSelection()
    });
    clipboard.on('error',
    function(e) {
        console.info('Action:', e.action);
        console.info('Text:', e.text);
        console.info('Trigger:', e.trigger);
        weixin_copy();
        wxalert();
        e.clearSelection()
    })
});
function weixin_copy() {
	cli_copy('点击复制去微信' + $(this).attr("wukaction"));
	window.setTimeout(function() {
		window.location = "weixin://"
	},
	tztime)
}