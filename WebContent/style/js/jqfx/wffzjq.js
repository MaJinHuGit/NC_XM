	var now = new Date();//当天日期
	var nowTime = now.getTime();//今天本周的第几天
	var day = now.getDay();
	var oneDayTime = 24*60*60*1000;

	function loadWffz(){
		$("#dateTime3hidden").val(getWeekStartDate()+" - "+getWeekEndDate());
		$("#dateTime2").val(getWeekStartDate()+" - "+getWeekEndDate());
		var startTime = getWeekStartDate()+' '+'00:00:00';
		var endTime = getWeekEndDate()+' '+'23:59:59';
		
		//点击事件加载两个对比图与文字说明---时间段选择
		
		//全区违法犯罪警情趋势对比图----快速选择
		initEchart1(startTime,endTime);
		//本周违法犯罪警情对比图
		initEchart2(startTime,endTime);
		//违法犯罪警情文字描述
		initDs(startTime,endTime);
		
		$("#bzwffzdbt").text("违法犯罪警情对比图");
	}
	
	function formaDate(date){
		var myyear = date.getFullYear();
		var mymonth = date.getMonth()+1;
		var myweekday = date.getDate();
		
		if(mymonth<10){
			mymonth = "0"+mymonth;
		}
		if(myweekday<10){
			myweekday = "0"+myweekday;
		}
		return (myyear+"-"+mymonth+"-"+myweekday);
	}

	function getWeekStartDate(){
		var MondayTime = nowTime-(day-1)*oneDayTime;
		var monday = new Date(MondayTime);
		
		return formaDate(monday)
	}
	function getWeekEndDate(){
		var SundayTime = nowTime+(7-day)*oneDayTime;
		var sunday = new Date(SundayTime);
		return formaDate(sunday)
	}
	var myDate = new Date();
	//获取当前年
	var year=myDate.getFullYear();
	var  t1 = "2017";
	var  t2 = "2018";
	var  t3 = "本周";
	var  t4 = "上周";

	//未反馈违法犯罪具体警情统计表
	function showhide1(){
		$("#show_1").css("display","block");
		//没有有效数据
		$("#show_2").css("display","none");
		$("#show_3").css("display","none");
		$("#show_4").css("display","none");
		IFrameResize();
	}
	
	//全区各所辖区警情分类统计表
	function showhide2(){
		var time = $('input:radio[name=choose2]:checked').val();
		var text = $("#dateTime2").val();
		var startTime = '';
		var endTime = '';
		if(text == '' || text == null){
			endTime = time;
			startTime = time;
		}else{
			var str = text.split("-");
			startTime = str[0]+"-"+str[1]+"-"+str[2]+"00:00:00";
			endTime = str[3]+"-"+str[4]+"-"+str[5]+" 23:59:59";
		}
		qqJqFl(startTime,endTime);
		$("#show_1").css("display","none");
		$("#show_2").css("display","block");
		$("#show_3").css("display","none");
		$("#show_4").css("display","none");
		IFrameResize();
	}
	
	//全区各辖区违法警情累计同比统计表
	function showhide3(){
		var time = $('input:radio[name=choose2]:checked').val();
		var text = $("#dateTime2").val();
		var startTime = '';
		var endTime = '';
		if(text == '' || text == null){
			endTime = time;
			startTime = time;
		}else{
			var str = text.split("-");
			startTime = str[0]+"-"+str[1]+"-"+str[2]+"00:00:00";
			endTime = str[3]+"-"+str[4]+"-"+str[5]+" 23:59:59";
		}
		qqJqTb(startTime,endTime);
		$("#show_1").css("display","none");
		$("#show_2").css("display","none");
		$("#show_3").css("display","block");
		$("#show_4").css("display","none");
		IFrameResize();
	}
	
	//"三盗两抢一诈骗"警情及其他警情情况
	function showhide4(){
		$("#show_1").css("display","none");
		$("#show_2").css("display","none");
		$("#show_3").css("display","none");
		$("#show_4").css("display","block");
		//没有有效数据
		IFrameResize();
	}
	
	$('input:radio[name=choose2]').change(function(){
		var ksms = $(this).val();
		//全区违法犯罪警情趋势对比图
		initEchart1(ksms,ksms);
		//本周违法犯罪警情对比图
		initEchart2(ksms,ksms);
		//违法犯罪警情文字描述
		initDs(ksms,ksms);
		if($("#tab_2").attr("class")=="active"){
			qqJqFl(ksms,ksms);
		}
		if($("#tab_3").attr("class")=="active"){
			qqJqTb(ksms,ksms);
		}
		var name = "";
	
		if(ksms=="bz"){
			name = "本周"
			var  t1 = "本周";
			var  t2 = "上周";
			var  t3 = "本周";
			var  t4 = "上周";
		}
		if(ksms=="by"){
			name = "本月"
			var  t1 = "本月";
			var  t2 = "上月";
			var  t3 = "本月";
			var  t4 = "上月";
		}
		if(ksms=="bj"){
			name = "本季度"
			var  t1 = "本季度";
			var  t2 = "上季度";
			var  t3 = "本季度";
			var  t4 = "上季度";
		}
		if(ksms=="bn"){
			name = "本年度"
			var  t1 = "2017";
			var  t2 = "2018";
			var  t3 = "本年";
			var  t4 = "上月";
		}
	 //   $("#qqwffzqsdbt").text(name+"违法犯罪警情趋势对比图");
	    $("#bzwffzdbt").text(name+"违法犯罪警情对比图");
	});


