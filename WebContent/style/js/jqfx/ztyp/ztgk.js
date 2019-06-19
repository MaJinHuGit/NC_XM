/**
 * 警情类别统计
 * @returns
 */
var colorArr1 = [
			'rgb(0,255,255)',
			'rgb(0,255,0)',
			'rgb(255,0,0)',
			'rgb(148,0,211)',
			'rgb(250,20,147)',
			'rgb(0,0,255)'
		];
		var colorArr2 = [
			'rgb(0,255,255,0.5)',
			'rgb(0,255,0,0.5)',
			'rgb(255,0,0,0.5)',
			'rgb(148,0,211,0.5)',
			'rgb(250,20,147,0.5)',
			'rgb(0,0,255,0.5)'
			];
var end_obj = new Array();
function initEchart1(startTime,endTime,strArr,nameArr,parentArr,pcsArr){
	var url = "../jqfx/selectType/getTu2";

	$.get(url,{
		startTime:startTime,
		endTime:endTime,
		strArr:strArr,
		nameArr:encodeURI(nameArr),
		parentArr:parentArr,
		pcsArr:encodeURI(pcsArr)
	},
		function(data){
		var leg = new Array();
		var hbArr = new Array();
		var serArr = new Array();
		var obj = data.list;
		for(var i=0;i<obj.length;i++){
			var countNow = obj[i].countNow;
			var typeName = obj[i].typeName;
			var startTime = obj[i].startTime;
			var endTime = obj[i].endTime;
			var num = obj[i].typeNum;
			var isParent = obj[i].isParent;
			//统计图展示效果格式化
			end_obj.push(obj[i].hb+"@"+obj[i].typeName);
			
			leg.push(typeName);
			serArr.push({
				name: typeName,
				value:countNow,
				startTime:startTime,
				endTime:endTime,
				num:num,
				isParent:isParent
			});
		}
		
		chart1.setOption({
			yAxis : [ {data : leg}],
			series : [ {data:serArr}]
		});
		chart1.on('click',function(params){
			var startTime = params.data.startTime;
			var endTime = params.data.endTime;
			var num = params.data.num;
			var name = params.data.name;
			var isParent = params.data.isParent;
			var pcs = encodeURI(pcsArr);
			var index= layer.open({
				type : 2,
				title : name,
				maxmin : true,
				area:['1000px', '580px'],
				shadeClose : false, // 点击遮罩关闭层
				content : 'tu2List.html?startTime='+startTime+'&endTime='+endTime+'&num='+num+'&isParent='+isParent+'&pcs='+pcs // iframe的url
			});
		});
	})
	
}


/**
 * 警情走势图
 * @returns
 */
function initEchart2(startTime,endTime,strArr,nameArr,parentArr,pcsArr){
	var url = "../jqfx/selectType/getTu1";
	$.get(url,{
		startTime:startTime,
		endTime:endTime,
		strArr:strArr,
		nameArr:encodeURI(nameArr),
		parentArr:parentArr,
		pcsArr:encodeURI(pcsArr)
	},
		function(data){
		
		//返回的数据集合
		var dataList = data.list;
		
		var leg = new Array();
		var series = new Array();
		//X轴数据
		var X = new Array();
		var X_data = dataList[0].dateList;
		for(var x=0;x<X_data.length;x++){
			X.push(X_data[x].day);
		}
		
		for(var i=0;i<dataList.length;i++){
			var type = dataList[i].typeName;
			var dateList = dataList[i].dateList;
			var countArr = new Array();
			for(var j=0;j<dateList.length;j++){
				countArr.push(dateList[j].count);
			}
			
			var s = {
					name:type,
					textStyle:{color:colorArr1[i]}
			}
			leg.push(s);
			
			var ser = {
					name:type,
		            data: countArr,
		            type: 'line',
					color:colorArr1[i],
					symbolSize: 5,
					shadowColor:"#000000",
					shadowOffsetX:0,
					shadowOffsetY:0,
					shadowBlur:10,
		            smooth: true,
					lineStyle: {
		                normal: {
		                    width: 3,
		                    color:colorArr1[i],
		                    shadowColor: colorArr2[i],
		                    shadowBlur: 20,
		                    shadowOffsetY: 5
		                }
		            },
					label: {
							normal: {
								formatter: '{c}',
							}
					},
					itemStyle: {
						 normal: {
							 label:{show:true,position:'top'}
							}
					}
			}
			series.push(ser);
		}
		chart2.setOption({
			xAxis : [{data : X}],
			series : series,
			legend : [{data : leg}]
		})
		
	});
}




var option1 = {

	    tooltip: {
			trigger: 'item',
			 formatter:function(value){
				
				 if(end_obj.length!=0){
						
						for(var i=0;i<end_obj.length;i++){
							var arry = end_obj[i].split("@")
							if(value['name']==arry[1]){
							
								return value['value']+'条\n环比：'+arry[0];
							}
						}
					}
					
				}
				
			
		},
		grid: {
			x: '12%',
			y: '6%',
			x2: '16%',
			y2: '4%',
			borderWidth:'0',
		},
		calculable : false,
		xAxis : [
			{
				splitNumber: 2, 
				scale: true, 
				show:false,
				type : 'value'
			}
		],
		yAxis : [
			{
				type : 'category',
				axisTick:{
					show:false
				},
				 splitLine:{
					show:false
				},
				axisLine:{  
				  lineStyle:{   
						color:'rgb(109,109,109)',		  
						width:1  
					  }  
				  }, 
				data : []
			}
		],
		series : [
			{
				
				type:'bar',
				stack: '总量',
				label: {
					normal: {
						formatter:function(value){
							console.log('第二个值：'+value['name']+':'+value['value'])
							if(end_obj.length!=0){
								
								for(var j=0;j<end_obj.length;j++){
								
									var arry = end_obj[j].split("@")
								
									if(value['name']==arry[1]){
							
										return value['value']+'条\n环比：'+arry[0];
									}
									
								}
							}
							
						}
						//	'{c}条\n环比：5%',
					}
				},
				itemStyle : { 
					normal: {
						color:colorArr1[0],
						label : {show: true, position: 'right'}
					}
				},
				data:[]
			}
		]
	};

option2 = {
		//提示框	
		tooltip : {
	        trigger: 'axis',
	       // formatter: "{a} <br/>{b} : 环比 {c}%"
	    },
	    //图例
		legend: {
			data:[],
			top:'top',
			//right: 'right',
	    },
		 grid: {
	        left: '2%',
	        right: '2%',
	        bottom: '3%',
			top:'12%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
			axisLine:{  
	          lineStyle:{  
	              color:'rgb(109,109,109)',	
	              width:2  
				  }  
			  },
			splitLine:{show: false},//去除网格线
			splitArea : {show : false},//保留网格区域
	        data: []
	    },
	    yAxis: {
			axisLine:{  
	          lineStyle:{  
	              //color:'#284c88', 
					color:'rgb(109,109,109)',			  
	              width:2  
				  }  
			  }, 
			splitLine:{show: false},//去除网格线
	        splitArea : {show : false},//保留网格区域		  
		    type: 'value'
	    },
	    series: []
	};






	

