var filterdata = new Array();
var qxtfilterdata = new Array();
var bztfilterdata = new Array();
var zztfilterdata = new Array();
var zbs;
var qxtzbs;
var bztzbs;
var zztzbs;
var columns;
var tabchange = false;
var charts = [];
var chart1 = echarts.init(document.getElementById("barEchart"));
var chart2 = echarts.init(document.getElementById("pieEchart"));
var chart3 = echarts.init(document.getElementById("lineEchart"));
$(function() {
			zbs = new Array();
			qxtzbs = new Array();
			bztzbs = new Array();
			zztzbs = new Array();
			var url = window.location.href.split("?")[1];
			var res = {};
			var arr = [];
			if (url != null) {
				var params = url.split("&");
				for (var i = 0; i < params.length; i++) {
					arr = params[i].split("=");
					res[arr[0]] = arr[1];
				}
			}
			echartsInit();
			if (res.value != null && res.value != "") {
				switch (res.method) {
					case "analysis" :
						historyAnalysis(res.value, res.type);
						break;
				}
			} else {
				init();
			}

		});
function historyAnalysis(value, type) {
	$.ajax({
				url : "/alarmAnalysis/dzfx/getById",
				type : "get",
				dataType : "json",
				data : {
					dzfxId : value
				},
				error : function() {
					alert("系统错误");
				},
				success : function(data) {
					switch (type) {
						case "table" :
							tabchange = true;
							tableAnalysisInit(data);
							break;
						case "line" :
							tabchange = true;
							lineAnalysisInit(data);
							break;
						case "pie" :
							tabchange = true;
							pieAnalysisInit(data);
							break;
						case "bar" :
							tabchange = true;
							barAnalysisInit(data);
							break;
					}
				}
			})
}
/**
 * 历史分析中分析按钮用方法，针对表格的历史分析
 * 
 * @param {}
 *            data
 */
function tableAnalysisInit(data) {
	$("#tab_1").tab("show");
	var jsondata = JSON.parse(data.dzfxText);
	var initParam = {
		bm : jsondata.bm,
		wdm : jsondata.wdm,
		bmSelector : "#selectTable",
		colSelector : "#column",
		type : "table"
	};

	historyBmInit(initParam);
	historyWdInit(initParam);
	zbs = jsondata.zb;

	jsondata.zb.forEach(function(zbItem, index) {
				addHistoryZhibiao(zbItem.zbm, index);
				var i = index;
				zbItem.filters.forEach(function(filter) {
							showFilter(i);
						});
			});
	$("#saveTable").attr("value", data.dzfxId);
	$("#saveTable").attr("data", data.dzfxName);
	console.log("sdfasdf");
	console.log(zbs);
}
/**
 * 曲线图历史记录初始化
 * 
 * @param {}
 *            data
 */
function lineAnalysisInit(data) {

	$("#tab_2").tab("show");
	var jsondata = JSON.parse(data.dzfxText);
	var initParam = {
		bm : jsondata.bm,
		wdm : jsondata.wdm,
		bmSelector : "#qxtTable",
		colSelector : "#qxtColumn",
		type : "line"
	};

	historyBmInit(initParam);
	historyWdInit(initParam);
	qxtzbs = jsondata.zb;

	jsondata.zb.forEach(function(zbItem, index) {
				addHistoryLineZhibiao(zbItem.zbm, index);
				var i = index;
				zbItem.filters.forEach(function(filter) {
							showQxtFilter(i);
						});

			});
	$("#saveLine").attr("value", data.dzfxId);
	$("#saveLine").attr("data", data.dzfxName);
	console.log("sdfasdf");
	console.log(qxtzbs);
}
/**
 * 饼状图历史分析初始化
 * 
 * @param {}
 *            data
 */
function pieAnalysisInit(data) {

	$("#tab_3").tab("show");
	var jsondata = JSON.parse(data.dzfxText);
	var initParam = {
		bm : jsondata.bm,
		wdm : jsondata.wdm,
		bmSelector : "#bztTable",
		colSelector : "#btzColumn",
		type : "pie"
	};

	historyBmInit(initParam);
	historyWdInit(initParam);
	bztzbs = jsondata.zb;

	$("#savePie").attr("value", data.dzfxId);
	$("#savePie").attr("data", data.dzfxName);
	console.log("sdfasdf");
	console.log(qxtzbs);
}
/**
 * 状图图历史分析初始化
 * 
 * @param {}
 *            data
 */
function barAnalysisInit(data) {

	$("#tab_4").tab("show");
	var jsondata = JSON.parse(data.dzfxText);
	var initParam = {
		bm : jsondata.bm,
		wdm : jsondata.wdm,
		bmSelector : "#zztTable",
		colSelector : "#zztColumn",
		type : "bar"
	};

	historyBmInit(initParam);
	historyWdInit(initParam);
	zztzbs = jsondata.zb;

	jsondata.zb.forEach(function(zbItem, index) {
				addHistoryBarZhibiao(zbItem.zbm, index);
				var i = index;
				zbItem.filters.forEach(function(filter) {
							showZztFilter(i);
						});

			});

	$("#saveBar").attr("value", data.dzfxId);
	$("#saveBar").attr("data", data.dzfxName);
	console.log("sdfasdf");
	console.log(qxtzbs);
}

