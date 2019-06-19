var option1 = {
            //图表提示框
            tooltip : {
                trigger: 'axis'
            },
            grid: {			
			    x:'6%',
				y:'10%',
				x2:'2%',
				y2:'15%',
				borderWidth:'0'
				
			},
            //是否充许托动自动计算
            calculable : true,
            //图例
            legend: {
			data:[ {name: '盗窃',  
					 textStyle:{color:'rgb(109,109,109)'}  
				   },  
				  {name:'抢劫',  
					textStyle:{color:'rgb(109,109,109)'}
				  },  
				  {name:'诈骗',  
					textStyle:{color:'rgb(109,109,109)'}
				  }
				  
			  ],
			top:'top'
		},
            //x轴
            xAxis : [
                {
                    type : 'category',
					axisLine:{  
						  lineStyle:{   
								color:'rgb(109,109,109)',		  
								width:1  
							  }  
						  }, 
				    splitLine:{show: false},//去除网格线
					splitArea : {show : false},//保留网格区域	
					axisLabel: {  //文字倾斜
					   interval:0,  
					   rotate:40  		   
					}, 
                   data : ['龙泉所','十陵所','北干道','同安所','大面所']
                }
            ],
            //y轴
            yAxis : [
                {
                    type : 'value',
                   // name : '本月新增警情',
					verticalAlign: 'middle',
                    axisLabel : {
                        formatter: '{value}'
                    },
					axisLine:{  
						  lineStyle:{   
								color:'rgb(109,109,109)',			  
								width:1  
						  }  
					  }, 

						splitLine:{show: false},//去除网格线
						splitArea : {show : false}//保留网格区域
					}
            ],
            //系列
            series : [
                {
                    name:'盗窃', 
					data: [569,1023,1489,956,1679],
                    type:'bar',
					//barWidth : 12,
					itemStyle: {
					  normal: {
						  //color: 'rgba(61,99,160,0.5)'						   
						    color: 'rgb(251,100,47)',
							label:{show:true,position:'top'}
							
						}
                    }
                },
                {
                    name:'抢劫',
					data: [658,1201,1376,592,1400],
                    type:'bar',
					//barWidth : 12,
					itemStyle: {
					  normal: {
						  //color: 'rgba(128,155,202,0.5)'
						 color: 'rgb(1,171,255)',
						 label:{show:true,position:'top'}
						}
                    }
                },
                {
                    name:'诈骗', 
					data: [568,1100,781,1104,585],
                    type:'bar',
					//barWidth : 12,
					itemStyle: {
					  normal: {
						  //color: 'rgba(128,155,202,0.5)'
						 color: 'rgb(46,199,201)',
						 label:{show:true,position:'top'}
						}
                    }
                }
                
            ]
        };	
		
var option2 = {
     
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
           legend : {
			   	orient: 'horizontal',
				data:[ {name: '盗窃',  
						 textStyle:{color:'rgb(109,109,109)'}  
					   },  
					  {name:'抢劫',  
						textStyle:{color:'rgb(109,109,109)'}
					  },  
					  {name:'诈骗',  
						textStyle:{color:'rgb(109,109,109)'}
					  }
					  
				  ],
				top:'top'
			   },
			   
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '50%',
                    center: ['50%', '50%'],					
					color: [ '#14be98', '#7ccde8', '#ec7c68','#8ad7c8', '#9f8c32'],
					itemStyle: {
							 normal: {   
								//borderColor: "rgba(40,76,136,0.1)",
								//borderWidth: 3							
							} 
                    },					
                    data:[
                        {value:2, name:'盗窃'},
                        {value:22, name:'抢劫'},
                        {value:40, name:'诈骗'}
                    ]
                }
            ]
        };


option3 = {
      tooltip: {
        trigger: 'axis'
    },

	legend: {
		data:[ {name: '盗窃',  
                 textStyle:{color:'rgb(109,109,109)'}  
               },  
              {name:'抢劫',  
                textStyle:{color:'rgb(109,109,109)'}
			  },  
              {name:'诈骗',  
                textStyle:{color:'rgb(109,109,109)'}
			  }
			  
          ],
		top:'top'
		//right: 'right',
    },
	 grid: {
        left: '2%',
        right: '2%',
        bottom: '10%',
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
		axisLabel: {  //文字倾斜
		   interval:0,  
		   rotate:40  		   
		}, 
		data : ['龙泉所','十陵所','北干道','同安所','大面所']
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
            name:'盗窃', 
            data: [569,1023,1489,956,1679],
            type: 'line',
			color:['rgb(251,100,47)'],
			symbolSize: 5,
            smooth: true,
			lineStyle: {
                normal: {
                    width: 3,
                    shadowColor: 'rgba(251,100,47,0.5)',
                    shadowBlur: 20,
                    shadowOffsetY: 5
                }
            },
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
				}
        },
        {
            name:'抢劫',
            data: [658,1201,1376,592,1400],
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
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
				}
        },
        {
            name:'诈骗', 
            data: [568,1100,781,1104,585],
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
			itemStyle: {
				 normal: {
					 label:{show:true,position:'top'}
					}
				}
        }
    ]
};