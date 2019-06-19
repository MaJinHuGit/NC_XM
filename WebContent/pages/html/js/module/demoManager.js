$('#layerDemo').on("click", function() {

	layer.open({
		id : 1,
		icon : 1,
		type : 2,
		title : '新增模块',
		skin : 'layer-ext-moon',
		area : [ '100%', '100%' ],
		shade : 0.4,
		content : 'addDemoManager.html',
		btn : [ '确定', '取消' ],
		btn1 : function(index, layero) {
			var iframeWin = window[layero.find('iframe')[0]['name']];
			iframeWin.callTest(index);
		},
		btn2 : function(index, layero) {
			layer.close(index);
		}

	});
});
$(function() {
	$('#table').bootstrapTable({
		url : '/alarmAnalysis/module/all',
		toolbar:'#exampleToolbar',
		method : 'get',
		dataType : 'json',
		pagination : true,
		pagination:true,
		sidePagination:'server',
		pageSize : 10,
		pageNumber : 1,
		queryParams : function(params) {
			console.log(params);
			return {
				limit : params.limit,
				offset : params.offset,
				order : params.order,
				sort : 'sysModuleOrder'
			}
		},
		columns : [ {
			field : 'sysModuleName',
			title : '名称'
		}, {
			field : 'sysModuleOrder',
			title : '顺序'
		}, {
			field : 'sysModuleState',
			title : '状态',
			formatter : function(value,row,index){
				var td;
				if(value>0){
					 td="<button class='layui-btn layui-btn-sm layui-btn-primary'>隐藏</button>"
						+"&nbsp;&nbsp;"
						+"<button class='layui-btn layui-btn-sm'>显示</button>";
				}else{
					 td="<button class='layui-btn layui-btn-sm'>隐藏</button>"
							+"&nbsp;&nbsp;"
							+"<button class='layui-btn layui-btn-sm layui-btn-primary'>显示</button>";
				}
				return td;
			}
		}, {
			field : 'sysModuleId',
			title : '操作',
			formatter : function(value,row,index){
				console.log(value);
				var td="<button onclick='deleteModule("+value+")' class='layui-btn layui-btn-sm layui-btn-danger'>删除</button>";
				return td;
			}
		} ]
	})
});

function callback(data, index) {
	layer.close(index);
	var option={
			queryParams:function(params){
				return {
					limit : params.limit,
					offset : params.offset,
					order : params.order,
					sort : 'sysModuleOrder'
				}
			}
	}
	$('#table').bootstrapTable('refresh',option);
}

function deleteModule(id){
	$.ajax({
		url:"/alarmAnalysis/module/remove",
		type:"post",
		dataType:"json",
		data:{
			id:id
		},
		error:function(){
			alert("系统错误");
		},
		success:function(data){
			if(data.code==0){
				var option={
						queryParams:function(params){
							return {
								limit : params.limit,
								offset : params.offset,
								order : params.order,
								sort : 'sysModuleOrder'
							}
						}
				}
				$('#table').bootstrapTable('refresh',option);
			}else{
				alert("删除失败");
			}	
		}
	});
}