/**
 * 本周违法犯罪警情对比图
 * @returns
 */
function initEchart2(startTime,endTime){
	var url = "../jqfx/jzjqxx/listByWeek";

	$.get(url,{startTime:startTime,endTime:endTime},function(data){
		
		var objLastWeek = data.lastWeek; 
		var objNowWeek = data.nowWeek; 
		var arrLastWeek = [];
		var arrNowWeek  = [];
		var legLastWeek = [];
		var legNowWeek = [];
		//上周统计
		for (var i = 0; i < objLastWeek.length; i++) {
			var val = objLastWeek[i].cunt;
			var name = objLastWeek[i].ct;
		
			
			legLastWeek.push(name);
			arrLastWeek.push({
					name: name,
					value:val
				});
			
		}
		//本周统计
		for (var i = 0; i < objNowWeek.length; i++) {
		
			var val = objNowWeek[i].cunt;
			var name = objNowWeek[i].ct;
			
			legNowWeek.push(name);
			arrNowWeek.push({
					name: name,
					value:val
				});
			//}
			
			
		}
		chart4.setOption({
			xAxis : [ {data : legNowWeek}],
			series : [ {data:arrNowWeek},{data:arrLastWeek}]
		});
	})
	
}

/**
 * 今年和去年每周违法犯罪警情对比图
 * @returns
 */
function initEchart1(startTime,endTime){
	
	var url = "../jqfx/jzjqxx/getYearWeek";

	$.get(url,{startTime:startTime,endTime:endTime},function(data){
		//console.info(data)
		var objLastYear = data.lastYearWeek; 
		var objNowYear = data.nowYearWeek; 
		var arrLastYear = [];
		var arrNowYear  = [];
		var legLastYear = [];
		var legNowYear = [];
		//上周统计
		for (var i = 0; i < objLastYear.length; i++) {
			var val = objLastYear[i].cunt;
			var name = objLastYear[i].ct;
		
			
			legLastYear.push(name);
			arrLastYear.push({
					name: name,
					value:val
				});
			
		}
		//本周统计
		for (var i = 0; i < objNowYear.length; i++) {
		
			var val = objNowYear[i].cunt;
			var name = objNowYear[i].ct;
			
			legNowYear.push(name);
			arrNowYear.push({
					name: name,
					value:val
				});
			//}
			
			
		}
		chart3.setOption({
			xAxis : [ {data : legNowYear}],
			series : [ {data:arrLastYear},{data:arrNowYear}]
			//legend:[ {name : legNowYear},{name : legNowYear}]
		});
	})
	
}
/**
 * 违法犯罪警情文字描述
 * @returns
 */
