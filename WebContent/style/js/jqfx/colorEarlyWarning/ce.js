
/**
 * 2017-5-16下午5:23:39
 * TODO
 * 当前列表标识1：全市，2：区县 3：派出所
 */
var listFlag = 1;
//用于派出所页面保存上级区县代码
var countyID = "";
$().ready(function(){
	
	//查询日期下拉列表
	//getDateList();
	//获取本周日期
	//getDate();
	//加载地图(默认为空)
	loadMap();
	//查询广安市分类列表
	//listByType("");
});

/**
  * listByType:(查询广元市分类列表)
  * TODO(默认执行)
  * @author: 黄月
  * @dateTime: 2017-5-16 下午2:17:21
  * @param: 
  * @return: void
 */
function listByType(date){
	//当前列表类型标识
	listFlag = 1;
	//隐藏按钮
	$(".query").hide();
	$.ajax({
		url : 'queryByType.do',
		type : 'post',
		async : false,
		dataType : 'json',
		data : {
			date : date
		},
		success : function (data) {
			if (data.status == "success") {
				var list = data.result;
				var html = "";
				var len = list.length;
				var qy = "";
				//遍历表格数据
				for ( var i = 0; i < len; i++) {
					if (i == 0) {
						qy += "<td rowspan='" + len + "'><a href='javascript:void(0);' id='toDown' onclick='changeList(this.id);'>广安</a></td>";
					} else {
						qy = "";
					}
					html += "<tr><td>" + list[i].abmc + "</td>" + qy + "<td>" + list[i].zcount
						+ "</td><td>" + list[i].szcount;
					html += "</td><td>" + list[i].zs + "%</td><td>"
						+ getColor(list[i].zcount, list[i].zs) + "</td></tr>";
				}
				//获取color对应值
				var colorVal = getColorIndex(getColor(list[0].zcount, list[0].zs));
				//地图数组
				var dataArr = "[{name:'广安区',value:" + colorVal + "},{name:'岳池县',value:" + colorVal
						+ "},{name:'武胜县',value:" + colorVal + "}," + "{name:'邻水县',value:" + colorVal
						+ "},{name:'华蓥市',value:" + colorVal + "}]";
				//加载地图
				toloadMap(dataArr);
				$('#colorList').html(html);
			} else {
				alert("查询信息错误，异常信息：" + data.result);
			}
		}
	});
}

/**
  * toList:(广元市、区转换)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-16 下午4:42:47
  * @param: 
  * @return: void
 */
function changeList(id){
	parent.openMask("LOADING...");
	//getDate();
	if (id == "toDown") {
		//列表查询
		queryColorList($('#data1').val(), "");
	} else {
		//查询广元市分类列表
		listByType($('#data1').val());
	}
	setTimeout(function(){parent.closeMask();}, 1000);
}

/**
  * sel_color:(检索)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-8 上午9:19:23
  * @param: 
  * @return: void
 */
function sel_color(){
	//获取当前时间段
	var date = $('#data1').val();
	//当前查询时间赋值
	$('#time_text').html(date.replace('@','~'));
	//获取当前列表标识，判断查询类型
	if (listFlag == 1) {
		//检索广元市级分类
		listByType(date);
	} else if (listFlag == 2) {
		//获取下拉列表查询类型
		var lb = $('#ajlb').val();
		//检索区县
		queryColorList(date, lb);
	} else {
		//获取下拉列表查询类型
		var lb = $('#ajlb').val();
		//检索区县
		policeStationList(date, lb, countyID);
	}
}


/**
  * queryColorList:(区县列表查询)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-6 下午12:16:11
  * @param: @param date
  * @return: void
 */
