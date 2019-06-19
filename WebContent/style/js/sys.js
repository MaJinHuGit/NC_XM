var basePath="/alarmAnalysis/"; 

$( function() {
	cklogin() ;
});

/**
 * 获取url中参数
 * @param name
 * @returns
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
/**
 * getUrlParam2:()
 * TODO(获取url中文参数)
 * @dateTime: 2018年9月30日 上午10:56:59
 * @param: @param name
 * @param: @returns
 * @return: any
*/
function getUrlParam2(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
   var r = window.location.search.substr(1).match(reg);  //匹配目标参数
   if (r != null) return decodeURI(r[2]); return null; //返回参数值,中文解码
}
//检查登录
function cklogin() {
    $.ajax({
        type: "POST",
        url: "cklogin",
        success: function (r) {
             if (r.code == 0) {
                 
            } else {
                //layer.msg(r.msg);
                //$("body").show();
            	top.location.href = basePath; 
            }
        },
    });
}