var lx = decodeURI(parent.dataParams['lx']);
var ksms = parent.dataParams['ksms'];
var zxsj = parent.dataParams['zxsj'];

var charts = [];
//var chart1 = echarts.init(document.getElementById("bzjq1"));
var chart1 = echarts.init(document.getElementById("bjsj"));
var chart2 = echarts.init(document.getElementById("bjxl"));
var chart3 = echarts.init(document.getElementById("gxdw"));
//var chart4 = echarts.init(document.getElementById("bjdh"));


/*chart2.setOption(bjxl);
chart3.setOption(gxdw);
chart4.setOption(bjdh);*/

/*charts.push(chart2);
charts.push(chart3);
charts.push(chart4);*/
		




$(function(){
	load();
	
	bzjqBjsj();
	
	
	bzjqBjxl();
	
	bzjqGxdw();
	
	bzjqBjdh();
})

function load() {
	
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : "../jqfx/bzjq/getAfdz",
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
			return {
				//说明：传入后台的参数包括offset开始索引，limit步长
				limit : size*num+1,	
				offset :(num-1)*size,
				lx:encodeURI(lx),	
				zxsj:zxsj,
				ksms:ksms,
				search:encodeURI($(".search").children(".input-outline").val())
			};
		},
		columns : [
			{
				field : 'afdz',
				title : '案发地址',
				switchable: false
			},
			{
				field : 'cunt',
				title : '数量',
				switchable: false
			},
			{	
				title : '操作',
				field : 'jjdbh',
				align : 'center',
				switchable: false,
				formatter : function(value, row, index) {
					var e = '<a class="btn btn-primary btn-sm ' + "" + '" href="#" mce_href="#" title="编辑" onclick="openFrame5(\''
						+ row.afdz
						+ '\')"><i class="fa fa-clone"></i></a> ';
					return e;
				}
			}]
		});
	
	
	$('#exampleTable2').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : "../jqfx/bzjq/getGjz",
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
			return {
				//说明：传入后台的参数包括offset开始索引，limit步长
				limit : size*num+1,	
				offset :(num-1)*size,
				lx:encodeURI(lx),	
				zxsj:zxsj,
				ksms:ksms,
				search:encodeURI($(".search").children(".input-outline").val())
			};
		},
		columns : [
			{
				field : 'gjz',
				title : '关键字',
				switchable: false
			},
			{
				field : 'cunt',
				title : '数量',
				switchable: false
			},
			
			{	
				title : '操作',
				field : 'jjdbh',
				align : 'center',
				switchable: false,
				formatter : function(value, row, index) {
					var e = '<a class="btn btn-primary btn-sm ' + "" + '" href="#" mce_href="#" title="编辑" onclick="openFrame6(\''
						+ row.gjz
						+ '\')"><i class="fa fa-clone"></i></a> ';
					return e;
				}
			}]
		});
	
	
	$('#exampleTable3').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : "../jqfx/bzjq/getBjdh",
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
			return {
				//说明：传入后台的参数包括offset开始索引，limit步长
				limit : size*num+1,	
				offset :(num-1)*size,
				lx:encodeURI(lx),	
				zxsj:zxsj,
				ksms:ksms,
				search:encodeURI($(".search").children(".input-outline").val())
			};
		},
		columns : [
			{
				field : 'bjdh',
				title : '报警电话',
				switchable: false
			},
			{
				field : 'cunt',
				title : '数量',
				switchable: false
			},
			{	
				title : '操作',
				field : 'bjdh',
				align : 'center',
				switchable: false,
				formatter : function(value, row, index) {
					var e = '<a class="btn btn-primary btn-sm ' + "" + '" href="#" mce_href="#" title="编辑" onclick="openFrame4(\''
						+ row.bjdh
						+ '\')"><i class="fa fa-clone"></i></a> ';
					return e;
				}
			}]
		});
}

function bzjqBjsj(){
	//$.ajaxSettings.async = false;
	$.get("../jqfx/bzjq/getBjsj",{zxsj:zxsj,ksms:ksms,lx:encodeURI(lx)},function(data){
		dbtData = data.data;
//	console.log(dbtData)
		var data = [];
		for (var i = 0; i < dbtData.length; i++) {
			data.push(dbtData[i].cunt);
			
		}
		
		var lge = 
				 {
			            name:lx,
			            type:'line',
			            stack: '总量',
			            data:data
			        }
//console.log(lge)
		window.resize = function () {
			chart1.resize();
	    
	    }
		chart1.setOption({series : [lge]});
		
		chart1.off('click');
		chart1.on('click', function(params){
			openFrame1(params);
		})
	/*	window.addEventListener("resize",function(){
			alert(34)
			chart1.resize();
		});*/
		/*window.onresize = function(){
			alert(34)
			chart1.resize();
		}
		*/
		// 设置自适应屏幕大小
		//window.onresize = myChart.resize;
		
	})
}

