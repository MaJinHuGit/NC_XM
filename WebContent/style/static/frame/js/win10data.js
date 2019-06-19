//菜单项
var win10data = 
	[
		//{ type : 1 , title : "示例登录页" ,icon : "<i class=\"fa fa-user icon black-green\"></i>" , url : "./login.html" },
		{ type : 1 , title : "切换壁纸" ,   icon : "<i class=\"icon fa fa-fw fa-picture-o blue\" ></i>" , url : "./frame/plugins/theme_switcher/theme_switcher.html"  },
		/*{ type : 2 , title : "二级菜单" ,   icon : "<i class=\"icon fa fa-fw fa-star red\" ></i>" ,
			child :[
				{ type : 1 , title : "切换壁纸" ,   icon : "<i class=\"icon fa fa-fw fa-picture-o blue\" ></i>" , url : "./plugins/theme_switcher/theme_switcher.html"  },
				{ type : 1 , title : "切换壁纸2" ,   icon : "<i class=\"icon fa fa-fw fa-picture-o blue\" ></i>" , url : "./plugins/theme_switcher/theme_switcher.html"  },
			]
		},*/
		
	];

/*
 * 			<div class="shortcut win10-drawer">
                <i class="icon fa fa-fw fa-star red"></i>
                <div class="title">二级菜单</div>
                <div class="win10-drawer-box">
                    <div class="shortcut-drawer win10-drawer">
                        <i class="icon fa fa-fw fa-star red"></i>
                        <div class="title">三级菜单</div>
                        <div class="win10-drawer-box">
                            <div class="shortcut-drawer win10-open-window" data-url="www.baidu.com">
                                <i class="icon fa fa-fw fa-th-list orange"></i>
                                <div class="title">子项1</div>
                            </div>
                            <div class="shortcut-drawer">
                                <i class="icon fa fa-fw fa-th-list orange"></i>
                                <div class="title">子项2</div>
                            </div>
                        </div>
                    </div>
                    <div class="shortcut-drawer win10-open-window" data-url="www.baidu.com">
                        <i class="icon fa fa-fw fa-th-list orange"></i>
                        <div class="title">子项1</div>
                    </div>
                    <div class="shortcut-drawer">
                        <i class="icon fa fa-fw fa-th-list orange"></i>
                        <div class="title">子项2</div>
                    </div>
                   
                </div>
            </div> 
 * 
 * */

$(function(){
	//$("#win10-shortcuts").html("");
	
	$.ajax({
		url:"getMenus",
		dataType:"json",
		async :false,
		success:function(data){
			if(data.code == 0){
				var menus = data.menus;
				var menu = null ;
				var child1 = null ;
				for (var i = 0; i < menus.length; i++) {
					
					var menu =  { type : 2 , title : menus[i].text , icon : "<i class=\"icon blue "+menus[i].attributes.icon+"\" ></i>"  
						, child : []
					};
					
					
					child1 = menus[i].children;
					if(child1 != null )
						for (var j = 0; j < child1.length; j++) {
							menu.child.push( { type : 1 , title : child1[j].text , icon : "<i class=\"icon blue "+child1[j].attributes.icon+"\" ></i>"  
								, url : child1[j].attributes.url
							} ) ; 
						}
					win10data.push( menu );
					
				}
			}
		}
		
	});
	for (var i = 0; i < win10data.length; i++) {
		if(win10data[i].type==1){
			$("#win10-shortcuts" ).append(
				'<div class="shortcut win10-open-window" data-url="'+win10data[i].url+'">'+
				    win10data[i].icon+
				    '<div class="title">'+win10data[i].title+'</div>'+
				'</div>'
			);
		}else{
			var html = 
					'<div class="shortcut win10-drawer" >'+
					    win10data[i].icon+
					    '<div class="title">'+win10data[i].title+'</div>'+
					    '<div class="win10-drawer-box">' ;
					    
					    var child = win10data[i].child;
					    for (var j = 0; j < child.length; j++) {
						    html += 
						    '<div class="shortcut-drawer win10-open-window" data-url="'+child[j].url+'">'+
			                    child[j].icon+
			                    '<div class="title">'+child[j].title+'</div>'+
			                '</div>';
					    }
		                
			html +='</div>'+
					'</div>';
			$("#win10-shortcuts" ).append( html	);
		}
	}
	
});
