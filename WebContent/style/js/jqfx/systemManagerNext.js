var tablename = getUrlParam("tablename")
$(function(){
	
	load(tablename);
	
	$('#layerFieldAdd').on("click", function(){
		//iframe层

	  layer.open({
			id:1,
	        type: 2,
	        title:'新增系统',
			maxmin: true,
			skin:'layer-ext-moon',
	        area: ['550px','350px'],
			shade: 0.4,
			shadeClose: true,
			closeBtn: 1,
			content: 'systemManagerAddField.html?tablename='+tablename,
	     

	   });
	});
})

function load(tablename) {
	
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : "../jqfx/sys/getFields",
		iconSize : 'outline',
		toolbar : '#exampleToolbar',
		striped : false, // 设置为true会有隔行变色效果
		dataType : "json", // 服务器返回的数据类型
		pagination : true, // 设置为true会在底部显示分页条
		// queryParamsType : "limit",
		// //设置为limit则会发送符合RESTFull格式的参数
		singleSelect : false, // 设置为true将禁止多选
		// contentType : "application/x-www-form-urlencoded",
		// //发送到服务器的数据编码类型
		pageSize : 6, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		search : false, // 是否显示搜索框
		//showColumns : true, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
		queryParams : function(params) {
			var size = params.limit;
			var num = (params.offset / params.limit) + 1;
			return {
				//说明：传入后台的参数包括offset开始索引，limit步长
				limit : size*num+1,	
				offset :(num-1)*size,
				tablename:tablename
			};
		},
		columns : [
			{
				field : 'columnname',
				title : '序号',
				formatter : function(value, row, index) {
					return index+1;
				}
			//	switchable: false
			},
			{
				field : 'comments',
				title : '名称',
			//	switchable: false
			},
			{
				field : 'datatype',
				title : '类型',
				//switchable: false
			},
			{	
				title : '操作',
				field : 'type',
				align : 'center',
				//switchable: false,
				formatter : function(value, row, index) {
					var e = "<button onclick='update(\""+row.columnname+"\",\""+row.comments+"\",\""+row.datatype+"\")' class='layui-btn layui-btn-sm layui-btn-normal'>修改</button>"
		            +"&nbsp;&nbsp;<button onclick='deleteField(\""+row.tablename+"\",\""+row.columnname+"\")' class='layui-btn layui-btn-sm layui-btn-normal'>删除</button>"
					return e;
				}
			
			}]
		});
}

function update(columnname,comments,datatype){
	  layer.open({
			id:1,
	        type: 2,
	        title:'新增系统',
			maxmin: true,
			skin:'layer-ext-moon',
	        area: ['550px','350px'],
			shade: 0.4,
			shadeClose: true,
			closeBtn: 1,
			content: 'systemManagerUpdateField.html?tablename='+tablename+'&field='+columnname+'&comments='+encodeURI(comments)+'&datatype='+encodeURI(datatype),		
	   });
	
}

function deleteField(tablename,columnname){
	$.get("../jqfx/sys/deleteField",{tablename:tablename,field:columnname},function(data){
		
		if(data.code==0){
			location.reload();
		}
		
	})
}