var now = new Date();//当天日期
var nowTime = now.getTime();//今天本周的第几天
var day = now.getDay();
var oneDayTime = 24*60*60*1000;

function loadHddjq(){
	$("#dateTime3hidden").val(getWeekStartDate()+" - "+getWeekEndDate());
	$("#dateTime3").val(getWeekStartDate()+" - "+getWeekEndDate());
	hddjqLoad();
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


function hddjqLoad(start,end,ksms) {
	var pcsNames = "";
	$("#hddjqhzt").find("tr").each(function(){
		var pcsName = $(this).find("td").eq(0).text();
		//pcsNames.push(encodeURI(pcsName));
		if(pcsNames==""){
			pcsNames = pcsName+",";
		}else{
			pcsNames += pcsName+",";
		}
	})
	pcsNames = encodeURI(pcsNames)
	$.get("../jqfx/hddjq/getHddjqByBldw", {start:start,end:end,ksms:ksms,pcsNames:pcsNames},function(data) {
		var data = data.data;
		console.log(data)
		for(var key in data){
			var pcsName = key;
			var obj = data[key];
			// 全区”黄、赌、毒“警情汇总表
			$("#hddjqhzt").find("tr").each(function(){
				if(key==$(this).find("td").eq(0).text()){
					for (var i = 0; i < obj.length; i++) {
						var jqs = obj[i].jqs
						var str = jqs.substr(jqs.length-1,1);
						if(isNaN(str)){
							if(str=="↑"){
								jqs = "<label class='red_text'>"+ obj[i].jqs + "</label>"
							}
							if(str=="↓"){
								jqs = "<label class='blue_text'>"+ obj[i].jqs + "</label>"
							}
						}
						if(obj[i].lb=="01"){
							$(this).find("td").eq(1).html(jqs)
						}
						if(obj[i].lb=="02"){
							$(this).find("td").eq(4).html(jqs)
						}
						if(obj[i].lb=="03"){
							$(this).find("td").eq(7).html(jqs)
						}
						if(obj[i].lb=="hj"){
							$(this).find("td").eq(10).html(jqs)
						}
					}
				}
			})
			// 全区”黄、赌、毒“累计警情对比表 
			$("#hddjqdbt").find("tr").each(function(){
				if(key==$(this).find("td").eq(0).text()){
					for (var i = 0; i < obj.length; i++) {
						var jqs = obj[i].jqs
						var str = jqs.substr(jqs.length-1,1);
						if(isNaN(str)){
							if(str=="↑"){
								jqs = "<label class='red_text'>"+ obj[i].jqs + "</label>"
							}
							if(str=="↓"){
								jqs = "<label class='blue_text'>"+ obj[i].jqs + "</label>"
							}
						}
						if(obj[i].lb=="01"){
							$(this).find("td").eq(1).html(jqs)
							$(this).find("td").eq(2).html(obj[i].tqjqs)
							$(this).find("td").eq(3).html(obj[i].tb)
						}
						if(obj[i].lb=="02"){
							$(this).find("td").eq(4).html(jqs)
							$(this).find("td").eq(5).html(obj[i].tqjqs)
							$(this).find("td").eq(6).html(obj[i].tb)
						}
						if(obj[i].lb=="03"){
							$(this).find("td").eq(7).html(jqs)
							$(this).find("td").eq(8).html(obj[i].tqjqs)
							$(this).find("td").eq(9).html(obj[i].tb)
						}
					}
				}
			})
		}
	})
}

$(".clickClass").click(function() {
	var lb = $(this).attr("data-lb");
	var bldw = $(this).siblings(":first").text();    

	data = {
		"lb":lb,
		"bldw":bldw,
		"ksms":$("#right3 input:radio[name=choose]:checked").val()==null?"bz":$("#right3 input:radio[name=choose]:checked").val(),
		"zxsj":$("#dateTime3hidden").val()
	};
	layer.open({
		id : 1,
		type : 2,
		title : bldw + convert(lb) + '警情列表',
		maxmin : true,
		skin : 'layer-ext-moon',
		area : [ '1100px', '600px' ],
		offset : [ '10px', '50px' ],
		shade : false,
		maxmin : true,
		shadeClose : true,
		closeBtn : 1,
		content : 'hddjqList.html',
		btn : [ '关闭' ],
		btn1 : function(index, layero) {
			layer.close(index);
		}
	})
})

$("#right3 input:radio[name=choose]").change(function(){
	var ksms = $(this).val()
	var name = "";
	hddjqLoad("","",ksms);
});


function convert(lb) {
	if (lb == "01") {
		return "“黄”";
	} else if (lb == "02") {
		return "“赌”";
	} else if (lb == "03") {
		return "“毒”";
	} else {
		return lb;
	}
}
