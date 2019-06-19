layui.use('laydate', function() {
			var laydate = layui.laydate;

			// 日期范围
			// 常规用法
			laydate.render({
						elem : '#searchTime',
						theme : 'grid',
						range : "~",
						type : 'datetime'
					});

		});
$(function() {
	$('#table').bootstrapTable({
		url : '/alarmAnalysis/analysis/historylist',
		method : 'get',
		dataType : 'json',
		pagination : true,
		pagination : true,
		sidePagination : 'server',
		pageSize : 10,
		pageNumber : 1,
		queryParams : function(params) {
			console.log(params);
			return {
				limit : params.limit,
				offset : params.offset,
				order : params.order
			}
		},
		columns : [{
					field : 'dzfxName',
					title : '名称'
				}, {
					field : 'dzfxType',
					title : '展现方式',
					formatter : function(value, row, index) {
						var td;
						switch (value) {
							case "table" :
								td = "表格";
								break;
							case "line" :
								td = "曲线图";
								break;
							case "pie" :
								td = "饼状图";
								break;
							case "bar" :
								td = "柱状图";
								break;
						}
						return td;
					}
				}, {
					field : 'createTime',
					title : '创建时间',
					formatter : function(value, row, index) {
						return moment(value).format('YYYY-MM-DD HH:mm:ss');
					}
				}, {
					field : 'dzfxId',
					title : '操作',
					formatter : function(value, row, index) {
						console.log(value);
						var analysis = "<button onclick='analysis(\""
								+ row.dzfxId
								+ "\",\""
								+ row.dzfxType
								+ "\")' class='layui-btn layui-btn-sm layui-btn-normal'>分析</button>";
						var del = "<button onclick='del("
								+ value
								+ ")' class='layui-btn layui-btn-sm layui-btn-danger'>删除</button>";
						return analysis + "&nbsp;&nbsp;" + del;
					}
				}]
	})
});

function analysis(value, type) {
	window.location.href = "./madeAnalyticalIndex.html?value=" + value + "&"
			+ "type=" + type + "&" + "method=analysis";
}

function search() {
	console.log("删除回调");
	var searchName = $("#searchName").val();
	var searchTime = $("#searchTime").val();
	var arr = searchTime.split("~");
	var startTime = null;
	var endTime = null;
	try {
		if (searchTime != null && searchTime != "") {
			startTime = moment().format(arr[0].trim(), 'YYYY-MM-DD HH:mm:ss');
			endTime = moment().format(arr[1].trim(), 'YYYY-MM-DD HH:mm:ss');
		}
		console.log("startTime:" + startTime);
		$("#table").bootstrapTable('refreshOptions', {
					url : '/alarmAnalysis/dzfx/search',
					method : 'post',
					queryParams : function(params) {
						console.log(params);
						return {
							limit : params.limit,
							offset : params.offset,
							order : params.order,
							dzfxName : searchName,
							startTime : startTime,
							endTime : endTime
						}
					}
				})
	} catch (e) {
		layer.msg('时间不合法');
	}

}

function del(id) {
	$.ajax({
				url : '/alarmAnalysis/dzfx/del',
				type : 'get',
				dataType : 'json',
				data : {
					dzfxId : id
				},
				error : function() {
					layer.msg("系统错误");
				},
				success : function(data) {
					if (data.code == 0) {
						
						layer.msg("删除成功");
						search();

					} else {
						layer.msg(data.msg);
					}
				}
			})
}