$(function(){
	$.ajax({
		url:"/alarmAnalysis/module/list",
		type:"get",
		dataType:"json",
		data:{
			sort:"sysModuleOrder",
			sysModuleState:"1",
			order:"asc"
		},
		error:function(){
			alert("系统错误");
		},
		success:function(data){
			data.forEach(function(module){
				var tab="<li>"
						+"<a href='#"+module.sysModuleId+"' data-toggle='pill'>"+module.sysModuleName+"</a>"
					+"</li>";
				var tab_content="<div class='tab-pane fade in' style='background:white;' id='"+module.sysModuleId+"'>"
								+"<div class='layui-row' style='margin:16px;'>"
								+module.sysModuleText
								+"</div>"
								+"</div>"
				$("#tab").append(tab);
				$("#tab-content").append(tab_content);
			});
		}
	});
});