var option1 = {

	    tooltip: {
			trigger: 'item',
			formatter: "{b} : {c}条<br/>环比：5%"
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
						formatter: '{c}条\n环比：5%',
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
       // formatter: "{a} <br/>{b} : 环比 {c}%"
    },
	legend: {
		data:[ 
              {name:'抢劫',  
                textStyle:{color:'rgb(109,109,109)'}
			  },
			  {name:'盗窃',  
                textStyle:{color:'rgb(109,109,109)'}
			  },
			  {name:'诈骗',  
                textStyle:{color:'rgb(109,109,109)'}
			  },
			  {name:'抢夺',  
                textStyle:{color:'rgb(109,109,109)'}
			  },
			  {name:'重复警情',  
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
            data: [2,3,4,5,4,2,1],
            type: 'line',
			color:['rgb(1,171,255)'],
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
			label: {
					normal: {
						formatter: '{c}%',
					}
			},
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
			}
        },
		{
            name:'盗窃',
            data: [2,2,3,4,3,1,2],
            type: 'line',
			color:['rgb(251,100,47)'],
			symbolSize: 5,
			shadowColor:"#000000",          //阴影颜色
			shadowOffsetX:0,            //阴影水平方向上的偏移距离。
			shadowOffsetY:0,            //阴影垂直方向上的偏移距离
			shadowBlur:10,              //图形阴影的模糊大小。
            smooth: true,
			lineStyle: {
                normal: {
                    width: 3,
                    shadowColor: 'rgba(251,100,47,0.5)',
                    shadowBlur: 20,
                    shadowOffsetY: 5
                }
            },
			label: {
					normal: {
						formatter: '{c}%',
					}
			},
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
			}
		},
		{
            name:'诈骗',
            data: [3,1,4,3,3,3,1],
            type: 'line',
			color:['rgb(46,199,201)'],
			symbolSize: 5,
			shadowColor:"#000000",          //阴影颜色
			shadowOffsetX:0,            //阴影水平方向上的偏移距离。
			shadowOffsetY:0,            //阴影垂直方向上的偏移距离
			shadowBlur:10,              //图形阴影的模糊大小。
            smooth: true,
			lineStyle: {
                normal: {
                    width: 3,
                    shadowColor: 'rgba(46,199,201,0.5)',
                    shadowBlur: 20,
                    shadowOffsetY: 5
                }
            },
			label: {
					normal: {
						formatter: '{c}%',
					}
			},
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
			}
		},
		{
            name:'抢夺',
            data: [1,3,3,4,5,4,2],
            type: 'line',
			color:['rgb(216,122,128)'],
			symbolSize: 5,
			shadowColor:"#000000",          //阴影颜色
			shadowOffsetX:0,            //阴影水平方向上的偏移距离。
			shadowOffsetY:0,            //阴影垂直方向上的偏移距离
			shadowBlur:10,              //图形阴影的模糊大小。
            smooth: true,
			lineStyle: {
                normal: {
                    width: 3,
                    shadowColor: 'rgba(216,122,128,0.5)',
                    shadowBlur: 20,
                    shadowOffsetY: 5
                }
            },
			label: {
					normal: {
						formatter: '{c}%',
					}
			},
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
			}
		},
		{
            name:'重复警情',
            data: [1,4,5,6,5,3,2],
            type: 'line',
			color:['rgb(182,162,222)'],
			symbolSize: 5,
			shadowColor:"#000000",          //阴影颜色
			shadowOffsetX:0,            //阴影水平方向上的偏移距离。
			shadowOffsetY:0,            //阴影垂直方向上的偏移距离
			shadowBlur:10,              //图形阴影的模糊大小。
            smooth: true,
			lineStyle: {
                normal: {
                    width: 3,
                    shadowColor: 'rgba(182,162,222,0.5)',
                    shadowBlur: 20,
                    shadowOffsetY: 5
                }
            },
			label: {
					normal: {
						formatter: '{c}%',
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
