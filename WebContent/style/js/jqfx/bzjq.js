var now = new Date();//当天日期
var nowTime = now.getTime();//今天本周的第几天
var day = now.getDay();
var oneDayTime = 24*60*60*1000;

$(function(){ 
	//alert(getWeekStartDate()+"-"+getWeekEndDate())
	$("#dateTime1hidden").val(getWeekStartDate()+" - "+getWeekEndDate());
	$("#dateTime1").val(getWeekStartDate()+" - "+getWeekEndDate());
	bzjqLoad("","","");
	bzjqLoadHours("","","");
});

function loadBzjq(){
	bzjqLoad("","","");
	bzjqLoadHours("","","");
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



var dbtData = [];
var dataParams = [];
function bzjqLoad(start,end,ksms){
	//$.ajaxSettings.async = false;
	$.get("../jqfx/bzjq/countByWeek",{start:start,end:end,ksms:ksms},function(data){
		dbtData = data.data;
		var wffzcunt = data.wffzcunt;
		var cunt = data.cunt;
		$("#weekTable").find("tr").eq(1).find("td").eq(1).html(wffzcunt.bq);
		$("#weekTable").find("tr").eq(1).find("td").eq(2).html(wffzcunt.sq);
		$("#weekTable").find("tr").eq(1).find("td").eq(3).html(wffzcunt.hb);
		$("#weekTable").find("tr").eq(1).find("td").eq(4).html(wffzcunt.tq);
		$("#weekTable").find("tr").eq(1).find("td").eq(5).html(wffzcunt.tb);
		$("#weekTable").find("tr").eq(2).find("td").eq(1).html(cunt.bq);
		$("#weekTable").find("tr").eq(2).find("td").eq(2).html(cunt.sq);
		$("#weekTable").find("tr").eq(2).find("td").eq(3).html(cunt.hb);
		$("#weekTable").find("tr").eq(2).find("td").eq(4).html(cunt.tq);
		$("#weekTable").find("tr").eq(2).find("td").eq(5).html(cunt.tb);
		
		/*$("#weekTable").find("tr").eq(1).find("td").eq(0).html(parseInt(dbtData.zjq.bq)+parseInt(wffzcunt))
		$("#weekTable").find("tr").eq(1).find("td").eq(1).html(dbtData.zjq.bq)
		$("#weekTable").find("tr").eq(1).find("td").eq(2).html(wffzcunt)*/
		/*$("#weekTable").find("tr").eq(1).find("td").eq(4).html(dbtData.zjq.tq)
		$("#weekTable").find("tr").eq(1).find("td").eq(5).html(dbtData.zjq.tb)	*/
		
		
		var htmlcfbj="";
		
		
    /*  
		htmlcfbj +="<p style='height: 5px'></p>";
		htmlcfbj +="<p style='text-align: left;'>&nbsp;&nbsp;重复报警&nbsp;&nbsp;&nbsp;"+dbtData.cfbj.bq+"</p>";
		htmlcfbj +="<p style='text-align: left;'>&nbsp;&nbsp;同比："+dbtData.cfbj.tb+"</p>";
		htmlcfbj +="<p style='text-align: left;'>&nbsp;&nbsp;环比："+dbtData.cfbj.hb+"</p>";
		
		alert(parseInt(dbtData.cfbj.bq))
		alert(parseInt(dbtData.cfbj.sq))
		alert((parseInt(dbtData.cfbj.bq)-parseInt(dbtData.cfbj.sq))/parseInt(dbtData.cfbj.sq)*100)
		//alert((dbtData.cfbj.tb-dbtData.cfbj.sq)/dbtData.cfbj.sq*100)
		//alert((parseInt(dbtData.cfbj.bq)-parseInt(dbtData.cfbj.sq))/parseInt(dbtData.cfbj.sq*100)>20&&(parseInt(dbtData.cfbj.bq)-parseInt(dbtData.cfbj.sq))/parseInt(dbtData.cfbj.sq)*100<=40)
		//alert((parseInt(dbtData.cfbj.bq)-parseInt(dbtData.cfbj.sq))/parseInt(dbtData.cfbj.sq)*100>40)
*/		if((parseInt(dbtData.cfbj.bq)-parseInt(dbtData.cfbj.sq))/parseInt(dbtData.cfbj.sq*100)>20&&(parseInt(dbtData.cfbj.bq)-parseInt(dbtData.cfbj.sq))/parseInt(dbtData.cfbj.sq)*100<=40){
			htmlcfbj +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.cfbj.bq)-parseInt(dbtData.cfbj.sq))/parseInt(dbtData.cfbj.sq)*100>40){
			htmlcfbj +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmlcfbj +="<div class='layuiadmin-card-text'>";
		}
		
		htmlcfbj +="<div class='layui-text-top'>";
		htmlcfbj +="<i class='layui-icon layui-icon-read'></i>&nbsp;重复报警</div>";
		htmlcfbj +="<div class='layui-text-center largeText'>"+dbtData.cfbj.bq+"</div>";
		htmlcfbj +="<div class='layui-text-bottom'>";
		htmlcfbj +="<ul class=''><li>";
		htmlcfbj +="<div>同比</div><div>"+dbtData.cfbj.tb+"</div></li>";
		htmlcfbj +="<li><div>环比</div><div>"+dbtData.cfbj.hb+"</div></li>";
		htmlcfbj +="</ul></div></div>";
		$("#cfbj").html(htmlcfbj);
		
      	var htmlts="";
		if((parseInt(dbtData.ts.bq)-parseInt(dbtData.ts.sq))/parseInt(dbtData.ts.sq*100)>20&&(parseInt(dbtData.ts.bq)-parseInt(dbtData.ts.sq))/parseInt(dbtData.ts.sq)*100<=40){
			htmlts +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.ts.bq)-parseInt(dbtData.ts.sq))/parseInt(dbtData.ts.sq)*100>40){
			htmlts +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmlts +="<div class='layuiadmin-card-text'>";
		}
		htmlts +="<div class='layui-text-top'>";
		htmlts +="<i class='layui-icon layui-icon-read'></i>&nbsp;投诉</div>";
		htmlts +="<div class='layui-text-center largeText'>"+dbtData.ts.bq+"</div>";
		htmlts +="<div class='layui-text-bottom'>";
		htmlts +="<ul class=''><li>";
		htmlts +="<div>同比</div><div>"+dbtData.ts.tb+"</div></li>";
		htmlts +="<li><div>环比</div><div>"+dbtData.ts.hb+"</div></li>";
		htmlts +="</ul></div></div>";
		$("#ts").html(htmlts);
		
		
      	var htmlzyrm="";		
		if((parseInt(dbtData.zyrm.bq)-parseInt(dbtData.zyrm.sq))/parseInt(dbtData.zyrm.sq*100)>20&&(parseInt(dbtData.zyrm.bq)-parseInt(dbtData.zyrm.sq))/parseInt(dbtData.zyrm.sq*100)<=40){
			htmlzyrm +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.zyrm.bq)-parseInt(dbtData.zyrm.sq))/parseInt(dbtData.zyrm.sq*100)>40){
			htmlzyrm +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmlzyrm +="<div class='layuiadmin-card-text'>";
		}
		htmlzyrm +="<div class='layui-text-top'>";
		htmlzyrm +="<i class='layui-icon layui-icon-read'></i>&nbsp;噪音扰民</div>";
		htmlzyrm +="<div class='layui-text-center largeText'>"+dbtData.zyrm.bq+"</div>";
		htmlzyrm +="<div class='layui-text-bottom'>";
		htmlzyrm +="<ul class=''><li>";
		htmlzyrm +="<div>同比</div><div>"+dbtData.zyrm.tb+"</div></li>";
		htmlzyrm +="<li><div>环比</div><div>"+dbtData.zyrm.hb+"</div></li>";
		htmlzyrm +="</ul></div></div>";
		$("#zyrm").html(htmlzyrm);
		
      	var htmlsrwb="";
		if((parseInt(dbtData.srwb.bq)-parseInt(dbtData.srwb.sq))/parseInt(dbtData.srwb.sq)*100>20&&(parseInt(dbtData.srwb.bq)-parseInt(dbtData.srwb.sq))/parseInt(dbtData.srwb.sq)*100<=40){
			htmlsrwb +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.srwb.bq)-parseInt(dbtData.srwb.sq))/parseInt(dbtData.srwb.sq)*100>40){
			htmlsrwb +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmlsrwb +="<div class='layuiadmin-card-text'>";
		}		
		htmlsrwb +="<div class='layui-text-top'>";
		htmlsrwb +="<i class='layui-icon layui-icon-read'></i>&nbsp;骚扰误报</div>";
		htmlsrwb +="<div class='layui-text-center largeText'>"+dbtData.srwb.bq+"</div>";
		htmlsrwb +="<div class='layui-text-bottom'>";
		htmlsrwb +="<ul class=''><li>";
		htmlsrwb +="<div>同比</div><div>"+dbtData.srwb.tb+"</div></li>";
		htmlsrwb +="<li><div>环比</div><div>"+dbtData.srwb.hb+"</div></li>";
		htmlsrwb +="</ul></div></div>";
		$("#srwb").html(htmlsrwb);
		
      	var htmlqz="";
	
		if((parseInt(dbtData.qz.bq)-parseInt(dbtData.qz.sq))/parseInt(dbtData.qz.sq)*100>20&&(parseInt(dbtData.qz.bq)-parseInt(dbtData.qz.sq))/parseInt(dbtData.qz.sq)*100<=40){
			htmlqz +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.qz.bq)-parseInt(dbtData.qz.sq))/parseInt(dbtData.qz.sq)*100>40){
			htmlqz +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmlqz +="<div class='layuiadmin-card-text'>";
		}
		htmlqz +="<div class='layui-text-top'>";
		htmlqz +="<i class='layui-icon layui-icon-read'></i>&nbsp;求助</div>";
		htmlqz +="<div class='layui-text-center largeText'>"+dbtData.qz.bq+"</div>";
		htmlqz +="<div class='layui-text-bottom'>";
		htmlqz +="<ul class=''><li>";
		htmlqz +="<div>同比</div><div>"+dbtData.qz.tb+"</div></li>";
		htmlqz +="<li><div>环比</div><div>"+dbtData.qz.hb+"</div></li>";
		htmlqz +="</ul></div></div>";
		$("#qz").html(htmlqz);
		
      	var htmlzx="";
		if((parseInt(dbtData.zx.bq)-parseInt(dbtData.zx.sq))/parseInt(dbtData.zx.sq)*100>20&&(parseInt(dbtData.zx.bq)-parseInt(dbtData.zx.sq))/parseInt(dbtData.zx.sq)*100<=40){
			htmlzx +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.zx.bq)-parseInt(dbtData.zx.sq))/parseInt(dbtData.zx.sq)*100>40){
			htmlzx +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmlzx +="<div class='layuiadmin-card-text'>";
		}
		htmlzx +="<div class='layui-text-top'>";
		htmlzx +="<i class='layui-icon layui-icon-read'></i>&nbsp;咨询</div>";
		htmlzx +="<div class='layui-text-center largeText'>"+dbtData.zx.bq+"</div>";
		htmlzx +="<div class='layui-text-bottom'>";
		htmlzx +="<ul class=''><li>";
		htmlzx +="<div>同比</div><div>"+dbtData.zx.tb+"</div></li>";
		htmlzx +="<li><div>环比</div><div>"+dbtData.zx.hb+"</div></li>";
		htmlzx +="</ul></div></div>";
		$("#zx").html(htmlzx);
		
      	var htmlhz="";
		if((parseInt(dbtData.hz.bq)-parseInt(dbtData.hz.sq))/parseInt(dbtData.hz.sq)*100>20&&(parseInt(dbtData.hz.bq)-parseInt(dbtData.hz.sq))/parseInt(dbtData.hz.sq)*100<=40){
			htmlhz +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.hz.bq)-parseInt(dbtData.hz.sq))/parseInt(dbtData.hz.sq)*100>40){
			htmlhz +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmlhz +="<div class='layuiadmin-card-text'>";
		}
		htmlhz +="<div class='layui-text-top'>";
		htmlhz +="<i class='layui-icon layui-icon-read'></i>&nbsp;火灾</div>";
		htmlhz +="<div class='layui-text-center largeText'>"+dbtData.hz.bq+"</div>";
		htmlhz +="<div class='layui-text-bottom'>";
		htmlhz +="<ul class=''><li>";
		htmlhz +="<div>同比</div><div>"+dbtData.hz.tb+"</div></li>";
		htmlhz +="<li><div>环比</div><div>"+dbtData.hz.hb+"</div></li>";
		htmlhz +="</ul></div></div>";
		$("#hz").html(htmlhz);
		
		
      	var htmljtsg="";
		if((parseInt(dbtData.jtsg.bq)-parseInt(dbtData.jtsg.sq))/parseInt(dbtData.jtsg.sq)*100>20&&(parseInt(dbtData.jtsg.bq)-parseInt(dbtData.jtsg.sq))/parseInt(dbtData.jtsg.sq)*100<=40){
			htmljtsg +="<div class='layuiadmin-card-text cardBlue'>";
		}else if((parseInt(dbtData.jtsg.bq)-parseInt(dbtData.jtsg.sq))/parseInt(dbtData.jtsg.sq)*100>40){
			htmljtsg +="<div class='layuiadmin-card-text cardRed'>";
		}else{
			htmljtsg +="<div class='layuiadmin-card-text'>";
		}
		htmljtsg +="<div class='layui-text-top'>";
		htmljtsg +="<i class='layui-icon layui-icon-read'></i>&nbsp;交通事故</div>";
		htmljtsg +="<div class='layui-text-center largeText'>"+dbtData.jtsg.bq+"</div>";
		htmljtsg +="<div class='layui-text-bottom'>";
		htmljtsg +="<ul class=''><li>";
		htmljtsg +="<div>同比</div><div>"+dbtData.jtsg.tb+"</div></li>";
		htmljtsg +="<li><div>环比</div><div>"+dbtData.jtsg.hb+"</div></li>";
		htmljtsg +="</ul></div></div>";
		$("#jtsg").html(htmljtsg);
		/*var dbt = [
			dbtData.jtsg.bq,
			dbtData.ts.bq,
			dbtData.zx.bq,
			dbtData.qz.bq,
			dbtData.srwb.bq,
			dbtData.zyrm.bq,
			dbtData.hz.bq,
			dbtData.cfbj.bq,
		]*/
		/*chart1.setOption({series:[{data:dbt}]});
		
		chart1.off('click');
		chart1.on('click', function(params){
			openFrame(params);
		});*/
		
	/*	var zbt =  
			[
				{ "name":"重复报警" , "value":dbtData.cfbj.bq}, 
				{ "name":"投诉" , "value":dbtData.ts.bq}, 
				{ "name":"噪音扰民" , "value":dbtData.zyrm.bq}, 
				{ "name":"骚扰误报" , "value":dbtData.srwb.bq}, 
				{ "name":"求助" , "value":dbtData.qz.bq}, 
				{ "name":"咨询" , "value":dbtData.zx.bq}, 
				{ "name":"火灾" , "value":dbtData.hz.bq}, 
				{ "name":"交通事故" , "value":dbtData.jtsg.bq}
			];
		chart2.setOption({series : [{data:zbt}]});*/
	})
}

