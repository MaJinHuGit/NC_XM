$(function(){ 
	ssyjLoad("","","","");
	loadHotMark("","","","");
});

$("#pcs").change(function(){
	var orgCode=$(this).val();
	var dateSelect=$("#ssyjSelect").val();
	if(dateSelect==0){
		if(orgCode==0){
			ssyjLoad("","","","");
			loadHotMark("","","","");
		}else{
			ssyjLoad("","",orgCode,"");
			loadHotMark("","",orgCode,"");
		}
	}else{
		if(orgCode==0){
			ssyjLoad("","","",dateSelect);
			loadHotMark("","","",dateSelect);
		}else{
			ssyjLoad("","",orgCode,dateSelect);
			loadHotMark("","",orgCode,dateSelect);
		}
	}
	reloadMap(orgCode);
});

function ssyjLoad(start,end,orgcode,weeknum){
	$.get("../jqfx/ssyj/getXqCount",{start:start,end:end,orgcode:orgcode,weeknum:weeknum},function(data){
		var data = data.data;
		//$("#sstable").find("tr").eq(2).find("td").eq(0).html(data.wfjq.orgname) //警情区域
		var num = 3;
//		if(!$("#backall").is(":hidden")){
//			num += 1;
//		}

		var startTime=data.startTime.split(" ")[0];
		var startDateTime=startTime.split("-");
		startTime=startDateTime[0]+"/"+startDateTime[1]+"/"+startDateTime[2];
		var endTime=data.endTime.split(" ")[0];
		var endDateTime=endTime.split("-");
		entTime=endDateTime[0]+"/"+endDateTime[1]+"/"+endDateTime[2];
		$("#weeks").html(startTime+"~"+entTime);
		var orgname=$("#pcs").find("option:selected").html();
		
		
		$("#sstable").find("tr").eq(num).find("td").eq(0).html(orgname);
		
		//违法警情
		$("#sstable").find("tr").eq(num).find("td").eq(2).html(data.xqajlb.wfjq.zzs);//本周
		$("#sstable").find("tr").eq(num).find("td").eq(3).html(data.xqajlb.wfjq.fdbv); //浮动比率
		$("#sstable").find("tr").eq(num).find("td").eq(4).html(data.xqajlb.wfjq.kfdbv); //可浮动比率
		$("#sstable").find("tr").eq(num).find("td").eq(5).html(getLbColor(data.xqajlb.wfjq.fdbv,data.xqajlb.wfjq.kfdbv)); //颜色
		
		//侵财
		$("#sstable").find("tr").eq(num+1).find("td").eq(1).html(data.xqajlb.qc.zzs);//本周
		$("#sstable").find("tr").eq(num+1).find("td").eq(2).html(data.xqajlb.qc.fdbv); //浮动比率
		$("#sstable").find("tr").eq(num+1).find("td").eq(3).html(data.xqajlb.qc.kfdbv); //可浮动比率
		$("#sstable").find("tr").eq(num+1).find("td").eq(4).html(getLbColor(data.xqajlb.qc.fdbv,data.xqajlb.qc.kfdbv)); //预警级别
		
		//两抢
		$("#sstable").find("tr").eq(num+2).find("td").eq(1).html(data.xqajlb.lq.zzs);//本周
		$("#sstable").find("tr").eq(num+2).find("td").eq(2).html(data.xqajlb.lq.fdbv); //浮动比率
		$("#sstable").find("tr").eq(num+2).find("td").eq(3).html(data.xqajlb.lq.kfdbv); //可浮动比率
		$("#sstable").find("tr").eq(num+2).find("td").eq(4).html(getLbColor(data.xqajlb.lq.fdbv,data.xqajlb.lq.kfdbv)); //预警级别
		
		//入室盗窃
		$("#sstable").find("tr").eq(num+3).find("td").eq(1).html(data.xqajlb.rsdq.zzs);//本周
		$("#sstable").find("tr").eq(num+3).find("td").eq(2).html(data.xqajlb.rsdq.fdbv); //浮动比率
		$("#sstable").find("tr").eq(num+3).find("td").eq(3).html(data.xqajlb.rsdq.kfdbv); //可浮动比率
		$("#sstable").find("tr").eq(num+3).find("td").eq(4).html(getLbColor(data.xqajlb.rsdq.fdbv,data.xqajlb.rsdq.kfdbv)); //预警级别
		
		//涉车盗窃
		$("#sstable").find("tr").eq(num+4).find("td").eq(1).html(data.xqajlb.scdq.zzs)//本周
		$("#sstable").find("tr").eq(num+4).find("td").eq(2).html(data.xqajlb.scdq.fdbv) //浮动比率
		$("#sstable").find("tr").eq(num+4).find("td").eq(3).html(data.xqajlb.scdq.kfdbv) //可浮动比率
		$("#sstable").find("tr").eq(num+4).find("td").eq(4).html(getLbColor(data.xqajlb.scdq.fdbv,data.xqajlb.scdq.kfdbv)) //预警级别
		
		//诈骗
		$("#sstable").find("tr").eq(num+5).find("td").eq(1).html(data.xqajlb.zp.zzs)//本周
		$("#sstable").find("tr").eq(num+5).find("td").eq(2).html(data.xqajlb.zp.fdbv) //浮动比率
		$("#sstable").find("tr").eq(num+5).find("td").eq(3).html(data.xqajlb.zp.kfdbv) //可浮动比率
		$("#sstable").find("tr").eq(num+5).find("td").eq(4).html(getLbColor(data.xqajlb.zp.fdbv,data.xqajlb.zp.kfdbv)) //预警级别
		
		//摩 、电
		$("#sstable").find("tr").eq(num+6).find("td").eq(1).html(data.xqajlb.md.zzs)//本周
		$("#sstable").find("tr").eq(num+6).find("td").eq(2).html(data.xqajlb.md.fdbv) //浮动比率
		$("#sstable").find("tr").eq(num+6).find("td").eq(3).html(data.xqajlb.md.kfdbv) //可浮动比率
		$("#sstable").find("tr").eq(num+6).find("td").eq(4).html(getLbColor(data.xqajlb.md.fdbv,data.xqajlb.md.kfdbv)) //预警级别
		
		
	})
}


function loadHotMark(start,end,orgcode,weeknum){
	var data= getQueryParams(weeknum,orgcode)
	$.ajax({
		url:'../jqfx/ssyj/getHotMark',
		type:'get',
		dataType:'json',
		data:data,
		error:function(){
			layer.msg("系统错误");
		},
		success:function(data){
			showHotMark(data.data);
		}
	})
}
//加载搜索日期
$.get("../jqfx/ssyj/getDate",function(data){
	var data = data.data
	$("#ssyjSelect").append("<option value='0'>本周</option>")
	for(var key in data){
		$("#ssyjSelect").append("<option value='"+key+"'>"+data[key]+"</option>");
	}  
});

function search(){
	var weeknum = $("#ssyjSelect option:selected").val();
	var orgCode=$("#pcs").val();
	if(orgCode != 0){
		ssyjLoad("","",orgCode,weeknum)
	}else{
		ssyjLoad("","","",weeknum)
	}
	
	loadHotMark("","",orgCode,weeknum);
	loadXqMap(weeknum,orgCode);
}
/**
 * 获取总警情的预警级别
 * @param fdbv 浮动比率
 * @param kfdbv 可浮动比率
 * @returns
 */
function getLbColor(fdbv,kfdbv){
	if(fdbv <= kfdbv){
		return '<span class="layui-badge layui-bg-green">绿色</span>';
	}else{
		return '<span class="layui-badge layui-bg-red">红色</span>';
	}
}