/**
 * 历史分析资源名称的初始化
 * 
 * @param {}
 *            initParam 定制分析查询参数对象
 */
function historyBmInit(initParam) {
	$.ajax({
				url : "/alarmAnalysis/analysis/selectTable",
				type : "get",
				dataType : "json",
				error : function() {
					alert("系统错误")
				},
				success : function(data) {
					console.log(data);
					console.log(initParam);
					var html = '<option>无</option>';
					data.forEach(function(table) {
								console.log("bm:"
										+ (table.tableName == initParam.bm));
								if (table.tableName == initParam.bm) {
									html += '<option selected="selected" value="'
											+ table.tableName
											+ '">'
											+ table.comments + '</option>';
								} else {
									html += '<option value="' + table.tableName
											+ '">' + table.comments
											+ '</option>';
								}

							});
					$(initParam.bmSelector).html(html);
					switch (initParam.type) {
						case "table" :
							lineInit(data);
							pieInit(data);
							barInit(data);
							break;
						case "line" :
							tableInit(data);
							pieInit(data);
							barInit(data);
							break;
						case "pie" :
							tableInit(data);
							lineInit(data);
							barInit(data);
							break;
						case "bar" :
							tableInit(data);
							lineInit(data);
							pieInit(data);
							break;
					}
				}
			});
}
/**
 * 历史分析维度初始化
 * 
 * @param {}
 *            initParam 定制分析查询参数对象
 */
function historyWdInit(initParam) {
	$.ajax({
				url : "/alarmAnalysis/analysis/selectCol",
				type : "get",
				dataType : "json",
				data : {
					tableName : initParam.bm
				},
				error : function() {
					alert("系统错误")
				},
				success : function(data) {
					console.log(data);
					columns = data
					var html = "";
					data.forEach(function(column) {
								if (column.columnName == initParam.wdm) {
									html += '<option selected="selected" value="'
											+ column.columnName
											+ '">'
											+ column.columnComments
											+ '</option>';
								} else {
									html += '<option value="'
											+ column.columnName + '">'
											+ column.columnComments
											+ '</option>';
								}

							})
					$(initParam.colSelector).html(html);
				}
			});
}
/**
 * 页面打开初始化方法
 */
function init() {

	$.ajax({
				url : "/alarmAnalysis/analysis/selectTable",
				type : "get",
				dataType : "json",
				error : function() {
					alert("系统错误")
				},
				success : function(data) {
					tableInit(data);
					lineInit(data);
					pieInit(data);
					barInit(data);
				}

			});
}
/**
 * 表格定制分析查詢条件初始化
 * 
 * @param {}
 *            data
 */
function tableInit(data) {
	var html = '<option>无</option>';
	data.forEach(function(table) {
				html += '<option value="' + table.tableName + '">'
						+ table.comments + '</option>';
			})
	$("#selectTable").html(html);
	$("#column").html('<option>无</option>');
}
/**
 * 曲线图定制查询分析条件初始化
 * 
 * @param {}
 *            data
 */
function lineInit(data) {
	var html = '<option>无</option>';
	data.forEach(function(table) {
				html += '<option value="' + table.tableName + '">'
						+ table.comments + '</option>';
			})
	$("#qxtTable").html(html);
	$("#qxtColumn").html('<option>无</option>');
}
/**
 * 饼状图定制分析查询条件初始化
 * 
 * @param {}
 *            data
 */
function pieInit(data) {
	var html = '<option>无</option>';
	data.forEach(function(table) {
				html += '<option value="' + table.tableName + '">'
						+ table.comments + '</option>';
			})
	$("#bztTable").html(html);
	$("#btzColumn").html('<option>无</option>');
}
/**
 * 柱状图定制分析查询条件初始化
 * 
 * @param {}
 *            data
 */
function barInit(data) {
	var html = '<option>无</option>';
	data.forEach(function(table) {
				html += '<option value="' + table.tableName + '">'
						+ table.comments + '</option>';
			})
	$("#zztTable").html(html);
	$("#zztColumn").html('<option>无</option>');
}
/**
 * 表格资源库选择条件改变事件
 */
$("#selectTable").change(function() {
	console.log($(this).val());
	var tableName = $(this).val();
	if (tableName != null) {
		$.ajax({
					url : "/alarmAnalysis/analysis/selectCol",
					type : "get",
					dataType : "json",
					data : {
						tableName : tableName
					},
					error : function() {
						alert("系统错误")
					},
					success : function(data) {
						console.log(data);
						columns = data
						var html;
						data.forEach(function(column) {
									html += '<option value="'
											+ column.columnName + '">'
											+ column.columnComments
											+ '</option>';
								})
						$("#column").html(html);
					}
				})
	}
});
/**
 * 曲线图资源库选择条件改变事件
 */