function bzjqLoadHours(start,end,ksms){
	//$.ajaxSettings.async = false;
	$.get("../jqfx/bzjq/countByWeek",{start:start,end:end,ksms:ksms},function(data){
		dbtData = data.data;
		var bqData=[];
//		var jtsg = [];
//		var hz = [];
//		var zx = [];
//		var qz = [];
//		var srwb = [];
//		var zyrm = [];
//		var ts = [];
//		var cfbj = []; 
//		 (dbtData.jtsg.bq.length)
	
//		for (var i = 0; i < dbtData.jtsg.bq.length; i++) {
//			jtsg.push(value:dbtData.jtsg.bq[i].gjz);
//			
//		}
//		for (var i = 0; i < dbtData.hz.bq.length; i++) {
//			hz.push(value:dbtData.hz.bq[i].gjz);
//		}
//		for (var i = 0; i < dbtData.zx.bq.length; i++) {
//			zx.push(value:dbtData.zx.bq[i].gjz);
//		}
//		for (var i = 0; i < dbtData.qz.bq.length; i++) {
//			qz.push(value:dbtData.qz.bq[i].gjz);
//		}
//		for (var i = 0; i < dbtData.srwb.bq.length; i++) {
//			srwb.push(value:dbtData.srwb.bq[i].gjz);
//		}
//		for (var i = 0; i < dbtData.zyrm.bq.length; i++) {
//			zyrm.push(value:dbtData.zyrm.bq[i].gjz);
//		}
//		for (var i = 0; i < dbtData.ts.bq.length; i++) {
//			ts.push(value:dbtData.ts.bq[i].gjz);
//		}
//		for (var i = 0; i < dbtData.cfbj.bq.length; i++) {
//			cfbj.push(value:dbtData.cfbj.bq[i].gjz);
//		}
		//{data:jtsg},
//		chart2.setOption({series : [{data:jtsg},{data:hz},{data:zx},{data:qz},
//			{data:srwb},{data:zyrm},{data:ts},{data:cfbj}]});
		bqData.push({
			name:'交通事故',
			value:dbtData.jtsg.bq});
		bqData.push({
			name:'火灾',
			value:dbtData.hz.bq});
		bqData.push({
			name:'咨询',
			value:dbtData.zx.bq});
		bqData.push({
			name:'求助',
			value:dbtData.qz.bq});
		bqData.push({
			name:'骚扰误报',
			value:dbtData.srwb.bq});
		bqData.push({
			name:'噪音扰民',
			value:dbtData.zyrm.bq});
		bqData.push({
			name:'投诉',
			value:dbtData.ts.bq});
		bqData.push({
			name:'重复报警',
			value:dbtData.cfbj.bq});
		chart2.setOption({series : [{data:bqData}]});
	})
}
$("#right1 input:radio[name=choose]").change(function(){
	var ksms = $(this).val()
	var name = "";
	bzjqLoad("","",ksms);
	bzjqLoadHours("","",ksms)
	if(ksms=="bz"){
		name = "本周"
	}
	if(ksms=="by"){
		name = "本月"
	}
	if(ksms=="bj"){
		name = "本季度"
	}
	if(ksms=="bn"){
		name = "本年度"
	}
    $("#bzjqzbt").text(name+"全区各类警情占比图");
    $("#bzjqdbt").text(name+"全区各类警情占比图");
});

