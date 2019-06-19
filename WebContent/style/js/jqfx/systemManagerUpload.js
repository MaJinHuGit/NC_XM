var tablename = getUrlParam("tablename")
$(function(){
	$("#MB").attr("href","../jqfx/sys/downLoadFieldMb?tablename="+tablename)
	$("#tablename").val(tablename)
	getUploadFiled();
	//load();
	
})


function uplaodQx(){
	
	var layerIndex = parent.layer.getFrameIndex(window.name);

	parent.layer.close(layerIndex);
	parent.location.reload();
}

function sub(){
	var value = $("#sub").val();
	if(value==0){
		uplaodSubDb();
		$("#sub").val(1);
	}else{
		uplaodSub();
		$("#sub").val(0)
	}
}


/**
 * 上传建表文件信息
 * @Description ()
 * TODO 
 * @Author 
 * @Date 2019年1月10日 下午2:28:16
 */
function uplaodSubDb(){

 
    	
           $('#mainForm').ajaxSubmit(      //ajax方式提交表单
    			{
    				url: '../jqfx/sys/uploadFieldDb',
    				type: 'post',
    				dataType: 'json',
    				beforeSubmit: function () {},
    				success: function (data) {
    					if (data.code == 0) {
    						var html = "";
    						var dbtData = data.data;
    						
    						var length = ""
    						if(dbtData.length>5){
    							length = 5
    						}else{
    							length = dbtData.length
    						}
    						
    						for (var i = 0; i < length; i++) {
    							html +="<tr>";
								var str = dbtData[i].split(",");
								for (var j = 0; j < str.length; j++) {
									html +="<td>"+str[j]+"</td>";
								}
								html +="</tr>";
								
							}
    						$("#tbody").html(html);
    						
    						/*$("#uplaodSubDb").css("display","none");
    						$("#uplaodSub").css("display","block");*/
						}
    				},
    			
    				
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

           $('#mainForm').ajaxSubmit(      //ajax方式提交表单
    			{
    				url: '../jqfx/sys/uploadFields',
    				type: 'post',
    				dataType: 'json',
    				beforeSubmit: function () {},
    				success: function (data) {
    					if (data.code == 0) {
    						var layerIndex = parent.layer.getFrameIndex(window.name);
    						//setTimeout(function(){parent.layer.close(layerIndex)}, 2000);

    						parent.location.reload();
    						/*var html = "";
    						var dbtData = data.data;
    						
    						var length = ""
    						if(dbtData.length>5){
    							length = 5
    						}else{
    							length = dbtData.length
    						}
    						
    						for (var i = 0; i < length; i++) {
    							html +="<tr>";
								var str = dbtData[i].split(",");
								for (var j = 0; j < str.length; j++) {
									html +="<td>"+str[j]+"</td>";
								}
								html +="</tr>";
								
							}
    						$("#tbody").html(html);*/
    						
    					
						}
    				},
    			
    				
    			});
}


function getUploadFiled(){
	//$.ajaxSettings.async = false;
	$.get("../jqfx/sys/getUploadFiled",{tablename:tablename},function(data){
		dbtData = data.data;
	
		var str = dbtData.split(",")
		var html = "<tr>";
		for (var i = 0; i < str.length; i++) {
			
			html +="<th>"+str[i]+"</th>";
			
			
		}
		html +="</tr>";
		$("#thead").html(html);
	})
}

function uploadTable(){
	
	$("#test3").click();
	
	//var file = $("#test3").val();
	
	//var fileName = getFileName(file);

}
function getFilePath(){
//	C:\fakepath\Fileld-20190112111158.xls
	var file = $("#test3").val();
	var pos = file.lastIndexOf("\\");
	$("#fileName").html(file.substring(pos+1))
	
	//return o.sunstring(pos+1)
}
/*function load() {
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
					var e = "<button onclick='layerDemoNext(\""+row.tablename+"\")' class='layui-btn layui-btn-sm layui-btn-normal'>修改</button>"
		            +"&nbsp;&nbsp;<button onclick='layerDemoUpload(\""+row.tablename+"\")' class='layui-btn layui-btn-sm layui-btn-normal'>删除</button>"
					return e;
				}
			
			
			}]
		});
}*/