function queryColorList(date, lb){
	//当前列表类型标识
	listFlag = 2;
	//显示按钮
	$(".query").show();
	$.ajax( {
		type : 'post',
		url : 'queryColorList.do',
		async : false,
		dataType:"json",
		data : {
			date : date,
			lb:lb
		},
		success : function(data) {
			//地图数据json字符串数组
			var jsonStr = "";
			if (data.status == "success") {
				//全市统计
				var listCount = data.result2;
				var html = "";
				html += "<tr><td>合计</td><td><a href='javascript:void(0);' id='toUp' onclick='changeList(this.id);'>全市</a></td><td>" + listCount[0] + "</td><td>" + listCount[1];
				html +=	"</td><td>" + listCount[2] + "%</td><td>" + getColor(listCount[0], listCount[2]) + "</td></tr>";
				//区县列表
				var list = data.result;
				//获取每个区县（1-8）
				var area = "";
				var qx = "";
				//遍历所有区县数据
				for ( var i = 0; i < list.length; i++) {
					//获取当前数据颜色
					var color = getColor(list[i].zcount, list[i].zs);
					//获取每个区县(1-8,同一数字代表同一区县)
					var mc = list[i].ddmc;
					//第一次循环
					if (area == "") {
						area = list[i].qhdm;
						qx += "<td rowspan='" + list.length/6 + "'>";
						qx +="<a href='javascript:void(0);' onclick='policeStationList(\""+date+"\",\""+lb+"\",\""+area+"\");'>";
						qx += mc + "</a></td>";
						//获取区域，颜色值键值对
						jsonStr += '{name:"' + mc + '",value:"' + getColorIndex(color) + '"},';
					} else if (list[i].qhdm != area) {
						//alert(list[i].qhdm)
						//当循环到下一个园区时
						area = list[i].qhdm;
						qx = "<td rowspan='" + list.length/6
						+ "'><a href='javascript:void(0);' onclick='policeStationList(\""+date+"\",\""+lb+"\",\""+area+"\");'>"
						+ mc + "</a></td>";
						jsonStr += '{name:"' + mc + '",value:"' + getColorIndex(color) + '"},';
					} else {
						qx = "";
					}
					html += "<tr><td>" + list[i].abmc + "</td>" + qx + "<td>" + list[i].zcount+"</td><td>" + list[i].szcount;
					html += "</td><td>" + list[i].zs + "%</td><td>" + color + "</td></tr>";
				}
				jsonStr = '[' + jsonStr.substring(0, jsonStr.length - 1) + ']';
				$('#colorList').html(html);
			} else {
				alert("查询数据异常！" + data.result);
			}
			toloadMap(jsonStr);
		}
	});
}

/**
  * toloadMap:(执行加载地图)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-8 上午9:19:48
  * @param: @param data
  * @return: void
 */
function loadMap(){
   var myChart = echarts.init(document.getElementById("map_main"));
   myChart.setOption(getMapOption());
   window.onresize = function () {
	   myChart.resize();
   };
}

var data = [
	{"name":"龙泉派出所","value":"4"},
	{"name":"大面派出所","value":"8"},
	{"name":"洛带派出所","value":"16"},
	{"name":"山泉派出所","value":"5"},
	{"name":"柏合派出所","value":"6"},
	{"name":"十陵派出所","value":"2"},
	{"name":"西河派出所","value":"1"},
	{"name":"洪安派出所","value":"5"},
	{"name":"黄土派出所","value":"12"},
	{"name":"洪河派出所","value":"3"},
	{"name":"万兴派出所","value":"4"},
	{"name":"经济开发区派出所","value":"1"},
	{"name":"北干道派出所","value":"2"},
	{"name":"平安派出所","value":"3"},
	{"name":"同安派出所","value":"8"},
	{"name":"茶店派出所","value":"9"},
	{"name":"怡和派出所","value":"1"}
];

var geoCoordMap =  {
		'龙泉派出所':[104.274227,30.553425],
		'大面派出所':[104.198146,30.594158],
		'洛带派出所':[104.33647,30.648352],
		'山泉派出所':[104.323937,30.545266],
		'柏合派出所':[104.229162,30.523758],
		'十陵派出所':[104.194691,30.665779],
		'西河派出所':[104.274518,30.562852],
		'洪安派出所':[104.341453,30.706663],
		'黄土派出所':[104.298387,30.693327],
		'洪河派出所':[104.160201,30.614568],
		'万兴派出所':[104.394536,30.654387],
		'经济开发区派出所':[104.193852,30.552691],
		'北干道派出所':[104.266031,30.570695],
		'平安派出所':[104.278727,30.581712],
		'同安派出所':[104.31748,30.615828],
		'茶店派出所':[104.358853,30.510944],
		'怡和派出所':[104.267179,30.578138]
}

//获取数据和坐标
var convertData= function (data) {
	var res = [];
	for (var i = 0; i < data.length; i++) {
		var geoCoord = geoCoordMap[data[i].name];
		if (geoCoord) {
			res.push({
				name: data[i].name,
				value: geoCoord.concat(data[i].value)
			});
		}
	}
	return res;
};


/**
 * 主页加载地图
 */
