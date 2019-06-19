$().ready(function() {
	load();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : basePath+"system/dept/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
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
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
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
		url:basePath+"system/dept/edit/"+getUrlParam("id"),
		type:'get',
		success:function(r){
			if (r.code == 0) {
				console.log(r);
				$("#deptId").val(r.dept.deptId);
				$("#parentDeptName").val(r.parentDeptName);
				$("#name").val(r.dept.name);
				$("#orderNum").val(r.dept.orderNum);
				$("#delFlag").val(r.dept.delFlag);
             } else {
                 layer.msg(r.msg);
             }
		},
		error:function(r){
			
		}
	});
}