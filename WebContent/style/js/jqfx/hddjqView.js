var jjdbh = parent.data['jjdbh'];

$(function(){
	load();
})

function load() {
	$.get("../jqfx/bzjq/get",{jjdbh:jjdbh},function(data){
		var data = data.data;
		$(".layui-table").find("tr").eq(0).find("td").eq(1).html(data.jjdbh)
		$(".layui-table").find("tr").eq(0).find("td").eq(3).html(data.bjsj)
		
		$(".layui-table").find("tr").eq(1).find("td").eq(1).html(data.bjlb)
		$(".layui-table").find("tr").eq(1).find("td").eq(3).html(data.bjlx)
		
		$(".layui-table").find("tr").eq(2).find("td").eq(1).html(data.bjxl)
		$(".layui-table").find("tr").eq(2).find("td").eq(3).html(data.bjdh)
		
		$(".layui-table").find("tr").eq(3).find("td").eq(1).html(data.afdz)
		$(".layui-table").find("tr").eq(3).find("td").eq(3).html(data.gjz)
		
		$(".layui-table").find("tr").eq(4).find("td").eq(1).html(data.gxdw)
		$(".layui-table").find("tr").eq(4).find("td").eq(3).html(data.jjlyh)
		
		$(".layui-table").find("tr").eq(5).find("td").eq(1).html(data.bjnr)
		$(".layui-table").find("tr").eq(5).find("td").eq(3).html(data.jams)
	})

}



	