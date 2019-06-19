var lx = decodeURI(parent.dataParams['lx']);
var ksms = parent.dataParams['ksms'];
var zxsj = parent.dataParams['zxsj'];
var point = parent.dataParams['point'];
var type = parent.dataParams['type'];
var name = decodeURI(parent.dataParams['name']);

$(function(){
	load();
})

function load() {
	var url = "";
	if(type==1){
		url = "../jqfx/bzjq/getList";
	}else if(type==2){
		url = "../jqfx/bzjq/getListBjxl";
	}else if(type==3){
		url = "../jqfx/bzjq/getListGxdw";
	}else if(type==4){
		url = "../jqfx/bzjq/getListBjdh";
	}else if(type==5){
		url = "../jqfx/bzjq/getListAfdz";
	}else if(type==6){
		url = "../jqfx/bzjq/getListGjz";
	}

	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : url,
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
				lx:encodeURI(lx),	
				ksms:ksms,
				zxsj:zxsj,
				point:point,
				name:encodeURI(name),
				search:encodeURI($(".search").children(".input-outline").val())
			};
		},
		columns : [
			{
				field : 'bjlb',
				title : '报警类别',
				switchable: false
			},
			{
				field : 'bjlx',
				title : '报警类型',
				switchable: false
			},
			{
				field : 'bjsj',
				title : '报警时间',
				width : '100px',
				switchable: false
			},
			{
				field : 'afdz',
				title : '案发地址'
			},
			{
				field : 'bjnr',
				title : '报警内容'
			},
			{
				field : 'gxdw',
				title : '管辖单位'
			},
			{	
				title : '操作',
				field : 'jjdbh',
				align : 'center',
				switchable: false,
				formatter : function(value, row, index) {
					var e = '<a class="btn btn-primary btn-sm ' + "" + '" href="#" mce_href="#" title="编辑" onclick="view(\''
						+ row.jjdbh
						+ '\')"><i class="fa fa-clone"></i></a> ';
					return e;
				}
			}]
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
		content : "bzjqView.html"
	});
}



	