function initDs(startTime,endTime){
	
	var url = "../jqfx/jzjqxx/dsByWeek";

	$.get(url,{startTime:startTime,endTime:endTime},function(data){
		//console.info(data)
		//本周违法犯罪警情信息
		var nowData = data.nowWeek
		//上周违法犯罪警情信息
		var lastData = data.lastWeek
		//去年本周违法犯罪警情信息
		var lastYearData = data.lastYearWeek
		//非侵财
		var nowFqc = "";
		var lastFqc = "";
		//侵财
		var nowQc = "";
		var lastQc = "";
		//三盗两抢一诈骗
		var nowQdz = "";
		var lasQdz = "";
		var dqzLastYearSum = ""
		
		var nowSum = data.nowSum.cunt;
		var lastSum = data.nowSum.cunt;
		
		var hbFqc = "";
		var hbQc = "";
		var hbQdz = "";
		var tbQdz = "";
		for(var i = 0,len = nowData.length ;i <len ;i++){
			//本周违法犯罪警情信息
			if(nowData[i].ct=="非侵财"){
				nowFqc = nowData[i].cunt
			}else if(nowData[i].ct=="侵财"){
				nowQc = nowData[i].cunt
			}else if(nowData[i].ct=="三盗两抢一诈骗"){
				nowQdz = nowData[i].cunt
			}
			//上周违法犯罪警情信息
			if(lastData[i].ct=="非侵财"){
				lastFqc = lastData[i].cunt
			}else if(lastData[i].ct=="侵财"){
				lastQc = lastData[i].cunt
			}else if(lastData[i].ct=="三盗两抢一诈骗"){
				lastQdz = lastData[i].cunt
			}
			
			//去年本周违法犯罪警情信息
			if(lastYearData[i].ct=="三盗两抢一诈骗"){
				dqzLastYearSum = lastYearData[i].cunt
			}
			
		}
		//CountLastWeek==0?CountThisWeek*100:((CountThisWeek-CountLastWeek)/CountLastWeek)*100)
		//非侵财环比
		hbFqc = lastFqc==0?(nowFqc*100).toFixed(2):Number((nowFqc-lastFqc)/lastFqc*100).toFixed(2) ;
	//var max = a>b?a:b;
		//侵财环比
		hbQc = lastQc==0?(nowQc*100).toFixed(2):Number((nowQc-lastQc)/lastQc*100).toFixed(2) ;
		//	Number((nowQc-lastQc)/lastQc*100).toFixed(2) ;
		//三盗两抢一诈骗环比
		hbQdz =lastQdz==0?(nowQdz*100).toFixed(2):Number((nowQdz-lastQdz)/lastQdz*100).toFixed(2) ;
		//三盗两抢一诈骗同比
		tbQdz = dqzLastYearSum==0?(nowQdz*100).toFixed(2):Number((nowQdz-dqzLastYearSum)/dqzLastYearSum*100).toFixed(2) ;
	
	
		var html = ""
		html +=t3+"共接报各类违法犯罪警情"+nowSum+"件，非侵财类违法犯罪警情"+nowFqc+"件，";
		html += "环比"+t4+lastFqc+"件";
		//侵财环比
		if(hbQc>0||hbQc==0){
			html +="上升"+hbQc+"%:";
		}else{
			html +="下降"+Math.abs(hbQc)+"%:";
		}
		html +="侵财类违法犯罪警情"+nowQc+"件，环比"+t4+lastQc+"件";
		if(hbQc>0||hbQc==0){
			html +="上升"+hbQc+"%。";
		}else{
			html +="下降"+Math.abs(hbQc)+"%。";
		}
		html +="“三盗两抢一诈骗“警情"+nowQdz+"件，环比"+t4+lastQdz+"件";
		if(hbQdz>0||hbQdz==0){
			html +="上升"+hbQdz+"%，";
		}else{
			html +="下降"+Math.abs(hbQdz)+"%，";
		}
			
		html +="同比"+dqzLastYearSum+"件";

		if(tbQdz>0||tbQdz==0){
			html +="上升"+tbQdz+"%。";
		}else{
			html +="下降"+Math.abs(tbQdz)+"%。";
		}
		$("#ms").html(html)
		
	});
}

