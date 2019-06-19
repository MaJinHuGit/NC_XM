var starTime = getUrlParam2("starTime")
var endTime = getUrlParam2("endTime")
var fztype = getUrlParam2("fztype");
$(function() {
	//加载高危区域下面的每个派出所的列表
	loadGwsdList("")
	//高危区域统计图
	getGwsdTjt()
})
/**
 * 加载高危时段下面的每个派出所的列表
 * @returns
 */
function loadGwsdList(times) {	

	 $('#exampleTable').bootstrapTable('destroy');
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "../jqfx/ztyp/getGwsdList",
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						search : true, // 是否显示搜索框
						//showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							var size = params.limit;
							var num = (params.offset / params.limit) + 1;
							//alert(num)
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长
								limit : size * num + 1,
								offset : (num - 1) * size,
								times : encodeURI(times),
								starTime:encodeURI(starTime),
								endTime:encodeURI(endTime),
								fztype:encodeURI(fztype),
								search:encodeURI($(".search").children(".input-outline").val())

							};
						},
						columns : [
								{
									field : 'dates',
									title : '时段',
									switchable : false
								},
								{
									field : 'ct',
									title : '犯罪类型',
									switchable : false
								},
								{
									field : 'sfdzmc',
									title : '案发地址',
									width : '100px',
									switchable : false
								},
								{
									field : 'jyaq',
									title : '简要案情'
								} ]
					});
}

function edit(id) {
	data = {
		"jjdbh" : id
	};
	layer.open({
		type : 2,
		title : '案件详情',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '450px' ],
		content : "wffzjqView.html"
	});
}

/**
 * 点击柱状图是查询
 * @param param
 * @returns
 */
function eConsole(param) {
	//alert(param.name)
	loadGwsdList(param.name);
}


/**
 * 高危区域统计图
 * @param startTime
 * @param endTime
 * @returns
 */
function getGwsdTjt() {
	var url = "../jqfx/ztyp/getGwsdTjt";
	$.get(url, {
		startTime : startTime,
		endTime : endTime,
		fztype:encodeURI(fztype),
	}, function(data) {
		/*console.log(data)*/
		var datas = data.gwsdTjt;
		//console.log(datas)
		var arr  = [];

		var leg = [];
		
		//本周统计
		for (var i = 0; i < datas.length; i++) {
		
			var val = datas[i].cunt;
			 var time = datas[0].dates
			    /*// alert(time.substr(0,1))
			     if(time.substr(0,1)==0){
			    	 time = time.substr(1,1)
			     }else{
			    	 time = time.substr(0,2)
			     }
			 alert(time)*/
			var name = time;
			
			leg.push(name);
			arr.push({
					name: name,
					value:val
				});
			//}
			
			
		}
		
		chart4.setOption({
			xAxis : [ {data : leg}],
			series : [ {data:arr}]
		});
		chart4.on("click", eConsole);
	});
}

var wffzjq2 = {
	//图表提示框
	tooltip : {
		trigger : 'axis'
	},
	grid : {
		x : '6%',
		y : '12%',
		x2 : '2%',
		y2 : '30%',
		borderWidth : '0',

	},
	//是否充许托动自动计算
	calculable : true,
	//图例
	legend : {
		data : [],
		top : 'top'
	},
	//x轴
	xAxis : [ {
		type : 'category',

		axisLine : {
			lineStyle : {
				color : 'rgb(109,109,109)',
				width : 1
			}
		},
		splitLine : {
			show : false
		},//去除网格线
		splitArea : {
			show : false
		},//保留网格区域	
		axisLabel : { //文字倾斜
			interval : 0,
			rotate : 40,
		},
		data : [  ],
		axisLabel : {//坐标轴刻度标签的相关设置。
			interval : 0,
			formatter : function(value) {
				if (value != undefined) {
					return value
				}
				;
			}
		}
	} ],
	//y轴
	yAxis : [ {
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
		},//去除网格线
		splitArea : {
			show : false
		},//保留网格区域
	} ],
	//系列
	series : [ {
		name : '',
		type : 'bar',
		//barWidth : 12,
		itemStyle : {
			normal : {
				//color: 'rgba(61,99,160,0.5)'						   
				color : 'rgb(66,143,213)',
				label : {
					show : true,
					position : 'top'
				}
			}
		},
		data : [ ]
	} ]
};
/**
 * getUrlParam2:()
 * TODO(获取url中文参数)
 * @author: 黄月
 * @dateTime: 2018年9月30日 上午10:56:59
 * @param: @param name
 * @param: @returns
 * @return: any
 */
function getUrlParam2(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null)
		return decodeURI(r[2]);
	return null; //返回参数值,中文解码
}
