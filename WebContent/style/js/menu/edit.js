var prefix = "."
$(function() {
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
		update();
	}
});
/**
 * 初始化：加载表单的信息
 * @returns
 */
function load(){
	$.ajax({
		url:basePath+"system/menu/edit/"+getUrlParam("id"),
		type:'get',
		success:function(r){
			if (r.code == 0) {
				console.log(r);
				$("#name").val(r.menu.name);
				$("#pName").val(r.pName);
				$("#url").val(r.menu.url);
				$("#perms").val(r.menu.perms);
				$("#orderNum").val(r.menu.orderNum);
				$("#icon").val(r.menu.icon);
				$("#parentId").val(r.menu.parentId);
				$("#menuId").val(r.menu.menuId);
             } else {
                 layer.msg(r.msg);
             }
		},
		error:function(r){
			
		}
	});
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : basePath+"system/menu/update",
		data : $('#signupForm').serialize(),// 你的formid
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
function validate() {
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