$("#qxtTable").change(function() {
	var tableName = $(this).val();
	if (tableName != null) {
		$.ajax({
					url : "/alarmAnalysis/analysis/selectCol",
					type : "get",
					dataType : "json",
					data : {
						tableName : tableName
					},
					error : function() {
						alert("系统错误")
					},
					success : function(data) {
						console.log(data);
						columns = data
						var html;
						data.forEach(function(column) {
									html += '<option value="'
											+ column.columnName + '">'
											+ column.columnComments
											+ '</option>';
								})
						$("#qxtColumn").html(html);
					}
				})
	}
});
/**
 * 饼状图资源库选择条件改变事件
 */
$("#bztTable").change(function() {
	var tableName = $(this).val();
	if (tableName != null) {
		$.ajax({
					url : "/alarmAnalysis/analysis/selectCol",
					type : "get",
					dataType : "json",
					data : {
						tableName : tableName
					},
					error : function() {
						alert("系统错误")
					},
					success : function(data) {
						console.log(data);
						columns = data
						var html;
						data.forEach(function(column) {
									html += '<option value="'
											+ column.columnName + '">'
											+ column.columnComments
											+ '</option>';
								})
						$("#btzColumn").html(html);
					}
				})
	}
});
/**
 * 柱状图资源库选择条件改变事件
 */
$("#zztTable").change(function() {
	var tableName = $(this).val();
	if (tableName != null) {
		$.ajax({
					url : "/alarmAnalysis/analysis/selectCol",
					type : "get",
					dataType : "json",
					data : {
						tableName : tableName
					},
					error : function() {
						alert("系统错误")
					},
					success : function(data) {
						console.log(data);
						columns = data
						var html;
						data.forEach(function(column) {
									html += '<option value="'
											+ column.columnName + '">'
											+ column.columnComments
											+ '</option>';
								})
						$("#zztColumn").html(html);
					}
				})
	}
});
/**
 * 添加表格的指标
 * 
 * @param {}
 *            data
 */
function addZhibiao(data) {
	filterdata.push(data);
	var index = filterdata.findIndex(function(e) {
				return e == data;
			});
	var data = '<div class="divideLine">'
			+ '	<div class="chooseData">'
			+ '		<span class="leftTextName">'
			+ data
			+ '</span>'
			+ '		<div class="rightIconDate chooseDataCon" onclick="filterClick('
			+ index
			+ ')" title="筛选"><i class="icon iconfont">&#xe7ff;</i></div>'
			+ '	</div>' + '	<div id="zb' + index + '">' + '	</div>';
	$("#zhibiaoValue").append(data);
	var zbdata = {
		zbm : filterdata[index],
		filters : new Array()
	}
	zbs.push(zbdata);
}
/**
 * 添加表格历史分析的指标
 * 
 * @param {}
 *            data
 * @param {}
 *            index
 */
function addHistoryZhibiao(data, index) {
	// var index = filterdata.findIndex(function(e) {
	// return e == data;
	// });
	filterdata[index] = data;
	var data = '<div class="divideLine">'
			+ '	<div class="chooseData">'
			+ '		<span class="leftTextName">'
			+ data
			+ '</span>'
			+ '		<div class="rightIconDate chooseDataCon" onclick="filterClick('
			+ index
			+ ')" title="筛选"><i class="icon iconfont">&#xe7ff;</i></div>'
			+ '	</div>' + '	<div id="zb' + index + '">' + '	</div>';
	$("#zhibiaoValue").append(data);
}
/**
 * 添加曲线图历史分析指标
 * 
 * @param {}
 *            data
 * @param {}
 *            index
 */
function addHistoryLineZhibiao(data, index) {
	qxtfilterdata[index] = data;
	var data = '<div class="divideLine">'
			+ '	<div class="chooseData">'
			+ '		<span class="leftTextName">'
			+ data
			+ '</span>'
			+ '		<div class="rightIconDate chooseDataCon" onclick="filterQxtClick('
			+ index
			+ ')" title="筛选"><i class="icon iconfont">&#xe7ff;</i></div>'
			+ '	</div>' + '	<div id="qxtzb' + index + '">' + '	</div>';
	$("#qxtZhiBiao").append(data);
}
/**
 * 添加曲线图指标
 * 
 * @param {}
 *            data
 */