//全区各所辖区警情分类统计表
function qqJqFl(startTime,endTime){
	var url1 = "../jqfx/jzjqxx/getQqjq";
	var url = "../jqfx/xqjqfl/getTable";
	$.ajaxSettings.async = false;
	$.get(url,{startTime:startTime,endTime:endTime},function(data){
		
//		console.log(data)
		
		//警情分类统计
		var list = data.list;
		var html = "";
		html +="<thead><tr><th rowspan='2'>单位</th>";
		//本年
		if(startTime == 'bn'){
			html +=	"<th rowspan='2'>去年合计</th>";
			html +="<th rowspan='2'>本年合计</th>";
		}else if(startTime == 'bj'){
			html +=	"<th rowspan='2'>上季度合计</th>";
			html +="<th rowspan='2'>本季度合计</th>";
			html +=	"<th rowspan='2'>去年同季度常量</th>";
		}else if(startTime == 'by'){
			html +=	"<th rowspan='2'>上月合计</th>";
			html +="<th rowspan='2'>本月合计</th>";
			html +=	"<th rowspan='2'>去年同月常量</th>";
		}else if(startTime == 'bz'){
			html +=	"<th rowspan='2'>上周合计</th>";
			html +="<th rowspan='2'>本周合计</th>";
			html +=	"<th rowspan='2'>去年同周常量</th>";
		}else{
			html +="<th rowspan='2'>本期合计</th>";
			html +=	"<th rowspan='2'>去年同期常量</th>";
		}
		
		html +="<th rowspan='2'>抢劫</th><th rowspan='2'>抢夺</th><th colspan='6' class='bottom_border'>盗窃</th><th rowspan='2'>扒窃</th><th colspan='2' class='bottom_border'>诈骗</th><th rowspan='2'>非侵财</th>";
		html +="</tr><tr><th>入室盗窃</th><th>盗窃汽车</th><th>盗窃车内物品</th><th>摩托车盗窃</th><th>电动车盗窃</th><th>其他盗窃</th><th>通信诈骗</th><th>街面诈骗</th></tr></thead><tbody>";
		
		for(var i=0;i<list.length;i++){
			html += "<tr><td>"+list[i].name+"</td>";
			if(startTime == 'bn'){
				html += isColor(list[i].lastCompareLastLastInfo,list[i].lastCompareLastLastNum,list[i].name,'去年合计aa','lastYear','lastYear')+list[i].lastCompareLastLastInfo+"</label></td>";
				html += isColor(list[i].nowCompareLastInfo,list[i].nowCompareLastNum,list[i].name,'本年合计aa','year','year')+list[i].nowCompareLastInfo+"</label></td>";
			}else if(startTime == 'bj'){
				html += isColor(list[i].lastCompareLastLastInfo,list[i].lastCompareLastLastNum,list[i].name,'上季度合计aa','lastJD','lastJD')+list[i].lastCompareLastLastInfo+"</label></td>";
				html += isColor(list[i].nowCompareLastInfo,list[i].nowCompareLastNum,list[i].name,'本季度合计aa','JD','JD')+list[i].nowCompareLastInfo+"</label></td>";
				html += isColor(list[i].nowCompareLastYearInfo,list[i].nowCompareLastYearNum,list[i].name,'去年同季度常量aa','lastYearJD','lastYearJD')+list[i].nowCompareLastYearInfo+"</label></td>";
			}else if(startTime == 'by'){
				html += isColor(list[i].lastCompareLastLastInfo,list[i].lastCompareLastLastNum,list[i].name,'上月合计aa','lastMonth','lastMonth')+list[i].lastCompareLastLastInfo+"</label></td>";
				html += isColor(list[i].nowCompareLastInfo,list[i].nowCompareLastNum,list[i].name,'本月合计aa','month','month')+list[i].nowCompareLastInfo+"</label></td>";
				html += isColor(list[i].nowCompareLastYearInfo,list[i].nowCompareLastYearNum,list[i].name,'去年同月常量aa','lastYearMonth','lastYearMonth')+list[i].nowCompareLastYearInfo+"</label></td>";
			}else if(startTime == 'bz'){
				html += isColor(list[i].lastCompareLastLastInfo,list[i].lastCompareLastLastNum,list[i].name,'上周合计aa','lastWeek','lastWeek')+list[i].lastCompareLastLastInfo+"</label></td>";
				html += isColor(list[i].nowCompareLastInfo,list[i].nowCompareLastNum,list[i].name,'本周合计aa','week','week')+list[i].nowCompareLastInfo+"</label></td>";
				html += isColor(list[i].nowCompareLastYearInfo,list[i].nowCompareLastYearNum,list[i].name,'去年同周常量aa','lastYearWeek','lastYearWeek')+list[i].nowCompareLastYearInfo+"</label></td>";
			}else{
				html += isColor(list[i].nowCompareLastInfo,list[i].nowCompareLastNum,list[i].name,'本期合计aa',startTime,endTime)+list[i].nowCompareLastInfo+"</label></td>";
				html += isColor(list[i].lastCompareLastLastInfo,list[i].lastCompareLastLastNum,list[i].name,'去年同期常量aa',startTime+"oneYear",endTime+"oneYear")+list[i].lastCompareLastLastInfo+"</label></td>";
			}
			html += isColor(list[i].robCompareInfo,list[i].robCompareNum,list[i].name,'抢劫',startTime,endTime)+list[i].robCompareInfo+"</label></td>";
			html += isColor(list[i].lootCompareInfo,list[i].lootCompareNum,list[i].name,'抢夺',startTime,endTime)+list[i].lootCompareInfo+"</label></td>";
			html += isColor(list[i].burglaryCompareInfo,list[i].burglaryCompareNum,list[i].name,'入室盗窃',startTime,endTime)+list[i].burglaryCompareInfo+"</label></td>";
			html += isColor(list[i].stolenMotorVehicleCompareInfo,list[i].stolenMotorVehicleCompareNum,list[i].name,'汽车盗窃',startTime,endTime)+list[i].stolenMotorVehicleCompareInfo+"</label></td>";
			html += isColor(list[i].theftOfCarPropertyCompareInfo,list[i].theftOfCarPropertyCompareNum,list[i].name,'盗窃车内物品',startTime,endTime)+list[i].theftOfCarPropertyCompareInfo+"</label></td>";
			html += isColor(list[i].burglaryCompareInfo,list[i].burglaryCompareNum,list[i].name,'摩托车盗窃',startTime,endTime)+list[i].burglaryCompareInfo+"</label></td>";
			html += isColor(list[i].stolenMotorVehicleCompareInfo,list[i].stolenMotorVehicleCompareNum,list[i].name,'电动车盗窃',startTime,endTime)+list[i].stolenMotorVehicleCompareInfo+"</label></td>";
			html += isColor(list[i].theftOfCarPropertyCompareInfo,list[i].theftOfCarPropertyCompareNum,list[i].name,'其他盗窃',startTime,endTime)+list[i].theftOfCarPropertyCompareInfo+"</label></td>";
			html += isColor(list[i].burglaryCompareInfo,list[i].burglaryCompareNum,list[i].name,'扒窃',startTime,endTime)+list[i].burglaryCompareInfo+"</label></td>";
			html += isColor(list[i].stolenMotorVehicleCompareInfo,list[i].stolenMotorVehicleCompareNum,list[i].name,'通信诈骗',startTime,endTime)+list[i].stolenMotorVehicleCompareInfo+"</label></td>";
			html += isColor(list[i].theftOfCarPropertyCompareInfo,list[i].theftOfCarPropertyCompareNum,list[i].name,'街面诈骗',startTime,endTime)+list[i].theftOfCarPropertyCompareInfo+"</label></td>";
			html += isColor(list[i].fraudCompareInfo,list[i].fraudCompareNum,list[i].name,'非侵财',startTime,endTime)+list[i].fraudCompareInfo+"</label></td></tr>";
		}
		html +=" </tbody>";
		$("#ff").html(html);
	});
}