var color = ['#759ba1','#8dc39a','#73babc','#e69e85','#7c97bd','#eddd78','#de6c66','#eb7f54'];
function bzjqBjxl(){
	//$.ajaxSettings.async = false;
	$.get("../jqfx/bzjq/getBjxl",{zxsj:zxsj,ksms:ksms,lx:encodeURI(lx)},function(data){
		dbtData = data.data;
	//	console.log(dbtData)
		var data = [];
		var datalge = [];
		var color2 = []
		for (var i = 0; i < dbtData.length; i++) {
			/*data.push(dbtData[i].cunt);*/
			
			var lge ={name: dbtData[i].bjxl , textStyle:{color:"#ffffff"}}
			var seriesData ={name: dbtData[i].bjxl , value:dbtData[i].cunt}
			data.push(seriesData);
			datalge.push(lge);
		}
		
		

		chart2.setOption({legend : {data:datalge},series : [{color:color2,data:data}]});
		chart2.off('click');
		chart2.on('click', function(params){
			openFrame2(params);
		})
	})
}


function bzjqGxdw(){
	//$.ajaxSettings.async = false;
	$.get("../jqfx/bzjq/getGxdw",{zxsj:zxsj,ksms:ksms,lx:encodeURI(lx)},function(data){
		dbtData = data.data;
		
		var data = [];
		var datalge = [];
		var color2 = []
		for (var i = 0; i < dbtData.length; i++) {
			/*data.push(dbtData[i].cunt);*/
			color2.push(color[i])
			var lge ={name: dbtData[i].gxdw , textStyle:{color:"#ffffff"}}
			var seriesData ={name: dbtData[i].gxdw , value:dbtData[i].cunt}
			data.push(seriesData);
			datalge.push(lge);
		}
		
		
		chart3.setOption({legend : {data:datalge},series : [{color:color2,data:data}]});
		chart3.off('click');
		chart3.on('click', function(params){
			openFrame3(params);
		})
	
	})
}

function bzjqBjdh(){
	//$.ajaxSettings.async = false;
	$.get("../jqfx/bzjq/getBjdh",{zxsj:zxsj,ksms:ksms,lx:encodeURI(lx)},function(data){
		dbtData = data.data;
		//console.log(dbtData)
		var data = [];
		var datalge = [];
		var color2 = []
		for (var i = 0; i < dbtData.length; i++) {
			/*data.push(dbtData[i].cunt);*/
			color2.push(color[i])
			var lge ={name: dbtData[i].bjdh , textStyle:{color:"#ffffff"}}
			var seriesData ={name: dbtData[i].bjdh , value:dbtData[i].cunt}
			data.push(seriesData);
			datalge.push(lge);
		}
		
		

		/*chart4.setOption({legend : {data:datalge},series : [{color:color2,data:data}]});
		chart4.off('click');
		chart4.on('click', function(params){
			openFrame4(params);
		})*/
	
	})
}
var bjsj = {
		
		  tooltip : {
		        trigger: 'axis'
		    },
		  
		   
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['0','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'],
		         	axisLine:{
                      lineStyle:{
                          color:'white',
                          width:2,//这里是为了突出显示加上的
                      }
                  } 
                  
                 

		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLine:{
                      lineStyle:{
                          color:'white',
                          width:2,//这里是为了突出显示加上的
                      }
                  } ,
                  splitLine:{
                  	show:false,
                  }
		        }
		    ],
		
		    series : [
		    	 {
			            name:'交通事故',
			            type:'line',
			            stack: '总量',
			            data:['0','4','0','4','0','4','0','4','0','4','0','4','0','4','0','4','0','4','0','4','0','4','0','4','0','4']
			        },
		    ]

};

