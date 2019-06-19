
$(function(){
	load();
	
	
	$('#layerDemoAdd').on("click", function(){
		//iframe层

	  layer.open({
			id:1,
	        type: 2,
	        title:'新增系统',
			maxmin: true,
			skin:'layer-ext-moon',
	        area: ['950px','650px'],
			shade: 0.4,
			shadeClose: true,
			closeBtn: 1,
			content: 'systemManagerAdd.html',		
	      //  btn:['确定','取消'],
	      /*  btn1: function (index,layero) {
	    	},
	        btn2:function (index,layero) {
	        	 layer.close(index);
	        }*/

	   });
	});

	

})

function load() {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : "../jqfx/sys/getTable",
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
		pageSize : 10, // 如果设置了分页，每页数据条数
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
			};
		},
		columns : [
			{
				field : 'comments',
				title : '名称',
			//	switchable: false
			},
			{
				field : 'count',
				title : '数量',
				//switchable: false
			},
			{	
				title : '操作',
				field : 'jjdbh',
				align : 'center',
				//switchable: false,
				formatter : function(value, row, index) {
					var e = "<button onclick='layerDemoNext(\""+row.tablename+"\")' class='layui-btn layui-btn-sm layui-btn-normal'>下级</button>"
		            +"&nbsp;&nbsp;<button onclick='layerDemoUpload(\""+row.tablename+"\")' class='layui-btn layui-btn-sm layui-btn-normal'> 导入数据</button>"
					return e;
				}
			
			
			}]
		});
}
/**
 * 下级
 * @Description ()
 * TODO 
 * @Author 
 * @Date 2019年1月10日 上午9:13:01
 * @returns
 */
 function layerDemoNext(tablename){

	  layer.open({
			id:1,
	        type: 2,
	        title:'下级',
			maxmin: true,
			skin:'layer-ext-moon',
	        area: ['950px','580px'],
			shade: 0.4,
			shadeClose: true,
			closeBtn: 1, 
	        content: 'systemManagerNext.html?tablename='+tablename,	
	      /*  btn:['确定','取消'],
	        btn1: function (index,layero) {
	    	},
	        btn2:function (index,layero) {
	        	 layer.close(index);
	        }*/
	   });
	}

/**
 * 导入数据
 * @Description (tablename)
 * TODO 
 * @Author 
 * @Date 2019年1月10日 上午9:13:22
 * @returns
 */
	function  layerDemoUpload(tablename){
	  layer.open({
			id:1,
	        type: 2,
	        title:'导入数据',
			maxmin: true,
			skin:'layer-ext-moon',
	        area: ['950px','580px'],
			shade: 0.4,
			shadeClose: true,
			closeBtn: 1, 
	        content: 'systemManagerUpload.html?tablename='+tablename,	
	       /* btn:['确定','取消'],
	        btn1: function (index,layero) {
	    	},
	        btn2:function (index,layero) {
	        	 layer.close(index);
	        }
*/
	   });
	}