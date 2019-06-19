$(function () {
	  $.ajax({
		  type:'get',
		  async:false, 
          url: basePath+"getMenus",
          data: $('#signupForm').serialize(),
          success: function (r) {
               if (r.code == 0) {
                 var menus=r.menus;
                 //遍历菜单
                 var html="";
                 for (var i = 0; i < menus.length; i++) {
					html+="<li ><a href='#'> <i class='fa fa fa-bar-chart-o' class='"+menus[i].attributes.icon+"'></i>"+
                    "<span class='nav-label' >"+menus[i].text+"</span> <span class='fa arrow'></span></a>"+
                    "<ul class='nav nav-second-level'>";
					 for (var j = 0; j < menus[i].children.length; j++) {
					 html+= "<li><a class='J_menuItem'  "+
                     " href='"+basePath+menus[i].children[j].attributes.url+"'   data-index=\"10\" >"+ menus[i].children[j].text+"</a></li>";
					 }
					 html+= " </ul></li>"; 
				 }
                  $("#side-menu").append(html);
              } else {
                  layer.msg(r.msg);
              }
          },
      });
})