var uuid = parent.data['id'];

$(function(){
	load();
})

function load() {
	$.get("../jqfx/showDetail/one",{id:uuid},function(data){
		var data = data.data;
		$(".layui-table").find("tr").eq(0).find("td").eq(1).html(data.jjdbh)
		
		$(".layui-table").find("tr").eq(1).find("td").eq(1).html(data.dzmc)
		
		$(".layui-table").find("tr").eq(2).find("td").eq(1).html(data.bzdz)
		
		$(".layui-table").find("tr").eq(3).find("td").eq(1).html(data.sfzh)
		
		$(".layui-table").find("tr").eq(4).find("td").eq(1).html(data.gajgmc)
		
		$(".layui-table").find("tr").eq(5).find("td").eq(1).html(data.gajgdm)
		
		$(".layui-table").find("tr").eq(6).find("td").eq(1).html(data.bsfzh)
		
		$(".layui-table").find("tr").eq(7).find("td").eq(1).html(data.lxdh)
		
		$(".layui-table").find("tr").eq(8).find("td").eq(1).html(data.jyaq)
	})

}
/**
 * pgis点详细信息载入
 * @param {} id
 */
function loadjqxx(id){
	$.get("../jqfx/showDetail/one",{id:id},function(data){
		var data = data.data;
		$(".layui-table").find("tr").eq(0).find("td").eq(1).html(data.jjdbh)
		
		$(".layui-table").find("tr").eq(1).find("td").eq(1).html(data.dzmc)
		
		$(".layui-table").find("tr").eq(2).find("td").eq(1).html(data.bzdz)
		
		$(".layui-table").find("tr").eq(3).find("td").eq(1).html(data.sfzh)
		
		$(".layui-table").find("tr").eq(4).find("td").eq(1).html(data.gajgmc)
		
		$(".layui-table").find("tr").eq(5).find("td").eq(1).html(data.gajgdm)
		
		$(".layui-table").find("tr").eq(6).find("td").eq(1).html(data.bsfzh)
		
		$(".layui-table").find("tr").eq(7).find("td").eq(1).html(data.lxdh)
		
		$(".layui-table").find("tr").eq(8).find("td").eq(1).html(data.jyaq)
	})

}



	