var bzjq1 = {
	tooltip: {
		trigger: 'item',
		formatter:function(params){
			var hb = "0%";
			var tb = "0%";
			var bq = 0;
			if(dbtData!=""){
				if(params.name=="重复报警"){
					hb = dbtData.cfbj.hb ;
					tb = dbtData.cfbj.tb ;
					bq = dbtData.cfbj.bq;
				}
				if(params.name=="投诉"){
					hb = dbtData.ts.hb;
					tb = dbtData.ts.tb ;
					bq = dbtData.ts.bq;
				}
				if(params.name=="噪音扰民"){
					hb = dbtData.zyrm.hb ;
					tb = dbtData.zyrm.tb ;
					bq = dbtData.zyrm.bq;
				}
				if(params.name=="骚扰误报"){
					hb = dbtData.srwb.hb ;
					tb = dbtData.srwb.tb;
					bq = dbtData.srwb.bq;
				}
				if(params.name=="求助"){
					hb = dbtData.qz.hb ;
					tb = dbtData.qz.tb;
					bq = dbtData.qz.bq;
				}
				if(params.name=="咨询"){
					hb = dbtData.zx.hb ;
					tb = dbtData.zx.tb ;
					bq = dbtData.zx.bq;
				}
				if(params.name=="火灾"){
					hb = dbtData.hz.hb ;
					tb = dbtData.hz.tb ;
					bq = dbtData.hz.bq;
				}
				if(params.name=="交通事故"){
					hb = dbtData.jtsg.hb;
					tb = dbtData.jtsg.tb ;
					bq = dbtData.jtsg.bq;
				}
			}
			return bq+"条\n环比："+hb+"\n同比："+tb;
		}
//		formatter: "{b} : {c}条"
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
				//triggerEvent:true,
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
				data : ['重复报警','投诉','噪音扰民','骚扰误报','求助','咨询','火灾','交通事故']
			},
			
		],
		series : [
			{
				type:'bar',
				stack: '总量',
				label: {
					normal: {
						//formatter: '{c}条' ,
						formatter:function(params){
							var hb = "0%";
							var tb = "0%";
							var bq = 0;
							if(dbtData!=""){
								if(params.name=="重复报警"){
									hb = dbtData.cfbj.hb ;
									tb = dbtData.cfbj.tb ;
									bq = dbtData.cfbj.bq;
								}
								if(params.name=="投诉"){
									hb = dbtData.ts.hb ;
									tb = dbtData.ts.tb ;
									bq = dbtData.ts.bq;
								}
								if(params.name=="噪音扰民"){
									hb = dbtData.zyrm.hb;
									tb = dbtData.zyrm.tb;
									bq = dbtData.zyrm.bq;
								}
								if(params.name=="骚扰误报"){
									hb = dbtData.srwb.hb ;
									tb = dbtData.srwb.tb ;
									bq = dbtData.srwb.bq;
								}
								if(params.name=="求助"){
									hb = dbtData.qz.hb + "%";
									tb = dbtData.qz.tb + "%";
									bq = dbtData.qz.bq;
								}
								if(params.name=="咨询"){
									hb = dbtData.zx.hb ;
									tb = dbtData.zx.tb;
									bq = dbtData.zx.bq;
								}
								if(params.name=="火灾"){
									hb = dbtData.hz.hb;
									tb = dbtData.hz.tb ;
									bq = dbtData.hz.bq;
								}
								if(params.name=="交通事故"){
									hb = dbtData.jtsg.hb ;
									tb = dbtData.jtsg.tb ;
									bq = dbtData.jtsg.bq;
								}
							}
							return bq+"条\n环比："+hb+"\n同比："+tb;
						},
					}
				},
				itemStyle : { normal: {color:'rgb(66,143,213)',label : {show: true, position: 'right'}}},
				data:[0,0,0,0,0,0,0,0]
			}
		]
	};
		
var bzjq2 = {
	tooltip : {
	    trigger: 'item',
	    formatter: "{a} <br/>{b} : {c} ({d}%)"
	},
	series : [
	    {
	        name: '警情类型',
	        type: 'pie',
	        radius : '60%',
	        center: ['50%', '50%'],
	        data:[],
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

function openFrame(params){
	dataParams = {
		"lx":encodeURI(params.name),
		"ksms":$("#right1 input:radio[name=choose]:checked").val(),
		"zxsj":$("#dateTime1hidden").val()
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

function goToBzjqView(type){
	dataParams = {
			"lx":encodeURI(type),
			"ksms":$("#right1 input:radio[name=choose]:checked").val(),
			"zxsj":$("#dateTime1hidden").val()
		};
		layer.open({
			id:1,
			type: 2,
			title:type + '警情列表',
			maxmin: true,
			skin:'layer-ext-moon',
			area: ['1150px','700px'],
			offset: ['10px', '10px'],
			shade: false,
			maxmin: true,
			shadeClose: true,
			closeBtn: 1, 
			content: 'bzjqListView.html',
			btn:['关闭'],
			btn1:function (index,layero) {
				layer.close(index);
			}
		})
	
}