function isColor(s,t,name,type,start,end){
	var str = "";
	if(s.indexOf("↑")!=-1){
		str = "<td onclick='jun_test(&quot;"+name+"&quot;,&quot;"+type+"&quot;,&quot;"+start+"&quot;,&quot;"+end+"&quot;)' title='"+t+"'><label class='red_text'>";
	}else if(s.indexOf("↓")!=-1){
		str = "<td onclick='jun_test(&quot;"+name+"&quot;,&quot;"+type+"&quot;,&quot;"+start+"&quot;,&quot;"+end+"&quot;)' title='"+t+"'><label class='blue_text'>";
	}else {
		str = "<td><label>";
	}
	return str;
}
function jun_test(name,type,start,end){
	openframe(name,type,start,end);
}

function openframe(name,type,start,end){
	layer.open({
		id:1,
		type: 2,
		title:name+'->'+type.replace("aa","")+'-> 详细表',
		maxmin: true,
		skin:'layer-ext-moon',
		area: ['1100px','600px'],
		offset: ['10px', '10px'],
		shade: false,
		maxmin: true,
		shadeClose: true,
		closeBtn: 1, 
		content: 'wffzxxList.html?start='+start+'&end='+end+'&name='+name+'&type='+type,
		btn:['关闭'],
		btn1:function (index,layero) {
			layer.close(index);
		}
	})
}