var bjxl = {
		
		 
		
	tooltip : {
		trigger: 'item',
		formatter: "{a} <br/>{b} : {c} ({d}%)"
	},
	legend : {
		orient: 'vertical',
		right: 'right',
		bottom: 'bottom',
		data:[
			/*{name: '重复报警' , textStyle:{color:"#ffffff"}},
			{name: '投诉' , textStyle:{color:"#ffffff"}},
			{name: '噪音扰民' , textStyle:{color:"#ffffff"}},
			{name: '骚扰误报' , textStyle:{color:"#ffffff"}},
			{name: '求助' , textStyle:{color:"#ffffff"}},
			{name: '咨询' , textStyle:{color:"#ffffff"}},
			{name: '火灾' , textStyle:{color:"#ffffff"}},
			{name: '交通事故' , textStyle:{color:"#ffffff"}}*/
		]
	},
	calculable : true,
	series : [{
		name:'',
		type:'pie',
		radius : '50%',
		center: ['50%', '45%'],	
		color: ['#759ba1','#8dc39a','#73babc','#e69e85','#7c97bd','#eddd78','#de6c66','#eb7f54'],
		itemStyle: {
				normal: {   
					//borderColor: "rgba(40,76,136,0.1)",
					//borderWidth: 3
				}
		},
		data:[
			/*{name:"重复报警",value:0},
			{name:"投诉",value:0},
			{name:"噪音扰民",value:0},
			{name:"骚扰误报",value:0},
			{name:"求助",value:0},
			{name:"咨询",value:0},
			{name:"火灾",value:0},
			{name:"交通事故",value:0}*/
		]
	}]
};

var gxdw = {
		
		 
		
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient: 'vertical',
			right: 'right',
			bottom: 'bottom',
			data:[
				{name: '重复报警' , textStyle:{color:"#ffffff"}},
				{name: '投诉' , textStyle:{color:"#ffffff"}},
				{name: '噪音扰民' , textStyle:{color:"#ffffff"}},
				{name: '骚扰误报' , textStyle:{color:"#ffffff"}},
				{name: '求助' , textStyle:{color:"#ffffff"}},
				{name: '咨询' , textStyle:{color:"#ffffff"}},
				{name: '火灾' , textStyle:{color:"#ffffff"}},
				{name: '交通事故' , textStyle:{color:"#ffffff"}}
			]
		},
		calculable : true,
		series : [{
			name:'',
			type:'pie',
			radius : '50%',
			center: ['50%', '45%'],	
			color: ['#759ba1','#8dc39a','#73babc','#e69e85','#7c97bd','#eddd78','#de6c66','#eb7f54'],
			itemStyle: {
					normal: {   
						//borderColor: "rgba(40,76,136,0.1)",
						//borderWidth: 3
					}
			},
			data:[
				{name:"重复报警",value:0},
				{name:"投诉",value:0},
				{name:"噪音扰民",value:0},
				{name:"骚扰误报",value:0},
				{name:"求助",value:0},
				{name:"咨询",value:0},
				{name:"火灾",value:0},
				{name:"交通事故",value:0}
			]
		}]
	};

/*var bjdh = {
		
		 
		
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			orient: 'vertical',
			right: 'right',
			bottom: 'bottom',
			data:[
				{name: '重复报警' , textStyle:{color:"#ffffff"}},
				{name: '投诉' , textStyle:{color:"#ffffff"}},
				{name: '噪音扰民' , textStyle:{color:"#ffffff"}},
				{name: '骚扰误报' , textStyle:{color:"#ffffff"}},
				{name: '求助' , textStyle:{color:"#ffffff"}},
				{name: '咨询' , textStyle:{color:"#ffffff"}},
				{name: '火灾' , textStyle:{color:"#ffffff"}},
				{name: '交通事故' , textStyle:{color:"#ffffff"}}
			]
		},
		calculable : true,
		series : [{
			name:'',
			type:'pie',
			radius : '50%',
			center: ['50%', '45%'],	
			color: ['#759ba1','#8dc39a','#73babc','#e69e85','#7c97bd','#eddd78','#de6c66','#eb7f54'],
			itemStyle: {
					normal: {   
						//borderColor: "rgba(40,76,136,0.1)",
						//borderWidth: 3
					}
			},
			data:[
				{name:"重复报警",value:0},
				{name:"投诉",value:0},
				{name:"噪音扰民",value:0},
				{name:"骚扰误报",value:0},
				{name:"求助",value:0},
				{name:"咨询",value:0},
				{name:"火灾",value:0},
				{name:"交通事故",value:0}
			]
		}]
	};*/

chart1.setOption(bjsj);
charts.push(chart1);

chart2.setOption(bjxl);
charts.push(chart2);