function addQxtZhibiao(data) {
	qxtfilterdata.push(data);
	var index = qxtfilterdata.findIndex(function(e) {
				return e == data;
			});
	var data = '<div class="divideLine">'
			+ '	<div class="chooseData">'
			+ '		<span class="leftTextName">'
			+ data
			+ '</span>'
			+ '		<div class="rightIconDate chooseDataCon" onclick="filterQxtClick('
			+ index
			+ ')" title="筛选"><i class="icon iconfont">&#xe7ff;</i></div>'
			+ '	</div>' + '	<div id="qxtzb' + index + '">' + '	</div>';
	$("#qxtZhiBiao").append(data);
	var zbdata = {
		zbm : qxtfilterdata[index],
		filters : new Array()
	}
	qxtzbs.push(zbdata);
}
/**
 * 添加柱状图指标
 * 
 * @param {}
 *            data
 */
function addZztZhibiao(data) {
	zztfilterdata.push(data);
	var index = zztfilterdata.findIndex(function(e) {
				return e == data;
			});
	var data = '<div class="divideLine">'
			+ '	<div class="chooseData">'
			+ '		<span class="leftTextName">'
			+ data
			+ '</span>'
			+ '		<div class="rightIconDate chooseDataCon" onclick="filterZztClick('
			+ index
			+ ')" title="筛选"><i class="icon iconfont">&#xe7ff;</i></div>'
			+ '	</div>' + '	<div id="zztzb' + index + '">' + '	</div>';
	$("#zztZhiBiao").append(data);
	var zbdata = {
		zbm : zztfilterdata[index],
		filters : new Array()
	}
	zztzbs.push(zbdata);
}
/**
 * 添加柱状图历史分析指标
 * 
 * @param {}
 *            data
 * @param {}
 *            index
 */
function addHistoryBarZhibiao(data, index) {
	zztfilterdata[index] = data;
	var data = '<div class="divideLine">'
			+ '	<div class="chooseData">'
			+ '		<span class="leftTextName">'
			+ data
			+ '</span>'
			+ '		<div class="rightIconDate chooseDataCon" onclick="filterZztClick('
			+ index
			+ ')" title="筛选"><i class="icon iconfont">&#xe7ff;</i></div>'
			+ '	</div>' + '	<div id="zztzb' + index + '">' + '	</div>';
	$("#zztZhiBiao").append(data);
}
/**
 * 打开表格的分析查询参数的过滤条件页面
 * 
 * @param {}
 *            filterIndex
 */
function filterClick(filterIndex) {
	layer.open({
				id : 1,
				icon : 1,
				type : 2,
				title : '筛选条件',
				skin : 'layer-ext-moon',
				area : ['950px', '600px'],
				shade : 0.6,
				content : 'chooseData.html',
				btn : ['确定', '取消'],
				btn1 : function(index, layero) {
					var iframeWin = window[layero.find('iframe')[0]['name']];
					var Data = iframeWin.returndata();
					var i = zbs.findIndex(function(zbdata) {
								return zbdata.zbm == filterdata[filterIndex];
							});
					layer.close(index);
					zbs[i].filters = Data;
					console.log("json:" + JSON.stringify(zbs));
					showFilter(i);
				},
				btn2 : function(index, layero) {
					layer.close(index);
				},
				success : function(layero, index) {
					var iframeWin = window[layero.find('iframe')[0]['name']];
					console.log(filterdata);
					var i = zbs.findIndex(function(zbdata) {
								return zbdata.zbm == filterdata[filterIndex];
							});
					console.log("----" + i);
					iframeWin.initCondition(columns, filterdata[filterIndex],
							zbs[i].filters)
				}

			});
}
/**
 * 打开曲线图分析查询参数的过滤条件页面
 * 
 * @param {}
 *            filterIndex
 */
function filterQxtClick(filterIndex) {
	layer.open({
				id : 1,
				icon : 1,
				type : 2,
				title : '筛选条件',
				skin : 'layer-ext-moon',
				area : ['950px', '600px'],
				shade : 0.6,
				content : 'chooseData.html',
				btn : ['确定', '取消'],
				btn1 : function(index, layero) {
					var iframeWin = window[layero.find('iframe')[0]['name']];
					var Data = iframeWin.returndata();
					var i = qxtzbs.findIndex(function(zbdata) {
								return zbdata.zbm == qxtfilterdata[filterIndex];
							});
					layer.close(index);
					qxtzbs[i].filters = Data;
					console.log("qxtzbs:" + qxtzbs);
					showQxtFilter(i);
				},
				btn2 : function(index, layero) {
					layer.close(index);
				},
				success : function(layero, index) {
					var iframeWin = window[layero.find('iframe')[0]['name']];
					console.log(qxtfilterdata);
					var i = qxtzbs.findIndex(function(zbdata) {
								return zbdata.zbm == qxtfilterdata[filterIndex];
							});
					console.log("----" + i);
					iframeWin.initCondition(columns,
							qxtfilterdata[filterIndex], qxtzbs[i].filters)
				}

			});
}
/**
 * 打开柱状图分析查询条件的过滤页面
 * 
 * @param {}
 *            filterIndex
 */
