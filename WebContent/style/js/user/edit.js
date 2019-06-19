// 以下为官方示例
$().ready(function() {
	load();
	validateRule();
	// $("#signupForm").validate();
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
		url:basePath+"system/user/edit/"+getUrlParam("id"),
		type:'get',
		success:function(r){
			if (r.code == 0) {
				console.log(r);
				$("#userId").val(r.user.userId);
				$("#name").val(r.user.name);
				$("#username").val(r.user.username);
				$("#deptId").val(r.user.deptId);
				$("#deptName").val(r.user.deptName);
				$("#email").val(r.user.email);
				$("#roleIds").val(r.user.roleIds);
				$("#menuId").val(r.user.menuId);
				//设置用户状态
				r.user.status==0?$("#err").attr("checked",true):$("#success").attr("checked",true);
				//角色
				var roles=r.roles;
				$("#roles").append("");
				var html="";
				for (var i = 0; i < roles.length; i++) {
					html+="<label class='checkbox-inline'>";
					html+="<input name='role' type='checkbox' value='"+roles[i].roleId+"' ";
					html+=" th:checked='${role.roleSign}'> "+roles[i].roleName;
					html+="</label>";
				}
				$("#roles").append(html);
			
             } else {
                 layer.msg(r.msg);
             }
		},
		error:function(r){
			
		}
	});
}
function update() {
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : basePath+"system/user/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function setCheckedRoles() {
	var roleIds = $("#roleIds").val();
	alert(roleIds);
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			username : {
				required : true,
				minlength : 2
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			email : {
				required : true,
				email : true
			},
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
			agree : "required"
		},
		messages : {

			name : {
				required : icon + "请输入姓名"
			},
			username : {
				required : icon + "请输入您的用户名",
				minlength : icon + "用户名必须两个字符以上"
			},
			password : {
				required : icon + "请输入您的密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirm_password : {
				required : icon + "请再次输入密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
			},
			email : icon + "请输入您的E-mail",
		}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:basePath+"/dept/deptTree.html"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}