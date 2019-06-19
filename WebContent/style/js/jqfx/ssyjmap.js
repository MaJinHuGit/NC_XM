var map = new BMap.Map("longquanMap");          // 创建地图实例  
var point = new BMap.Point(104.274227,30.553425);  // 创建点坐标
var opts = {type: BMAP_NAVIGATION_CONTROL_LARGE};
map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别  
map.enableScrollWheelZoom(true);
var local = new BMap.LocalSearch(map, {
	renderOptions:{map: map}
});	

function clear(){
	map.clearOverlays();
}

function showHeatMap(orgname) {
	//热力图
	var points = [];
	if(orgname==""){
		points =[
			{"lng":104.274227,"lat":30.553425,"count":390,name:"龙泉派出所"},
			{"lng":104.198146,"lat":30.594158,"count":168,name:"大面派出所"},
			{"lng":104.33647,"lat":30.648352,"count":115,name:"洛带派出所"},
			{"lng":104.323937,"lat":30.545266,"count":15,name:"山泉派出所"},
			{"lng":104.229162,"lat":30.523758,"count":151,name:"柏合派出所"},
			{"lng":104.194691,"lat":30.665779,"count":140,name:"十陵派出所"},
			{"lng":104.274518,"lat":30.562852,"count":66,name:"西河派出所"},
			{"lng":104.341453,"lat":30.706663,"count":66,name:"洪安派出所"},
			{"lng":104.298387,"lat":30.693327,"count":51,name:"黄土派出所"},
			{"lng":104.160201,"lat":30.614568,"count":50,name:"洪河派出所"},
			{"lng":104.394536,"lat":30.654387,"count":240,name:"万兴派出所"},
			{"lng":104.193852,"lat":30.552691,"count":240,name:"经济开发区派出所"},
			{"lng":104.266031,"lat":30.570695,"count":150,name:"北干道派出所"},
			{"lng":104.278727,"lat":30.581712,"count":0,name:"平安派出所"},
			{"lng":104.31748,"lat":30.615828,"count":258,name:"同安派出所"},
			{"lng":104.358853,"lat":30.510944,"count":351,name:"茶店派出所"},
			{"lng":104.267179,"lat":30.578138,"count":151,name:"怡和派出所"},
		];
	}else{
		points =[
			{"lng":104.394536,"lat":30.654387,"count":240,name:"万兴派出所"},
			{"lng":104.193852,"lat":30.552691,"count":240,name:"经济开发区派出所"},
			{"lng":104.266031,"lat":30.570695,"count":150,name:"北干道派出所"},
		];
	}
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
	map.addOverlay(heatmapOverlay);
	heatmapOverlay.setDataSet({data:points,max:400});
	//是否显示热力图
	function openHeatmap(){
		heatmapOverlay.show();
	}
	function closeHeatmap(){
		heatmapOverlay.hide();
	}
	
	for (var i = 0; i < points.length; i ++) {
		var point = new BMap.Point(points[i].lng,points[i].lat,points[i].name);
		
		var label = new BMap.Label(points[i].name,{offset:new BMap.Size(20,-10)});
		//设置文字样式
		label.setStyle({
			color: '#333',
			border: '1px solid "#ff8355"',
			borderRadius: "5px",
			textAlign: "center",
		});
		addMarker(point,label);
	}
}

//标注点及标签
function addMarker(point,label){
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);
	marker.setLabel(label);
	marker.addEventListener("click",attribute);
	//单击触发事件
	function attribute(){
		clear();
		showHeatMap("");
	}	
}

function setGradient(){
	var gradient = {};
	var colors = document.querySelectorAll("input[type='color']");
	colors = [].slice.call(colors,0);
	colors.forEach(function(ele){
		gradient[ele.getAttribute("data-key")] = ele.value; 
	});
	heatmapOverlay.setOptions({"gradient":gradient});
}