function filterZztClick(filterIndex) {
	layer.open({
				id : 1,
				icon : 1,
				type : 2,
				title : '筛选条件',
				skin : 'layer-ext-moon',
				area : ['950px', '600px'],
				shade : 0.6,
				content : 'chooseData.html',
				btn : ['确定', '取消'],
				btn1 : function(index, layero) {
					var iframeWin = window[layero.find('iframe')[0]['name']];
					var Data = iframeWin.returndata();
					var i = zztzbs.findIndex(function(zbdata) {
								return zbdata.zbm == zztfilterdata[filterIndex];
							});
					layer.close(index);
					zztzbs[i].filters = Data;
					console.log("json:" + JSON.stringify("zztzbs:" + zztzbs));
					showZztFilter(i);
				},
				btn2 : function(index, layero) {
					layer.close(index);
				},
				success : function(layero, index) {
					var iframeWin = window[layero.find('iframe')[0]['name']];
					console.log(zztfilterdata);
					var i = zztzbs.findIndex(function(zbdata) {
								return zbdata.zbm == zztfilterdata[filterIndex];
							});
					console.log("----" + i);
					iframeWin.initCondition(columns,
							zztfilterdata[filterIndex], zztzbs[i].filters)
				}

			});
}
/**
 * 显示表格的过滤条件
 * 
 * @param {}
 *            index
 */
function showFilter(index) {
	var zbbm = "zb" + index;
	var filters = zbs[index].filters;
	var html = "";
	console.log(zbbm);
	filters.forEach(function(e) {
				html += '<div class="chooseTime">' + e.conditionName + '</div>';
			})
	$("#" + zbbm).html(html);
}
/**
 * 显示曲线图的过滤条件
 * 
 * @param {}
 *            index
 */
function showQxtFilter(index) {
	var zbbm = "qxtzb" + index;
	var filters = qxtzbs[index].filters;
	var html = "";
	console.log(zbbm);
	filters.forEach(function(e) {
				html += '<div class="chooseTime">' + e.conditionName + '</div>';
			})
	$("#" + zbbm).html(html);
}
/**
 * 显示柱状图的过滤条件
 * 
 * @param {}
 *            index
 */
function showZztFilter(index) {
	var zbbm = "zztzb" + index;
	var filters = zztzbs[index].filters;
	var html = "";
	console.log(zbbm);
	filters.forEach(function(e) {
				html += '<div class="chooseTime">' + e.conditionName + '</div>';
			})
	$("#" + zbbm).html(html);
}
/**
 * 开始表格分析
 */
function startAnalysis() {
	var bm = $("#selectTable").val();
	var wdm = $("#column").val();
	var wdzs = $("#column").find("option:selected").text();
	var data = {
		bm : bm,
		wdm : wdm,
		wdzs : wdzs,
		zb : zbs
	}
	console.log("analysisPara.json:" + JSON.stringify(data));
	$.ajax({
				url : "/alarmAnalysis/analysis/startAnalysis",
				type : "post",
				data : {
					data : JSON.stringify(data)
				},
				error : function() {
					alert("系统错误");
				},
				success : function(data) {
					showTable(data);
				}
			});

}
/**
 * 开始曲线图分析
 */
function qxtStartAnalysis() {
	var bm = $("#qxtTable").val();
	var wdm = $("#qxtColumn").val();
	var wdzs = $("#qxtColumn").find("option:selected").text();
	var data = {
		bm : bm,
		wdm : wdm,
		wdzs : wdzs,
		zb : qxtzbs
	}
	console.log("analysisPara.json:" + JSON.stringify(data));
	$.ajax({
				url : "/alarmAnalysis/analysis/startAnalysis",
				type : "post",
				data : {
					data : JSON.stringify(data)
				},
				error : function() {
					alert("系统错误");
				},
				success : function(data) {
					showQxt(data);
				}
			});
}
/**
 * 显示表格的分析结果
 * 
 * @param {}
 *            data
 */
function showTable(data) {
	var thead_begin = "<tr>";
	var thead_end = "</tr>";
	var thead = "";
	var html = "";
	for (var i = 0; i < data[0].length; i++) {
		thead += "<th>" + data[0][i] + "</th>"
	}
	$("#tableHead").html(thead_begin + thead + thead_end);
	for (var i = 1; i < data.length; i++) {
		var tbody_begin = "<tr>";
		var tbody_end = "</tr>";
		var tbody = "";
		for (var j = 0; j < data[i].length; j++) {
			tbody += "<td>" + data[i][j] + "</td>"
		}
		html += (tbody_begin + tbody + tbody_end);
	}
	$("#tableValue").html(html);
}
/**
 * 显示曲线图的分析结果
 * 
 * @param {}
 *            data
 */
