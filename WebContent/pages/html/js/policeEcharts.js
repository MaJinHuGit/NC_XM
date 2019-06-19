var option1 = {
     
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
           legend : {
			   	orient: 'vertical',
				right: 'right',
				bottom: 'bottom',
				data:[ {name: '火灾事故' , textStyle:{color:"#ffffff"}},
					   {name: '违法犯罪' , textStyle:{color:"#ffffff"}},
					   {name: '交通事故' , textStyle:{color:"#ffffff"}},
					   {name: '咨询求助' , textStyle:{color:"#ffffff"}},
					   {name: '骚扰误报' , textStyle:{color:"#ffffff"}}
					  ]
			   },
            calculable : true,
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '50%',
                    center: ['50%', '45%'],					
					color: [ '#14be98', '#7ccde8', '#ec7c68','#8ad7c8', '#9f8c32'],
					itemStyle: {
							 normal: {   
								//borderColor: "rgba(40,76,136,0.1)",
								//borderWidth: 3   
							} 
                    },					
                    data:[
                        {value:2, name:'火灾事故'},
                        {value:22, name:'违法犯罪'},
                        {value:40, name:'交通事故'},
						{value:33, name:'咨询求助'},
                        {value:3, name:'骚扰误报'}
                    ]
                }
            ]
        };
		
option2 = {
      tooltip: {
        trigger: 'axis'
    },

	legend: {
		data:[ {name: '2017',  
                 textStyle:{color:'rgb(109,109,109)'}  
               },  
              {name:'2018',  
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
        data: ['1日', '2日', '3日', '4日', '5日', '6日', '7日', '8日', '9日', '10日', '11日', '12日', '13日', '14日', '15日']
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
            name:'2017', 
            data: [569,698,811,982,1023,1489,956,1679,1545,1698,1462,1999,2000,1898,1769],
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
            name:'2018',
            data: [658, 824,964,1290,1601,1876,1409,1201,1409,1655,1218,1599,1688,1152,1400],
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
        }
    ]
};
var option3 = {
            //图表提示框
            tooltip : {
                trigger: 'axis'
            },
            grid: {			
			    x:'6%',
				y:'12%',
				x2:'2%',
				y2:'19%',
				borderWidth:'0',
				
			},
            //是否充许托动自动计算
            calculable : true,
            //图例
            legend: {
					data:[ {name: '本周',  
							 textStyle:{color:"#809bca"}  
						   },  
						  {name:'上周',  
							textStyle:{color:"#809bca"}
						  }
					  ],
					top: 'top'
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
					   rotate:40 , 		   
					}, 
                    data : ['盗窃电动车','其他盗窃','入室盗窃','摩托车盗窃','街面诈骗','通讯诈骗','盗窃汽车','抢夺','非侵财']
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
						splitArea : {show : false},//保留网格区域
					}
            ],
            //系列
            series : [
                {
                    name:'本周',
                    type:'bar',
					//barWidth : 12,
					itemStyle: {
					  normal: {
						  //color: 'rgba(61,99,160,0.5)'						   
						    color: 'rgb(66,143,213)',
							label:{show:true,position:'top'}
						}
                    },
                    data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 152]
                },
                {
                    name:'上周',
                    type:'bar',
					//barWidth : 12,
					itemStyle: {
					  normal: {
						  //color: 'rgba(128,155,202,0.5)'
						 color: 'rgb(65,80,111)',
						 label:{show:true,position:'top'}
						}
                    },
                    data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 148]
                }
                
            ]
        };
		
		