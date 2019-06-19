//
//$(function(){
//	
//	getGwqy("","","");
//	getGwsd("","","");
//})
//
// 
///**
// *  高危区域
// * @param startTime  开始时间
// * @param endTime  结束时间
// * @param fstype  犯罪类别
// * @returns
// */
//function getGwqy(startTime,endTime,fstype){
//	
//	var url = "../jqfx/ztyp/getGwqy";
//	$.get(url,{startTime:startTime,endTime:endTime,fstype:encodeURI(fstype)},function(data){
//		//console.log(data)
//	     var datas = data.gwqy
//	     var html = "";
//		html +=datas[0].name+datas[0].cunt+"件，"+datas[1].name+datas[1].cunt+"件，"+datas[2].name+datas[2].cunt+"件<a onclick='showGwqy(\""+startTime+"\",\""+endTime+"\")'>详情>></a>";
//		$("#gwqy").html(html);
//	    });
//	}
///**
// * 高危时段
// @param startTime  开始时间
// * @param endTime  结束时间
// * @param fstype  犯罪类别
// * @returns
// */
//function getGwsd(startTime,endTime,fstype){
//	var url = "../jqfx/ztyp/getGwsd";
//	$.get(url,{startTime:startTime,endTime:endTime,fstype:encodeURI(fstype)},function(data){
//		//console.log(data)
//	     var datas = data.gwsd
//	     var time = datas[0].dates
//	    // alert(time.substr(0,1))
//	     if(time.substr(0,1)==0){
//	    	 time = time.substr(1,1)
//	     }else{
//	    	 time = time.substr(0,2)
//	     }
//	     var html = "";
//		html +=time+"点<a onclick='showGwsd(\""+startTime+"\",\""+endTime+"\")'>详情>></a>";
//		$("#gwsd").html(html);
//	    });
//	}
//
