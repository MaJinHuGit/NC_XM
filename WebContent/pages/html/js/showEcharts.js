  function createRandomItemStyle() {
            return {
                normal: {
                    color: 'rgb(' + [
                        Math.round(Math.random() * 160),
                        Math.round(Math.random() * 160),
                        Math.round(Math.random() * 160)
                    ].join(',') + ')'
                    
                   
                }
            };
        }

var option1 = {

            tooltip: {
                show: true
            },
            series: [{
                name: 'Google Trends',
                type: 'wordCloud',
                size: ['80%', '80%'], //字符云大小，支持绝对值（px）和百分比
                textRotation : [0, 45, 90, -45], //文字旋转角度可选列表，默认会随机从水平（0）和垂直（90）两个方向中选择，可以设置多个可选角度，例如 [0, -45, 45, 90]
                textPadding: 0,
                //字体大小自动计算配置，默认开启自动计算，程序会根据每个数据的 value 大小以及画布的大小控制字体大小以达到最佳的显示效果。minSize 可以强制最小字体。 关闭的时候字体大小取 itemStyle.normal.textStyle.fontSize，建议开启。
                autoSize: {
                    enable: true,
                    minSize: 14
                },
                data: [
  {name: "出入境持出入境证件人员信息(境内人员)",value:2900,itemStyle: createRandomItemStyle()},
 {name: "出入境常住境外人员信息(境外人员)",value:100,itemStyle: createRandomItemStyle()},
 {name: "禁毒毒品案件查获制毒物品信息",value:2450,itemStyle: createRandomItemStyle()},
 {name: "禁毒毒品案件信息",value:2150,itemStyle: createRandomItemStyle()},
 {name: "禁毒涉毒人员基本信息表",value:450,itemStyle: createRandomItemStyle()},
 {name: "禁毒吸毒人员基本信息",value:500,itemStyle: createRandomItemStyle()},
 {name: "监管全省违法犯罪人员",value:1100,itemStyle: createRandomItemStyle()},
 {name: "经侦案件信息登记主表",value:850,itemStyle: createRandomItemStyle()},
 {name: "经侦犯罪嫌疑单位信息",value:350,itemStyle: createRandomItemStyle()},
 {name: "经侦犯罪嫌疑人信息登记",value:250,itemStyle: createRandomItemStyle()},
 {name: "交警卡口信息",value:1300,itemStyle: createRandomItemStyle()},
 {name: "交警电子眼监控记录",value:1600,itemStyle: createRandomItemStyle()},
 {name: "交警一般事故信息主表",value:750,itemStyle: createRandomItemStyle()},
 {name: "交警危险化学品运输证信息",value:200,itemStyle: createRandomItemStyle()},
 {name: "交警机动车信息",value:2400,itemStyle: createRandomItemStyle()},
 {name: "交警驾驶人信息",value:1250,itemStyle: createRandomItemStyle()},
 {name: "交警简易事故信息主表",value:2500,itemStyle: createRandomItemStyle()},
 {name: "交警违法记录",value:4650,itemStyle: createRandomItemStyle()},
 {name: "警综案事件案件信息",value:1650,itemStyle: createRandomItemStyle()},
 {name: "警综案事件报案人/受害人/证人等人员信息",value:1600,itemStyle: createRandomItemStyle()},
 {name: "警综案事件警情信息",value:4350,itemStyle: createRandomItemStyle()},
 {name: "警综案事件人员案件关联信息",value:150,itemStyle: createRandomItemStyle()},
 {name: "警综案事件物品信息表",value:4100,itemStyle: createRandomItemStyle()},
 {name: "警综案事件涉案物品电脑详细信息",value:3150,itemStyle: createRandomItemStyle()},
 {name: "警综案事件涉案物品机动车详细信息",value:2000,itemStyle: createRandomItemStyle()},
 {name: "警综案事件涉案物品枪详细信息",value:4150,itemStyle: createRandomItemStyle()},
 {name: "警综案事件涉案物品移动设备信息",value:3250,itemStyle: createRandomItemStyle()},
 {name: "警综案事件涉案物品银行卡详细信息",value:3850,itemStyle: createRandomItemStyle()},
 {name: "警综案事件嫌疑人关系人信息",value:1150,itemStyle: createRandomItemStyle()},
 {name: "警综案事件嫌疑人联系方式信息",value:4300,itemStyle: createRandomItemStyle()},
 {name: "警综案事件嫌疑人信息",value:2150,itemStyle: createRandomItemStyle()},
 {name: "警综单位从业人员-法人轨迹信息",value:45,itemStyle: createRandomItemStyle()},
 {name: "警综单位从业人员信息",value:50,itemStyle: createRandomItemStyle()},
 {name: "警综单位单位地址信息",value:15,itemStyle: createRandomItemStyle()},
 {name: "警综单位-公共娱乐场所信息",value:30,itemStyle: createRandomItemStyle()},
 {name: "警综单位-旅馆信息",value:50,itemStyle: createRandomItemStyle()},
 {name: "警综单位-内保单位信息",value:20,itemStyle: createRandomItemStyle()},
 {name: "警综单位-汽车租赁信息",value:5,itemStyle: createRandomItemStyle()},
 {name: "警综单位涉危化涉爆信息",value:25,itemStyle: createRandomItemStyle()},
 {name: "警综单位实有单位基本信息",value:25,itemStyle: createRandomItemStyle()},
 {name: "警综单位-网吧信息",value:50,itemStyle: createRandomItemStyle()},
 {name: "警综人口实有人口关系人信息",value:40,itemStyle: createRandomItemStyle()},
 {name: "警综人口实有人口居住轨迹",value:5,itemStyle: createRandomItemStyle()},
 {name: "警综人口实有人口联系方式",value:40,itemStyle: createRandomItemStyle()},
 {name: "警综人口实有人口属性轨迹信息",value:15,itemStyle: createRandomItemStyle()},
 {name: "警综人口实有人口通信录信息",value:30,itemStyle: createRandomItemStyle()},
 {name: "警综人口实有人口虚拟身份",value:20,itemStyle: createRandomItemStyle()},
 {name: "警综人口实有人口信息",value:20,itemStyle: createRandomItemStyle()},
 {name: "警综刑侦标采人员社会关系信息",value:15,itemStyle: createRandomItemStyle()},
 {name: "警综刑侦标采人员通讯录信息",value:40,itemStyle: createRandomItemStyle()},
 {name: "警综刑侦标采人员信息",value:45,itemStyle: createRandomItemStyle()},
 {name: "警综治安标准地址库信息",value:20,itemStyle: createRandomItemStyle()},
 {name: "警综治安房屋产权人信息(单位)",value:50,itemStyle: createRandomItemStyle()},
 {name: "警综治安房屋产权人信息(个人)",value:10,itemStyle: createRandomItemStyle()},
 {name: "警综治安房屋基本信息",value:20,itemStyle: createRandomItemStyle()},
 {name: "警综治安小区信息",value:20,itemStyle: createRandomItemStyle()},
 {name: "警综字典信息字典表信息",value:20,itemStyle: createRandomItemStyle()},
 {name: "情报中心重点人员",value:5,itemStyle: createRandomItemStyle()}


                ]
            }]
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