function getMapOption(){
	var option = {
			backgroundColor: '#404a59',
			title: {
				text : '龙泉驿区',
				/*subtext : '四色预警',*/
				textStyle: {color: '#fff'}
			},
			//提示框
			tooltip : {
				trigger: 'item',
				formatter: function (params) {
					//添加显示标签,默认为显示坐标
					return params.name+'<br>'+'本周警情'+''+':'+''+params.value[2]+''+'件'
				},
				padding:[
					5,  // 上
					10, // 右
					5,  // 下
					10, // 左
				],
				textStyle:{
					color: '#fff',
					fontSize:'13'
				}
			},
			//地图
			geo: {
				map: 'longquanyi',
				label: {
					emphasis: {
						show: false
					}
				},
				roam: false,
				itemStyle: {
					//正常状态下
					normal: {
						areaColor: '#323c48',
						borderColor: '#111'
					},
					//选定某一区域下 相当于 hover
					emphasis: {areaColor: '#2a333d'}
				},
				z:1
			},
			//这里数据中主要是冒泡图数据
			series : [{
				type: 'effectScatter',
				coordinateSystem: 'geo',
				data: convertData(data),
				//symbolSize:10,
				symbolSize: function (val) {
					return val[2] * 2;
				},
				showEffectOn: 'render',
				rippleEffect: {
					brushType: 'stroke'
				},
				hoverAnimation: true,
				label: {
					normal: {
						formatter: '{b}',
						position: 'right',
						show: false
					},
					emphasis: {
						show: true
					}
				},
				itemStyle: {
					normal: {
						color:function(params){
							if(params.value[2]>15){
								return '#FF5722';
							}else if(params.value[2]>10){
								return '#FFB800';
							}else if(params.value[2]>5){
								return '#1E9FFF';
							}else{
								return '#009688';
							}
						},
						shadowBlur: 10,
						shadowColor: '#333'
					}
				}
			}
		]
	};
	return option;
}

/**
  * getDate:(获取本周日期)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-6 下午3:54:18
  * @param: 
  * @return: void
 */
function getDate(){
		var now = new Date;
	    var day = now.getDay();
	    var week = "7123456";
	    var first = 1 - week.indexOf (day);
	    var f = new Date;
	    f.setDate (f.getDate () + first);
	    var last = 7 - week.indexOf (day);
	    var l = new Date;
	    l.setDate (l.getDate () + last);
	    $('#time_text').html(f.Format("yyyy/MM/dd") + "~" + l.Format("yyyy/MM/dd"));
	    var st = f.Format("yyyy/MM/dd") + "~" + l.Format("yyyy/MM/dd");
	    //var op = "";
	    /*var op=document.getElementById('data1').children;
	    alert(op.length)
	    for(var i=0,len=op.length;i<len;i++){
	        //alert(op[i].val());
	    }*/
	    var obj = document.getElementById("data1");
	  //  obj = document.getElementById("test");
	    for(var i=0;i<obj.length;i++){
	    	var tim = obj[i].value.replace('@','~');
	        if(tim==st){
	        	 obj[i].selected = true;
	        }
	           
	    }
	   
}

/**
 * @author Administrator
 *日期格式化
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) {
    	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
    	if (new RegExp("(" + k + ")").test(fmt)) {
    		fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    	}
    }
    return fmt;
};

/**
  * getColorIndex:(获取当前颜色给地图赋值)
  * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
  * @author: 黄月
  * @dateTime: 2017-5-16 下午4:18:25
  * @param: 
  * @return: void
 */
function getColorIndex(color){
	var colorFlag = 0;
	if (color.indexOf("绿色") > 0) {
		colorFlag = 1;
	} else if (color.indexOf("黄色") > 0) {
		colorFlag = 2;
	} else if (color.indexOf("橙色") > 0) {
		colorFlag = 3;
	} else if (color.indexOf("红色") > 0) {
		colorFlag = 4;
	}
	return colorFlag;
}

