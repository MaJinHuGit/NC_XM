
$(function(){
	load();
})
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
function getUrlParam2(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
   var r = window.location.search.substr(1).match(reg);  //匹配目标参数
   if (r != null) return decodeURI(r[2]); return null; //返回参数值,中文解码
}
function load() {
	var type = getUrlParam2("type");
	var name = getUrlParam2("name");
	var end = getUrlParam("end");
	var start = getUrlParam("start");
	$('#wffzxxTable').bootstrapTable("removeAll");
	$('#wffzxxTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : "../jqfx/showDetail/detil",
		iconSize : 'outline',
//		toolbar : '#exampleToolbar',
		striped : true, // 设置为true会有隔行变色效果
		dataType : "json", // 服务器返回的数据类型
		pagination : true, // 设置为true会在底部显示分页条
		// queryParamsType : "limit",
		// //设置为limit则会发送符合RESTFull格式的参数
		singleSelect : false, // 设置为true将禁止多选
		// contentType : "application/x-www-form-urlencoded",
		// //发送到服务器的数据编码类型
		pageSize : 10, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		search : true, // 是否显示搜索框
		//showColumns : true, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
		queryParams : function(params) {
			var size = params.limit;
			var num = (params.offset / params.limit) + 1;
			var searchs = "";
			if(typeof(params.search)!="undefined"){
				searchs = params.search;
			}
			return {
				//说明：传入后台的参数包括offset开始索引，limit步长
				limit : size*num+1,	
				offset :(num-1)*size,
				startTime:start,
				endTime:end,
				name:encodeURI(name),	
				type:encodeURI(type),
				search:encodeURI(searchs)
			};
		},
		columns : [
			{
				field : 'id',
				title : '案件主键',//
				switchable: false
			},
			{
				field : 'jqlydm',
				title : '警方来源代码',//
				switchable: false
			},
			{
				field : 'bjfsdm',//
				title : '接警方式代码'
			},
			{
				field : 'rqsj',
				title : '报警时间',
				formatter : function(value, row, index) {
					return dateFormat(row.rqsj);
				}
			},
			{	
				title : '接警人_姓名',//
				field : 'xm'
			},
			{	
				title : '报案人_姓名',//
				field : 'bxm'
			},
			{	
				title : '操作',
				field : 'oper',
				align : 'center',
				switchable: false,
				formatter : function(value, row, index) {
					var e = '<a class="btn btn-primary btn-sm ' + "" + '" href="#" mce_href="#" title="显示详情" onclick="showView(\''
						+ row.id
						+ '\')"><i class="fa fa-clone"></i></a> ';
					return e;
				}
			}]
		});
}

function dateFormat(time){
	var date = new Date(time);
	var year = date.getFullYear();
    var month = date.getMonth()+1;    //js从0开始取 
    if(month < 10){
    	month = '0'+ month;
    }
    var date1 = date.getDate();
    if(date1 < 10){
    	date1 = '0'+ date1;
    }
    var hour = date.getHours();
    if(hour < 10){
    	hour = '0'+ hour;
    }
    var minutes = date.getMinutes();
    if(minutes < 10){
    	minutes = '0'+ minutes;
    }
    var second = date.getSeconds();
    if(second < 10){
    	second = '0'+ second;
    }
    return year+"-"+month+"-"+date1+" "+hour+":"+minutes+":"+second;
}

function showView(id) {
	data = {"id":id};
	layer.open({
		type : 2,
		title : '案件详情',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '450px' ],
		content : "qqjqfxView.html"
	});
}