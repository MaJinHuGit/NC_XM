var lb = decodeURI(parent.data["lb"]);
var bldw = decodeURI(parent.data["bldw"]);
var ksms = parent.data["ksms"];
var zxsj = parent.data["zxsj"];

$(function(){
	load();
})

function load() {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : "../jqfx/hddjq/getList",
		iconSize : 'outline',
		toolbar : '#exampleToolbar',
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
			return {
				//说明：传入后台的参数包括offset开始索引，limit步长
				limit : size*num+1,	
				offset :(num-1)*size,
				lb:lb,	
				bldw:encodeURI(bldw),	
				ksms:ksms,
				zxsj:zxsj,
				search:encodeURI($(".search").children(".input-outline").val())
			};
		},
		columns : [
			{
				field : 'lb',
				title : '报警类别',
				switchable: false,
				formatter : function(value, row, index) {
					var e = convert(row.lb)
					return e;
				}
			},
			{
				field : 'bjsj',
				title : '报警时间',
				switchable: false
			},
			{
				field : 'jbnr',
				title : '报警内容',
				width : '100px',
				switchable: false
			},
			{
				field : 'bldw',
				title : '办理单位'
			},
			{
				field : 'bljg',
				title : '办理结果'
			},
			{
				field : 'sqmj',
				title : '社区民警'
			},
			{
				field : 'bz',
				title : '备注'
			}
//			{	
//				title : '操作',
//				field : 'jjdbh',
//				align : 'center',
//				switchable: false,
//				formatter : function(value, row, index) {
//					var e = '<a class="btn btn-primary btn-sm ' + "" + '" href="#" mce_href="#" title="编辑" onclick="view(\''
//						+ row.jjdbh
//						+ '\')"><i class="fa fa-clone"></i></a> ';
//					return e;
//				}
//			}
			]
		});
}


function view(id) {
	data = {"jjdbh":id};
	layer.open({
		type : 2,
		title : '案件详情',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '450px' ],
		content : "hddjqView.html"
	});
}


function convert(lb) {
	if (lb == "01") {
		return "“黄”";
	} else if (lb == "02") {
		return "“赌”";
	} else if (lb == "03") {
		return "“毒”";
	} else {
		return lb;
	}
}
