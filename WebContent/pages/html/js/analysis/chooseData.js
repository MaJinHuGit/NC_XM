var varcharReg=/varchar/i;
var timeReg=/(time)+|(date)+/i
var numReg=/number/i
var filterData;
var returnData;
var arrayData=new Array();
var dateinput;
var columns;
function initCondition(cols,data,array){
	console.log("data_array");
	console.log(cols);
	console.log(array);
	
	arrayData=array
	filterData=data;
	columns=cols;
	if(varcharReg.test(data.dataType)){
		varcharInit();
		
		
	}
	if(timeReg.test(data.dataType)){
		timeInit();
		
	}
	
	if(numReg.test(data.dataType)){
		numberInit();
		
	}
	loadCol(cols);
	showResult();
	
}

function varcharInit(){
	$("#tb1").css("display","block");
	$("#date1").css("display","none");
	$("#date2").css("display","none");
	$("#tb2").css("display","none");
	$("#tb4").css("display","none");
	$("#tb3").css("display","none");
	$("#addbtn").val(1);
}
function date1Init(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#date1',//指定元素
		    theme: 'grid',
		    type: 'datetime'
		  });
		});
}

function date2Init(){
	layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#date2',//指定元素
		    theme: 'grid',
		    type: 'datetime',
		    range:"~"
		  });
		});
}


function timeInit(){
	$("#tb1").css("display","none");
	$("#tb2").css("display","block");
	$("#tb4").css("display","none");
	$("#date1").css("display","block");
	$("#date2").css("display","none");
	$("#tb3").css("display","none");
	dateinput="#date1";
	date1Init();
	date2Init();
	$("#addbtn").val(2);
}

function numberInit(){
	$("#tb4").css("display","block");
	$("#tb1").css("display","none");
	$("#tb2").css("display","none");
	$("#date1").css("display","none");
	$("#date2").css("display","none");
	$("#tb3").css("display","none");
	$("#addbtn").val(3);
}


$("#timeselect").change(function(){
	console.log("change");
	if('gtlt'==$(this).val()){
		$("#tb3").css("display","block");
		$("#date1").css("display","none");
		$("#date2").css("display","inline");
		dateinput="#date2";
	}else{
		$("#tb3").css("display","none");
		$("#date1").css("display","inline");
		$("#date2").css("display","none");
		dateinput="#date1";
	}
});




$("#addbtn").click(function(){	
	var btnvalue=$(this).val();
	var col_index=$("#selectCol").val();
	var col=columns[col_index];
	console.log("colName:"+col);
	switch(btnvalue){
		case "1":
			addVarCharCondition(col);
			break;
		case "2":
			console.log("2----");
			addTimeCondition(col);
			break;
		case "3":
			addNumberCondition(col)	
			break;
	}
	
})

function addTimeCondition(col){
	var dateval=$(dateinput).val();
	var compareHtml=$("#timeselect").find("option:selected").text();
	var compareval=$("#timeselect").val();
	var conditionName=col.columnComments+" "+compareHtml+" "+dateval;
	var condition;
	var logicOp="and";
	var extraParam=null;
	var column=col.columnName;
	var value="'"+dateval+"'";
	if(timeReg.test(col.dataType)){
		value="to_date('"+dateval+"','yyyy-mm-dd hh24:mi:ss')";
	}
	switch(compareval){
	case "lt":
		var compare="<";
		condition=column+compare+value;
		break;
	case "gt":
		var compare=">";
		condition=column+compare+value;
		break;
	case "le":
		var compare="<=";
		condition=column+compare+value;
		break;
	case "ge":
		var compare=">=";
		condition=column+compare+value;
		break;
	case "gtlt":
		var arr=dateval.split("~");
		var value1,value2;
		if(timeReg.test(col.dataType)){
			value1="to_date('"+arr[0].trim()+"','yyyy-mm-dd hh24:mi:ss')";
			value2="to_date('"+arr[1].trim()+"','yyyy-mm-dd hh24:mi:ss')";
		}else{
			value1="'"+arr[0].trim()+"'";
			value2="'"+arr[1].trim()+"'";
		}

		condition=column+">="+value1+" and "+column+"<="+value2;
		extraParam=new Array();
		$(".chekcebox:checked").each(function(){
			let test=$(this).val();
			switch(test){
			case "tb":
				conditionName+=" 同比";
				extraParam.push("tb");
				break;
			case "tbl":
				conditionName+=" 同比率";
				extraParam.push("tbl");
				break;
			case "hb":
				conditionName+=" 环比";
				extraParam.push("hb");
				break;
			case "hbl":
				conditionName+=" 环比率";
				extraParam.push("hbl");
				break;
			}
		});
		break;
	}
	var result={
			conditionName:conditionName,
			condition:condition,
			logicOp:logicOp,
			extraParam:extraParam,
			originData:dateval,
			colName:column
	}
	arrayData.push(result);
	showResult();
}