/**
 * getColor:(颜色判断)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-5-9 上午11:00:46
 * @param: @param zjql 周警情量
 * @param: @param dtcl 动态常量
 * @return: void
*/
function getColor(zjql1, dtcl1){
	var zjql= parseInt(zjql1);
	var dtcl= parseInt(dtcl1);
	if (zjql <= 5) {
		if (dtcl <= 50) {
			return "<span class='label label-success'>绿色</span>";
		} else if (50 < dtcl && dtcl <= 100) {
			return "<span class='label label-info'>黄色</span>";
		} else if (100 < dtcl && dtcl  <= 200) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (200 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	} else if (5 < zjql && zjql <= 10) {
		if (dtcl <= 50) {
			return "<span class='label label-success'>绿色</span>";
		} else if (50 < dtcl && dtcl  <= 90) {
			return "<span class='label label-info'>黄色</span>";
		} else if (90 < dtcl && dtcl  <= 190) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (190 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	} else if (10 < zjql && zjql <= 20) {
		if (dtcl <= 30) {
			return "<span class='label label-success'>绿色</span>";
		} else if (30 < dtcl && dtcl  <= 70) {
			return "<span class='label label-info'>黄色</span>";
		} else if (70 < dtcl && dtcl <= 140) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (140 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	} else if (20 < zjql && zjql <= 50) {
		if (dtcl <= 25) {
			return "<span class='label label-success'>绿色</span>";
		} else if (25 < dtcl && dtcl  <= 45) {
			return "<span class='label label-info'>黄色</span>";
		} else if (45 < dtcl && dtcl  <= 80) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (80 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	} else if (50 < zjql && zjql <= 100) {
		if (dtcl <= 20) {
			return "<span class='label label-success'>绿色</span>";
		} else if (20 < dtcl && dtcl  <= 35) {
			return "<span class='label label-info'>黄色</span>";
		} else if (35 < dtcl && dtcl  <= 60) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (60 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	} else if (100 < zjql && zjql <= 300) {
		if (dtcl <= 10) {
			return "<span class='label label-success'>绿色</span>";
		} else if (10 < dtcl && dtcl  <= 18) {
			return "<span class='label label-info'>黄色</span>";
		} else if (18 < dtcl  && dtcl <= 30) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (30 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	} else if (300 < zjql && zjql <= 600) {
		if (dtcl <= 8) {
			return "<span class='label label-success'>绿色</span>";
		} else if (8 < dtcl && dtcl  <= 15) {
			return "<span class='label label-info'>黄色</span>";
		} else if (15 < dtcl  && dtcl <= 20) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (20 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	} else if (600 < zjql) {
		if (dtcl <= 6) {
			return "<span class='label label-success'>绿色</span>";
		} else if (6 < dtcl && dtcl  <= 10) {
			return "<span class='label label-info'>黄色</span>";
		} else if (10 < dtcl && dtcl  <= 15) {
			return "<span class='label label-warning'>橙色</span>";
		} else if (15 < dtcl) {
			return "<span class='label label-important'>红色</span>";
		}
	}
}


/**
 * policeStationList:(跳转派出所列表)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-6-8 上午11:09:37
 * @param: @param qhdm
 * @return: void
*/
var qh = "";
function policeStationList(date,lb,qhdm){
	//当前列表类型标识
	listFlag = 3;
	qh = qhdm;
	$.ajax( {
		type : 'post',
		url : 'policeStationList.do',
		async : false,
		dataType:"json",
		data : {
			date : date,
			lb:lb,
			qhdm:qhdm
		},
		success : function(data) {
			if (data.status == "success") {
				//保存当前县级代码
				countyID = qhdm;
				var html = "<tr><td colspan='6'><a href='javascript:void(0);' id='toDown' onclick='changeList(this.id);'>上一级</a></td></tr>";
				var list = data.result;
				//获取每个派出所代码
				var policeId = "";
				var policeCount = 0;
				var policeDom = "";
				//第一次遍历所有派出所，获取派出所个数
				for ( var j = 0; j < list.length; j++) {
					 if (policeId != list[j].qhdm && list[j].ddmc != '其他派出所'){
						 policeId = list[j].qhdm;
						 policeCount++;
					 } 
				}
				policeId = "";
				for ( var i = 0; i < list.length; i++) {
					//获取当前数据颜色
					var color = getColor(list[i].zcount, list[i].zs);
					//获取每个派出所
					var policeName = list[i].ddmc;
					//当循环到下一个派出所时
					if (policeName == '其他派出所') {
						policeDom = "<td>" + policeName + "</td>";
					} else if (list[i].qhdm != policeId) {
						policeId = list[i].qhdm;
						policeDom = "<td rowspan='"+list.length/policeCount+"'>" + policeName + "</td>";
					} else {
						policeDom = "";
					}
					html += "<tr><td>" + list[i].abmc + "</td>" + policeDom + "<td>" + list[i].zcount+"</td><td>" + list[i].szcount;
					html += "</td><td>" + list[i].zs + "%</td><td>" + color + "</td></tr>";
				}
				$('#colorList').html(html);
			} else {
				alert("查询数据异常！" + data.result);
			}
		}
	});
}
/**
 * downloadExcel:(文件导出)
 * TODO(这里描述方法适用条件/执行流程/使用方法/注意事项)
 * @author: 黄月
 * @dateTime: 2017-6-8 上午9:47:35
 * @param: 
 * @return: void
*/
function downloadExcel(){
	var downloadFlag = "";
	//获取当前表格查询登记和条件
	//获取当前时间段
	var date = $('#data1').val();
	var lb=$("#ajlb").val();
	//获取当前列表标识，判断查询类型
	if (listFlag == 1) {
		//检索广元市级分类
		downloadFlag = "1@" + date;
	} else if (listFlag == 2) {
		downloadFlag = "2@" + date + "@" + lb;

	}else if(listFlag == 3){
		downloadFlag = "3@" + date + "@" + lb + "@" + qh;
	}
	
	window.location.href="downloadExcel.do?flag=" + downloadFlag;
}