function showQxt(data) {
	var legendData = new Array();
	var xAxisData = new Array();
	var series = new Array();
	for (var i = 1; i < data[0].length; i++) {
		var lengendData = {
			name : data[0][i],
			textStyle : {
				color : 'rgb(109,109,109)'
			}
		}
		legendData.push(lengendData);
	}
	for (var i = 1; i < data.length; i++) {
		xAxisData.push(data[i][0]);
	}

	for (var i = 1; i < data[0].length; i++) {
		var numData = new Array();
		for (var j = 1; j < data.length; j++) {
			numData.push(data[j][i]);
		}
		console.log(numData);
		var seriesData = {
			name : data[0][i],
			data : numData,
			type : 'line',
			symbolSize : 5,
			smooth : true,
			lineStyle : {
				normal : {
					width : 3,
					shadowBlur : 20,
					shadowOffsetY : 5
				}
			},
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'top'
					}
				}
			}
		};
		series.push(seriesData);
	}

	console.log(series);
	var qxt = {
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : legendData,
			top : 'top'
			// right: 'right',
		},
		grid : {
			left : '2%',
			right : '2%',
			bottom : '10%',
			top : '12%',
			containLabel : true
		},
		xAxis : {
			type : 'category',
			axisLine : {
				lineStyle : {
					color : 'rgb(109,109,109)',
					width : 1
				}
			},
			splitLine : {
				show : false
			},// 去除网格线
			splitArea : {
				show : false
			},// 保留网格区域
			axisLabel : { // 文字倾斜
				interval : 0,
				rotate : 40
			},
			data : xAxisData
		},
		yAxis : {
			axisLine : {
				lineStyle : {
					// color:'#284c88',
					color : 'rgb(109,109,109)',
					width : 1
				}
			},
			splitLine : {
				show : false
			},// 去除网格线
			splitArea : {
				show : false
			},// 保留网格区域
			type : 'value'
		},
		series : series
	}
	chart3.clear();
	console.log(qxt);
	chart3.setOption(qxt);
	chart3.resize();
}
/**
 * 开始饼状图分析
 */
function bztStartAnalysis() {
	var bm = $("#bztTable").val();
	var wdm = $("#btzColumn").val();
	var wdzs = $("#btzColumn").find("option:selected").text();
	var bztzb = {
		zbm : "饼状图",
		filters : bztfilterdata
	}
	bztzbs.push(bztzb);
	var data = {
		bm : bm,
		wdm : wdm,
		wdzs : wdzs,
		zb : bztzbs
	}
	console.log("analysisPara.json:" + JSON.stringify(data));
	$.ajax({
				url : "/alarmAnalysis/analysis/startAnalysis",
				type : "post",
				data : {
					data : JSON.stringify(data)
				},
				error : function() {
					alert("系统错误");
				},
				success : function(data) {
					showBzt(data);
				}
			});
}
/**
 * 显示饼状图分析结果
 * 
 * @param {}
 *            data
 */
function showBzt(data) {
	var legendData = new Array();

	for (var i = 1; i < data.length; i++) {
		var lengendData = {
			name : data[i][0],
			textStyle : {
				color : 'rgb(109,109,109)'
			}
		};
		legendData.push(lengendData);
	}

	var series = new Array();
	var pieDatas = new Array();
	for (var i = 1; i < data.length; i++) {
		var pieData = {
			name : data[i][0],
			value : data[i][1]
		}
		pieDatas.push(pieData);
	}

	var seriesData = {
		name : data[0][0],
		type : 'pie',
		radius : '50%',
		center : ['50%', '50%'],

		itemStyle : {
			normal : {
			// borderColor: "rgba(40,76,136,0.1)",
			// borderWidth: 3
			}
		},
		data : pieDatas
	};
	series.push(seriesData);
	var bzt = {

		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient : 'horizontal',
			data : legendData,
			top : 'top'
		},

		calculable : true,
		series : series
	};

	chart2.clear();
	chart2.setOption(bzt);
	chart2.resize();
}
/**
 * 开始柱状图分析
 */
function zztStartAnalysis() {
	var bm = $("#zztTable").val();
	var wdm = $("#zztColumn").val();
	var wdzs = $("#zztColumn").find("option:selected").text();
	var data = {
		bm : bm,
		wdm : wdm,
		wdzs : wdzs,
		zb : zztzbs
	}
	console.log("analysisPara.json:" + JSON.stringify(data));
	$.ajax({
				url : "/alarmAnalysis/analysis/startAnalysis",
				type : "post",
				data : {
					data : JSON.stringify(data)
				},
				error : function() {
					alert("系统错误");
				},
				success : function(data) {
					showZzt(data);
				}
			});
}
/**
 * 显示柱状图分析结果
 * 
 * @param {}
 *            data
 */
