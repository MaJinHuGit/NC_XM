
$(function(){
	load();
	
	 loadExit();
	 
	
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
		pageSize : 4, // 如果设置了分页，每页数据条数
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
				field : 'count',
				title : '数量',
				//switchable: false
			}]
		});
}

/**
 * 已有资源
 * @Description ()
 * TODO 
 * @Author 
 * @Date 2019年1月10日 上午9:18:53
 * @returns
 */
function loadExit() {
	$('#exampleTable2').bootstrapTable({
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
			};
		},
		columns : [
			{
				field : 'comments',
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
				field : 'count',
				title : '数量',
				//switchable: false
			}]
		});
}

/**
 * 上传建表文件信息
 * @Description ()
 * TODO 
 * @Author 
 * @Date 2019年1月10日 下午2:28:16
 */
function uplaodSub(){

  //  $("#agreementSub").on("click",function(){
    	
           $('#mainForm').ajaxSubmit(      //ajax方式提交表单
    			{
    				url: '../jqfx/sys/createNewTable',
    				type: 'post',
    				dataType: 'json',
    				beforeSubmit: function () {},
    				success: function (data) {
    					if (data.code == 0) {
    						layer.msg("成功");
							
							var layerIndex = parent.layer.getFrameIndex(window.name);
							setTimeout(function(){parent.layer.close(layerIndex)}, 2000);
							
							parent.location.reload();
						}
    				},
    			
    				
    				//clearForm: false,//禁止清楚表单
    				//resetForm: false //禁止重置表单
    			});
  //  });
}

function uplaodQx(){
	
	var layerIndex = parent.layer.getFrameIndex(window.name);

			parent.layer.close(layerIndex);
}


function uploadTable(){
	$("#test3").click();
	var file = $("#test3").val();
	
	//var fileName = getFileName(file);

}
function getFilePath(){
//	C:\fakepath\Fileld-20190112111158.xls
	var file = $("#test3").val();
	var pos = file.lastIndexOf("\\");
	$("#fileName").html(file.substring(pos+1))
	
	//return o.sunstring(pos+1)
}

