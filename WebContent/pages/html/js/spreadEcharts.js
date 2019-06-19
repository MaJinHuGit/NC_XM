var option1= {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },

    series: [
        {
            name:'盗窃',
            type:'pie',
            //selectedMode: 'single',
            radius: [0, '30%'],

            label: {
                normal: {
                    position: 'inner'
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:35, name:'1时'},
				{value:9, name:'4时'},
				{value:9, name:'8时'},
				{value:18, name:'12时'},
				{value:79, name:'16时'},
				{value:12, name:'20时'},
				{value:11, name:'24时'}
            ]
        },
        {
            name:'盗窃',
            type:'pie',
			selectedMode: 'single',
            radius: ['40%', '55%'],
            label: {
                normal: {
                    //formatter: 'value',
                    backgroundColor: '#eee',
                    borderColor: '#aaa',
                    borderWidth: 1,
                    borderRadius: 4,
					position: 'top',
                    padding: [0, 7],
                    rich: {
                        a: {
                            color: '#999',
                            lineHeight: 22,
                            align: 'center'
                        },
                        hr: {
                            borderColor: '#aaa',
                            width: '100%',
                            borderWidth: 0.5,
                            height: 0
                        },
                        b: {
                            fontSize: 16,
                            lineHeight: 33
                        },
                        per: {
                            color: '#eee',
                            backgroundColor: '#334455',
                            padding: [2, 4],
                            borderRadius: 2
                        }
                    }
                }
            },
            data:[
                {value:335, name:'9月1日', selected:true},
                {value:310, name:'9月2日'},
                {value:234, name:'9月3日'},
                {value:135, name:'9月4日'},
                {value:1048, name:'9月5日'},
                {value:251, name:'9月6日'},
                {value:147, name:'9月7日'},
                {value:102, name:'9月8日'}
            ]
        }
    ]
};


var data = [
    [5, 10, 7.5],
    [40, 54, 47],
    [30, 65, 47.5],
    [25, 56, 40.5],
    [20, 40, 30],
    [20, 40, 30],
    [25, 40, 32.5],
    [18, 40, 29],
    [20, 35, 27.5],
    [20, 30, 25],
    [18, 30, 24],
    [20, 27, 24.5],
    [15, 28, 21.5],
    [15, 23, 19],
    [16, 35, 51],
    [15, 26, 20.5],
    [15, 24, 19.5],
    [15, 20, 17.5],
    [1, 8, 4.5]
];
var cities = ['龙泉派出所', '黄土派出所', '成都市洛带派出所', '龙泉驿区公安分局', '万兴派出所止马店村警务室', '茶店派出所', '山泉铺派出所', '平安派出所(红岭路)', '山泉镇派出所', '长安派出所', '万兴派出所(下街)', '洛带古镇派出所', '十陵公安派出所', '大面派出所', '怡和派出所', '同安派出所', '西河派出所', '西河镇派出所', '柏合派出所'];
var barHeight = 5;
option2 = {
     title: {
        text: '盗窃共发生898件',
        subtext: '日最大值：65件；日最小值：1件；日平均值：36件。',
    },
	legend: {
        show: true,
        data: ['价格范围', '均值']
    },
    grid: {
        top: 100
    },
    angleAxis: {
        type: 'category',
        data: cities
    },
    tooltip: {
        show: true,
        formatter: function (params) {
            var id = params.dataIndex;
            return cities[id] + '<br>最低：' + data[id][0] + '<br>最高：' + data[id][1] + '<br>平均：' + data[id][2];
        }
    },
    radiusAxis: {
    },
    polar: {
    },
    series: [{
        type: 'bar',
        itemStyle: {
            normal: {
                color: 'transparent'
            }
        },
        data: data.map(function (d) {
            return d[0];
        }),
        coordinateSystem: 'polar',
        stack: '最大最小值',
        silent: true
    }, {
        type: 'bar',
        data: data.map(function (d) {
            return d[1] - d[0];
        }),
        coordinateSystem: 'polar',
        name: '价格范围',
        stack: '最大最小值'
    }, {
        type: 'bar',
        itemStyle: {
            normal: {
                color: 'transparent'
            }
        },
        data: data.map(function (d) {
            return d[2] - barHeight;
        }),
        coordinateSystem: 'polar',
        stack: '均值',
        silent: true,
        z: 10
    }, {
        type: 'bar',
        data: data.map(function (d) {
            return barHeight * 2
        }),
        coordinateSystem: 'polar',
        name: '均值',
        stack: '均值',
        barGap: '-100%',
        z: 10
    }],
    legend: {
        show: true,
        data: ['A', 'B', 'C']
    }
};