Date.prototype.Format = function (fmt) { //author: meizz
	  var o = {
	    "M+": this.getMonth() + 1, //月份
	    "d+": this.getDate(), //日
	    "h+": this.getHours(), //小时
	    "m+": this.getMinutes(), //分
	    "s+": this.getSeconds(), //秒
	    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
	    "S": this.getMilliseconds() //毫秒
	  };
	  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	  for (var k in o)
	  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	  return fmt;
	}

//全区各辖区违法警情累计同比统计表
function qqJqTb(startTime,endTime){
	var url = "../jqfx/jzjqxx/getQqjqTb";
	$.ajaxSettings.async = false;
	$.get(url,{startTime:startTime,endTime:endTime},function(data){
		//console.log(data)
		var date=new Date();
		//警情分类统计
		var list = data.list;
	
		var html = "";
		html +="<thead><tr> <th width='40%'>单位</th>";
		html +="<th width='20%'>截止目前累计（件）"+year+"年1月31日至"+date.Format("yyyy年M月d日")+"</th>";
		html +="<th width='20%'>去年同期累计（件）"+(year-1)+"年1月31日至"+(year-1)+"年"+date.Format("M月d日")+"</th>";
		html +=" <th width='20%'>同比</th></tr></thead> <tbody>";
		for(var i = 0,len = list.length;i < len;i++){
			
			html += "<tr><td>"+list[i].name+"</td> ";
			html += "<td>"+list[i].cuntnow+"</td> ";
			html += "<td>"+list[i].cuntlast+"</td> ";
			html += "<td>"+list[i].tb+"</td></tr>";
			
		}
		html +=" </tbody>";
		//console.log(html)
		$("#qqwffwtb").html(html);
	});
	
	
}

