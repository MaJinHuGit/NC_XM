var prefix = "system/menu"
$(function() {
	//加载根目录
	load();
	validateRule();
	//打开图标列表
    $("#ico-btn").click(function(){
    
        layer.open({
            type: 2,
			title:'图标列表',
            content: basePath+'/FontIcoList.html',
            area: ['480px', '90%'],
            success: function(layero, index){
                //var body = layer.getChildFrame('.ico-list', index);
                //console.log(layero, index);
            }
        });
    });
});
$.validator.setDefaults({
	submitHandler : function() {
		submit01();
	}
});
function submit01() {
	$.ajax({
		cache : true,
		type : "POST",
		url : basePath+prefix + "/save",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
			laryer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("保存成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				layer.alert(data.msg)
			}
		}
	});
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入菜单名"
			},
			type : {
				required : icon + "请选择菜单类型"
			}
		}
	})
}
/**
 * 初始化：加载表单的信息
 * @returns
 */
function load(){
	$.ajax({
		url:basePath+"system/menu/add/"+getUrlParam("pId"),
		type:'get',
		success:function(r){
			if (r.code == 0) {
				console.log(r);
				$("#pName").val(r.pName);
				$("#parentId").val(r.pId);
             } else {
                 layer.msg(r.msg);
             }
		},
		error:function(r){
			
		}
	});
}