chart3.setOption(gxdw);
charts.push(chart3);

/*chart4.setOption(bjdh);
charts.push(chart4);*/

$(window).resize(function() {
	
	for(var i = 0; i < charts.length; i++) {
		charts[i].resize();
	}
	
});
function view(id) {
	data = {"jjdbh":id};
	layer.open({
		type : 2,
		title : '案件详情',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '450px' ],
		content : "bzjqView.html"
	});
}

function openFrame1(params){
	
	dataParams = {
		"lx":lx,
		"name":encodeURI(params.seriesName),
		"ksms":ksms,
		"zxsj":zxsj,
		"point":params.name,
		"type":1
	};
	layer.open({
		id:1,
		type: 2,
		title:params.seriesName + '警情列表',
		maxmin: true,
		skin:'layer-ext-moon',
		area: ['1100px','600px'],
		offset: ['10px', '10px'],
		shade: false,
		maxmin: true,
		shadeClose: true,
		closeBtn: 1, 
		content: 'bzjqList.html',
		btn:['关闭'],
		btn1:function (index,layero) {
			layer.close(index);
		},
		
	})
}

function openFrame2(params){
	
	dataParams = {
		"lx":lx,
		"name":encodeURI(params.name),
		"ksms":ksms,
		"zxsj":zxsj,
		"point":"",
		"type":2
	};
	layer.open({
		id:1,
		type: 2,
		title:params.name + '警情列表',
		maxmin: true,
		skin:'layer-ext-moon',
		area: ['1100px','600px'],
		offset: ['10px', '10px'],
		shade: false,
		maxmin: true,
		shadeClose: true,
		closeBtn: 1, 
		content: 'bzjqList.html',
		btn:['关闭'],
		btn1:function (index,layero) {
			layer.close(index);
		}
	})
}

function openFrame3(params){
	
	dataParams = {
			"lx":lx,
			"name":encodeURI(params.name),
		"ksms":ksms,
		"zxsj":zxsj,
		"point":"",
		"type":3
	};
	layer.open({
		id:1,
		type: 2,
		title:params.name + '警情列表',
		maxmin: true,
		skin:'layer-ext-moon',
		area: ['1100px','600px'],
		offset: ['10px', '10px'],
		shade: false,
		maxmin: true,
		shadeClose: true,
		closeBtn: 1, 
		content: 'bzjqList.html',
		btn:['关闭'],
		btn1:function (index,layero) {
			layer.close(index);
		}
	})
}

function openFrame4(params){
	
	dataParams = {
			"lx":lx,
			"name":encodeURI(params),
		"ksms":ksms,
		"zxsj":zxsj,
		"point":"",
		"type":4
	};
	layer.open({
		id:1,
		type: 2,
		title: '警情列表',
		maxmin: true,
		skin:'layer-ext-moon',
		area: ['1100px','600px'],
		offset: ['10px', '10px'],
		shade: false,
		maxmin: true,
		shadeClose: true,
		closeBtn: 1, 
		content: 'bzjqList.html',
		btn:['关闭'],
		btn1:function (index,layero) {
			layer.close(index);
		}
	})
}

function openFrame5(params){
	
	dataParams = {
			"lx":lx,
			"name":encodeURI(params),
		"ksms":ksms,
		"zxsj":zxsj,
		"point":"",
		"type":5
	};
	layer.open({
		id:1,
		type: 2,
		title:params + '警情列表',
		maxmin: true,
		skin:'layer-ext-moon',
		area: ['1100px','600px'],
		offset: ['10px', '10px'],
		shade: false,
		maxmin: true,
		shadeClose: true,
		closeBtn: 1, 
		content: 'bzjqList.html',
		btn:['关闭'],
		btn1:function (index,layero) {
			layer.close(index);
		}
	})
}

function openFrame6(params){
	
	dataParams = {
			"lx":lx,
			"name":encodeURI(params),
		"ksms":ksms,
		"zxsj":zxsj,
		"point":"",
		"type":6
	};
	layer.open({
		id:1,
		type: 2,
		title:params + '警情列表',
		maxmin: true,
		skin:'layer-ext-moon',
		area: ['1100px','600px'],
		offset: ['10px', '10px'],
		shade: false,
		maxmin: true,
		shadeClose: true,
		closeBtn: 1, 
		content: 'bzjqList.html',
		btn:['关闭'],
		btn1:function (index,layero) {
			layer.close(index);
		}
	})
}


	