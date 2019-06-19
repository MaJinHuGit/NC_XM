$(function(){ 
	ssyjLoad("","","","");
});

function ssyjLoad(start,end,orgname,weeknum){
	$.ajaxSettings.async = false;
	$.get("../jqfx/ssyj/countByWeek",{start:start,end:end,orgname:orgname,weeknum:weeknum},function(data){
		var data = data.data;
		//$("#sstable").find("tr").eq(2).find("td").eq(0).html(data.wfjq.orgname) //警情区域
		var num = 3;
//		if(!$("#backall").is(":hidden")){
//			num += 1;
//		}
		$("#sstable").find("tr").eq(num).find("td").eq(2).html(data.wfjq.jqsl)//本周
		$("#sstable").find("tr").eq(num).find("td").eq(3).html(data.wfjq.szjz) //四周均值
		$("#sstable").find("tr").eq(num).find("td").eq(4).html(data.wfjq.zs) //增速
		
		$("#sstable").find("tr").eq(num+1).find("td").eq(1).html(data.qc.jqsl)//本周
		$("#sstable").find("tr").eq(num+1).find("td").eq(2).html(data.qc.szjz) //四周均值
		$("#sstable").find("tr").eq(num+1).find("td").eq(3).html(data.qc.zs) //增速
		
		$("#sstable").find("tr").eq(num+2).find("td").eq(1).html(data.lq.jqsl)//本周
		$("#sstable").find("tr").eq(num+2).find("td").eq(2).html(data.lq.szjz) //四周均值
		$("#sstable").find("tr").eq(num+2).find("td").eq(3).html(data.lq.zs) //增速
		
		$("#sstable").find("tr").eq(num+3).find("td").eq(1).html(data.rsdq.jqsl)//本周
		$("#sstable").find("tr").eq(num+3).find("td").eq(2).html(data.rsdq.szjz) //四周均值
		$("#sstable").find("tr").eq(num+3).find("td").eq(3).html(data.rsdq.zs) //增速
		
		$("#sstable").find("tr").eq(num+4).find("td").eq(1).html(data.scdq.jqsl)//本周
		$("#sstable").find("tr").eq(num+4).find("td").eq(2).html(data.scdq.szjz) //四周均值
		$("#sstable").find("tr").eq(num+4).find("td").eq(3).html(data.scdq.zs) //增速
		
		$("#sstable").find("tr").eq(num+5).find("td").eq(1).html(data.zp.jqsl)//本周
		$("#sstable").find("tr").eq(num+5).find("td").eq(2).html(data.zp.szjz) //四周均值
		$("#sstable").find("tr").eq(num+5).find("td").eq(3).html(data.zp.zs) //增速
		
		$("#sstable").find("tr").eq(num+6).find("td").eq(1).html(data.md.jqsl)//本周
		$("#sstable").find("tr").eq(num+6).find("td").eq(2).html(data.md.szjz) //四周均值
		$("#sstable").find("tr").eq(num+6).find("td").eq(3).html(data.md.zs) //增速
	})
	$.ajaxSettings.async = true;
}

//加载搜索日期
$.get("../jqfx/ssyj/getDate",function(data){
	var data = data.data
	for(var key in data){
		$("#ssyjSelect").append("<option value='"+key+"'>"+data[key]+"</option>");
	}  
})

function search(){
	var weeknum = $("#ssyjSelect option:selected").val();
	ssyjLoad("","","",weeknum)
}