function showResult(){
	$("#condition").html("");
	arrayData.forEach(function(row,index){
		var logic;
		if(index==0){
			if(row.logicOp=="or"){
				row.logicOp=="and"
			}
			logic="并且"
		}else{
			if(row.logicOp=="and"){
				logic="<select onchange='logicChange("+index+")' id='logic'>" +
						"<option value='and'  selected='selected'>并且</option>" +
						"<option value='or'>或者</option>" +
						"</select>"
			}else{
				logic="<select onchange='logicChange("+index+")' id='logic'>" +
				"<option value='and'>并且</option>" +
				"<option value='or'  selected='selected'>或者</option>" +
				"</select>"
			}
		}
		
		
		var html='<tr>'
		+'<td>'+row.conditionName+'</td>'
		+'<td>'+logic+'</td>'
		+'<td><button onclick="deleteCondition('+index+')" class="layui-btn layui-btn-sm layui-btn-danger">删除</button></td>'
		+'</tr>';
		$("#condition").append(html);
	})
	console.log("------------------------")
	console.log(arrayData);
}


function logicChange(index){
	var logicval=$("#logic").val();
	arrayData[index].logicOp=logicval;
}

function deleteCondition(index){
	arrayData.splice(index,1);
	showResult();
}

function addVarCharCondition(col){
	
	var varcharVal=$("#varchar").val();
	var compareHtml=$("#varcharSelect").find("option:selected").text();
	var compareval=$("#varcharSelect").val();
	var conditionName=col.columnComments+" "+compareHtml+" \""+varcharVal+"\"";
	var condition;
	var logicOp="and";
	var extraParam=null;
	var column=col.columnName;
	var value="'%"+varcharVal+"%'";
	switch(compareval){
		case "like":
			var compare=" like ";
			condition=column+compare+value;
			break;
		case "unlike":
			var compare=" not like ";
			condition=column+compare+value;
			break;
		case "eq":
			var compare=" = ";
			condition=column+compare+value;
			break;
		case "nq":
			var compare=" != ";
			condition=column+compare+value;
			break;
		}
	var result={
			conditionName:conditionName,
			condition:condition,
			logicOp:logicOp,
			extraParam:extraParam,
			originData:varcharVal,
			colName:column
	}
	arrayData.push(result);
	showResult();
}

function addNumberCondition(col){
	var numberVal=$("#number").val();
	var compareHtml=$("#numberSelect").find("option:selected").text();
	var compareval=$("#numberSelect").val();
	var conditionName=col.columnComments+" "+compareHtml+" "+varcharVal+" ";
	var condition;
	var logicOp="and";
	var extraParam=null;
	var column=col.columnName;
	var value=numberVal;
	if(!numReg.test(filter.dataType)){
		value="\""+numberVal+"\""
	}
	switch(compareval){
		case "lt":
			var compare=" < ";
			condition=column+compare+value;
			break;
		case "gt":
			var compare=" > ";
			condition=column+compare+value;
			break;
		case "le":
			var compare=" <= ";
			condition=column+compare+value;
			break;
		case "ge":
			var compare=" >= ";
			condition=column+compare+value;
			break;
		case "eq":
			var compare=" = ";
			condition=column+compare+value;
			break;
		case "ne":
			var compare=" != ";
			condition=column+compare+value;
			break;
	}
	var result={
			conditionName:conditionName,
			condition:condition,
			logicOp:logicOp,
			extraParam:extraParam,
			originData:varcharVal,
			colName:column
	}
	arrayData.push(result);
	showResult();
}

function returndata(){
	return arrayData;
}

function changeSelect(){
	var selectVal=$("#selecttb").val();
	if(selectVal!=null&&selectVal!=""){
		switch(selectVal){
			case "varchar":
				varcharInit();
				break;
			case "datetime":
				timeInit();
				break;
			case "number":
				numberInit();
				break;	
		}
	}
}

function loadCol(cols){
	var html="";
	cols.forEach(function(col,index){
		html+="<option value='"+index+"'>"+col.columnComments+"</option>";
	})
	$("#selectCol").html(html);
}