function showZzt(data) {
	var legendData = new Array();
	var xAxisData = new Array();
	var series = new Array();
	for (var i = 1; i < data[0].length; i++) {
		var lengendData = {
			name : data[0][i],
			textStyle : {
				color : 'rgb(109,109,109)'
			}
		}
		legendData.push(lengendData);
	}
	for (var i = 1; i < data.length; i++) {
		xAxisData.push(data[i][0]);
	}

	for (var i = 1; i < data[0].length; i++) {
		var numData = new Array();
		for (var j = 1; j < data.length; j++) {
			numData.push(data[j][i]);
		}
		console.log(numData);
		var seriesData = {
			name : data[0][i],
			data : numData,
			type : 'bar',
			symbolSize : 5,
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'top'
					}
				}
			}
		};
		series.push(seriesData);
	}

	console.log(series);
	var zzt = {
		// 图表提示框
		tooltip : {
			trigger : 'axis'
		},
		grid : {
			x : '6%',
			y : '10%',
			x2 : '2%',
			y2 : '15%',
			borderWidth : '0'

		},
		// 是否充许托动自动计算
		calculable : true,
		// 图例
		legend : {
			data : legendData,
			top : 'top'
		},
		// x轴
		xAxis : [{
					type : 'category',
					axisLine : {
						lineStyle : {
							color : 'rgb(109,109,109)',
							width : 1
						}
					},
					splitLine : {
						show : false
					},// 去除网格线
					splitArea : {
						show : false
					},// 保留网格区域
					axisLabel : { // 文字倾斜
						interval : 0,
						rotate : 40
					},
					data : xAxisData
				}],
		// y轴
		yAxis : [{
					type : 'value',
					// name : '本月新增警情',
					verticalAlign : 'middle',
					axisLabel : {
						formatter : '{value}'
					},
					axisLine : {
						lineStyle : {
							color : 'rgb(109,109,109)',
							width : 1
						}
					},

					splitLine : {
						show : false
					},// 去除网格线
					splitArea : {
						show : false
					}// 保留网格区域
				}],
		// 系列
		series : series
	};
	chart1.clear();
	console.log(zzt);
	chart1.setOption(zzt);
	chart1.resize();
}
/**
 * 保存表格的分析查询参数提示框
 */
function saveTableConfirm() {

	layer.open({
		id : 1,
		icon : 1,
		type : 1,
		title : '保存确认',
		skin : 'demo-class',
		shade : 0.6,
		content : "<input type='text' class='textBlack selectStyle' id='dzfxName' value='' placeholder='请输入保存的名称'/>",
		btn : ['确定', '取消'],
		btn1 : function(index, layero) {
			saveTableAnalysis(layero.find("input").val(), index);
		},
		btn2 : function(index, layero) {
			layer.close(index);
		},
		success : function(layero, index) {
			if ($("#saveTable").val() != null) {
				layero.find("input").val($("#saveTable").attr("data"));
			}
		}

	});

}
/**
 * 保存表格分析查询参数
 * 
 * @param {}
 *            dzfxName
 * @param {}
 *            index
 */
function saveTableAnalysis(dzfxName, index) {
	var bm = $("#selectTable").val();
	var id = $("#saveTable").val();
	var wdm = $("#column").val();
	var wdzs = $("#column").find("option:selected").text();
	if (bm != null && bm != "") {
		var data = {
			bm : bm,
			wdm : wdm,
			wdzs : wdzs,
			zb : zbs
		}
		var dzfx = {
			dzfxId : id,
			dzfxText : JSON.stringify(data),
			dzfxName : dzfxName,
			createTime : new Date(),
			dzfxType : "table"
		}
		$.ajax({
					url : "/alarmAnalysis/dzfx/save",
					type : "post",
					dataType : "JSON",
					data : dzfx,
					error : function() {
						alert("系统错误");
					},
					success : function(data) {
						if (data.status == 0) {
							layer.msg("保存成功");
						} else {
							layer.msg(data.msg);
							layer.close(index);
						}
					}
				});
	}
}
/**
 * 打开曲线图分析查询参数提示框
 */
function saveLineConfirm() {
	layer.open({
		id : 1,
		icon : 1,
		type : 1,
		title : '保存确认',
		skin : 'demo-class',
		shade : 0.6,
		content : "<input type='text' class='textBlack selectStyle' id='dzfxName' value='' placeholder='请输入保存的名称'/>",
		btn : ['确定', '取消'],
		btn1 : function(index, layero) {
			saveLineAnalysis(layero.find("input").val(), index);
		},
		btn2 : function(index, layero) {
			layer.close(index);
		},
		success : function(layero, index) {
			if ($("#saveLine").val() != null) {
				layero.find("input").val($("#saveLine").attr("data"));
			}
		}

	});
}
/**
 * 保存曲线图分析查询参数
 * 
 * @param {}
 *            dzfxName
 * @param {}
 *            index
 */
