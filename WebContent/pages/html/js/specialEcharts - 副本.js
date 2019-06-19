var option1 = {

	    tooltip: {
			trigger: 'item',
			formatter: "{b} : {c}条<br/>环比{d}%"
		},
		grid: {
			x: '12%',
			y: '6%',
			x2: '2%',
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
				data : ['重复报警','赌博','诈骗','抢夺','入室盗窃','盗窃','抢劫']
			}
		],
		series : [
			{
				
				type:'bar',
				stack: '总量',
				label: {
					normal: {
						formatter: '{c}条\n环比{d}%',
					}
				},
				itemStyle : { normal: {color:'rgb(66,143,213)',label : {show: true, position: 'right'}}},
				data:[356,367,385,402,405,421,434]
			}
		]
	};
	
option2 = {
	tooltip : {
        trigger: 'axis',
        formatter: "{a} <br/>{b} : 环比 {c}%"
    },
	legend: {
		data:[ 
              {name:'抢劫',  
                textStyle:{color:'rgb(109,109,109)'}
			  }
          ],
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
              width:1  
			  }  
		  },
		splitLine:{show: false},//去除网格线
		splitArea : {show : false},//保留网格区域
        data: ['09/03', '09/04', '09/05', '09/06', '09/07', '09/08', '09/09']
    },
    yAxis: {
		axisLine:{  
          lineStyle:{  
              //color:'#284c88', 
				color:'rgb(109,109,109)',			  
              width:1  
			  }  
		  }, 
		splitLine:{show: false},//去除网格线
        splitArea : {show : false},//保留网格区域		  
	    type: 'value'
    },
    series: [
        {
            name:'抢劫',
            data: [2,-2,4,5,-1,4,3],
            type: 'line',
			color:['rgb(66,143,213)'],
			symbolSize: 5,
			shadowColor:"#000000",          //阴影颜色
			shadowOffsetX:0,            //阴影水平方向上的偏移距离。
			shadowOffsetY:0,            //阴影垂直方向上的偏移距离
			shadowBlur:10,              //图形阴影的模糊大小。
            smooth: true,
			lineStyle: {
                normal: {
                    width: 3,
                    shadowColor: 'rgba(1,171,255,0.5)',
                    shadowBlur: 20,
                    shadowOffsetY: 5
                }
            },
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
			}
        }
    ]
};