//全区各辖区违法警情累计同比统计表
function getDqzTbHb(startTime,endTime){
	var url = "../jqfx/jzjqxx/getDqzTbHb";
	$.ajaxSettings.async = false;
	$.get(url,{startTime:startTime,endTime:endTime},function(data){
		//console.log(data)
		var date=new Date();
		//警情分类统计
		var list = data.list;
		//console.log(list.length)
		var html = "";
		html +="<thead><tr> <th width='20%'>类型</th>";
		html +="<th width='10%'>本周（件）</th>";
		html +="<th width='10%'>上周（件））</th>";
		html +="<th width='10%'>环比</th>";
		html +="<th width='20%'>截止目前累计（件）"+year+"年1月31日至"+date.Format("yyyy年M月d日")+"</th>";
		html +="<th width='20%'>去年同期累计（件）"+(year-1)+"年1月31日至"+(year-1)+"年"+date.Format("M月d日")+"</th>";
		html +=" <th width='10%'>同比</th></tr></thead> <tbody>";
		for(var i = 0,len = list.length;i < len;i++){
			//console.log(list[i].name=="undefined")
			if(list[i].name=="undefined"){
				
			}else{
				html += "<tr><td>"+list[i].name+"</td> ";
				html += "<td>"+list[i].cuntnowweek+"</td> ";
				html += "<td>"+list[i].cuntlastweek+"</td> ";
				html += "<td>"+list[i].hb+"</td> ";
				html += "<td>"+list[i].cuntnowyear+"</td> ";
				html += "<td>"+list[i].cuntlastyear+"</td> ";
				html += "<td>"+list[i].tb+"</td></tr>";
			}
			
			
		}
		html +=" </tbody>";
		$("#szlqyzp").html(html);
	});
}
var wffzjq1 = {
		  tooltip: {
		        trigger: 'axis'
		    },

			legend: {
				data:[ {name: ''+(year-1)+'',  
		                 textStyle:{color:'rgb(109,109,109)'}  
		               },  
		              {name:''+year+'', 
		                textStyle:{color:'rgb(109,109,109)'}
					  }
		          ],
				top:'top'
				//right: 'right',
		    },
			dataZoom: [
		        {
		            show: true,
		            realtime: true,
					borderColor:"#595b63",
					textStyle:{color:'#bbbbbb'},  
		            start:0,
		            end: 20,
					bottom:0
		        },
		        {
		            type: 'inside',
					borderColor:"#595b63",
		            realtime: true,
		            start:0,
		            end: 20
		        }
		    ],
			grid: {
		        left: '2%',
		        right: '2%',
		        bottom: '11%',
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
				splitArea:{show : false},//保留网格区域
		        data : [].map(function (str) {
		                return str.replace(' ', '\n')
		            })
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
		            name:''+(year-1)+'', 
		            data: [],
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
					/* markArea: {
		                silent: true,
		                data: [[{
		                    xAxis: '2009/6/12\n7:00'
		                }, {
		                    xAxis: '2009/6/12\n12:00'
		                }]]
		            }*/
		        },
		        {
		            name:''+year+'',
		            data: [],
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
					/*markArea: {
		                silent: true,
		                data: [[{
		                    xAxis: '2009/6/12\n7:00'
		                }, {
		                    xAxis: '2009/6/12\n12:00'
		                }]]
		            }	*/
		        }
		    ]

};
var wffzjq2 = {
		tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        type: 'scroll',
	        orient: 'vertical',
	        right: 10,
	        top: 20,
	        bottom: 20,
	        data: ['火灾事故救援','违法警情','交通事故','求助','骚扰误报','噪音扰民']
	    },
	    series : [
	        {
	        	name:"",
	            type: 'pie',
	            radius : '55%',
	            center: ['40%', '50%'],
	            data: [
	                {
	                	name: "火灾事故救援",
	                	value: "1"
	                },
	                {
	                	name: "违法警情",
	                	value: "10"
	                },
	                {
	                	name: "交通事故",
	                	value: "33"
	                },
	                {
	                	name: "求助",
	                	value: "47"
	                },
	                {
	                	name: "骚扰误报",
	                	value: "3"
	                },
	                {
	                	name: "噪音扰民",
	                	value: "7"
	                },
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	
