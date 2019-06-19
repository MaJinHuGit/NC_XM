$().ready(function() {
	load();
	validateRule();
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
		url:basePath+"common/dict/edit/"+getUrlParam("id"),
		type:'get',
		success:function(r){
			if (r.code == 0) {
				console.log(r);
				$("#id").val(r.dict.id);
				$("#name").val(r.dict.name);
				$("#value").val(r.dict.value);
				$("#type").val(r.dict.type);
				$("#description").val(r.dict.description);
				$("#parentId").val(r.dict.parentId);
				$("#remarks").val(r.dict.remarks);
				$("#sort").val(r.dict.sort);
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
		url : basePath+"common/dict/update",
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