function saveLineAnalysis(dzfxName, index) {
	var bm = $("#qxtTable").val();
	var wdm = $("#qxtColumn").val();
	var id = $("#saveLine").val();
	var wdzs = $("#qxtColumn").find("option:selected").text();
	if (bm != null && bm != "") {
		var data = {
			bm : bm,
			wdm : wdm,
			wdzs : wdzs,
			zb : qxtzbs
		}
		var dzfx = {
			dzfxId : id,
			dzfxText : JSON.stringify(data),
			dzfxName : dzfxName,
			createTime : new Date(),
			dzfxType : "line"
		}
		$.ajax({
					url : "/alarmAnalysis/dzfx/save",
					type : "post",
					dataType : "JSON",
					data : dzfx,
					error : function() {
						alert("系统错误");
					},
					success : function(data) {
						if (data.status == 0) {
							layer.msg("保存成功");
						} else {
							layer.msg(data.msg);
							layer.close(index);
						}
					}
				});
	}

}
/**
 * 打开柱状图分析查询参数提示框
 */
function savePieConfirm() {
	layer.open({
		id : 1,
		icon : 1,
		type : 1,
		title : '保存确认',
		skin : 'demo-class',
		shade : 0.6,
		content : "<input type='text' class='textBlack selectStyle' id='dzfxName' value='' placeholder='请输入保存的名称'/>",
		btn : ['确定', '取消'],
		btn1 : function(index, layero) {
			savePieAnalysis(layero.find("input").val(), index);
		},
		btn2 : function(index, layero) {
			layer.close(index);
		},
		success : function(layero, index) {
			if ($("#savePie").val() != null) {
				layero.find("input").val($("#savePie").attr("data"));
			}
		}

	});
}
/**
 * 保存饼状图分析查询参数
 * 
 * @param {}
 *            dzfxName
 * @param {}
 *            index
 */
function savePieAnalysis(dzfxName, index) {
	var bm = $("#bztTable").val();
	var wdm = $("#btzColumn").val();
	var id = $("#savePie").val();
	var wdzs = $("#btzColumn").find("option:selected").text();
	if (bm != null && bm != "") {
		var data = {
			bm : bm,
			wdm : wdm,
			wdzs : wdzs,
			zb : bztzbs
		}
		var dzfx = {
			dzfxId : id,
			dzfxText : JSON.stringify(data),
			dzfxName : dzfxName,
			createTime : new Date(),
			dzfxType : "pie"
		}
		$.ajax({
					url : "/alarmAnalysis/dzfx/save",
					type : "post",
					dataType : "JSON",
					data : dzfx,
					error : function() {
						alert("系统错误");
					},
					success : function(data) {
						if (data.status == 0) {
							layer.msg("保存成功");
						} else {
							layer.msg(data.msg);
							layer.close(index);
						}
					}
				});
	}

}
/**
 * 打开柱状图分析查询参数提示框
 */
function saveBarConfirm() {
	layer.open({
		id : 1,
		icon : 1,
		type : 1,
		title : '保存确认',
		skin : 'demo-class',
		shade : 0.6,
		content : "<input type='text' class='textBlack selectStyle' id='dzfxName' value='' placeholder='请输入保存的名称'/>",
		btn : ['确定', '取消'],
		btn1 : function(index, layero) {
			saveBarAnalysis(layero.find("input").val(), index);
		},
		btn2 : function(index, layero) {
			layer.close(index);
		},
		success : function(layero, index) {
			if ($("#saveBar").val() != null) {
				layero.find("input").val($("#saveBar").attr("data"));
			}
		}
	});
}
/**
 * 保存柱状图分析查询参数
 * 
 * @param {}
 *            dzfxName
 * @param {}
 *            index
 */
function saveBarAnalysis(dzfxName, index) {
	var bm = $("#zztTable").val();
	var wdm = $("#zztColumn").val();
	var id = $("#saveBar").val();
	var wdzs = $("#zztColumn").find("option:selected").text();
	if (bm != null && bm != "") {
		var data = {
			bm : bm,
			wdm : wdm,
			wdzs : wdzs,
			zb : zztzbs
		}
		var dzfx = {
			dzfxId : id,
			dzfxText : JSON.stringify(data),
			dzfxName : dzfxName,
			createTime : new Date(),
			dzfxType : "bar"
		}
		$.ajax({
					url : "/alarmAnalysis/dzfx/save",
					type : "post",
					dataType : "JSON",
					data : dzfx,
					error : function() {
						alert("系统错误");
					},
					success : function(data) {
						if (data.status == 0) {
							layer.msg("保存成功");
						} else {
							layer.msg(data.msg);
							layer.close(index);
						}
					}
				});
	}

}

/**
 * tab 栏切换重新绘制图表视图
 */
$('a[data-toggle="pill"]').on('shown.bs.tab', function(e) {
			console.log("tab.show");
			for (var i = 0; i < charts.length; i++) {
				charts[i].resize();
			}
		});
$(window).resize(function() {
			console.log("resize");
			for (var i = 0; i < charts.length; i++) {
				charts[i].resize();
			}
		});
function echartsInit() {

//	chart1.setOption(option1);
//	chart2.setOption(option2);
//	chart3.setOption(option3);

	charts.push(chart1);
	charts.push(chart2);
	charts.push(chart3);
}
