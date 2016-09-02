$(function(){
	//初始化页面时间显示
	initWebTime();    
	//加载菜单
	menuLoad();
	
});
function menuLoad(){
	$.ajax({
		url:'/admin/loadMenu.json',
		type:'POST',
		dataType: "json",
		success:function(data){
			if(data!=null){
				var menu = data.menu;
				$.each(menu, function(n1, v1){
					if(v1.parentId=='0'){
						var html = '<li class="title"><a href="#" onClick="menuClick(this);" style="color:#ffffff;background-color: #003147">'+v1.name+'</a>'+
						'<ul class="nav nav-second-level menu-child" style="display: none">';
						$.each(menu, function(n2, v2){
							if(v2.parentId==v1.id){
								html+='<li>'+
								'<a href="#" style="color:#ffffff;" data="'+v2.menuUrl+'" onclick="newContent(this);">'+v2.name+'</a>'+
								'</li>';
							}
						});
						html+='</ul></li>';
						$("#side-menu").append(html);
					}
				});
			}
		}
	});
}

//初始化菜单单击事件
function menuClick(e){
	$("#side-menu li ul").stop().slideUp(500);
	$("#side-menu>li>a").css("background-color","#003147");
	$("#side-menu>li>ul>li>a").css({"background-color":"#404D5B"});
	$(e).css("background-color","#0099CC");
	if($(e).next("ul").attr("style").indexOf("display: none;")>=0){
		$(e).next("ul").stop().slideDown(500);
		
	}
}
//子菜单点击事件,跳转页面
function newContent(e){
	$("#side-menu>li>a").css("background-color","#003147");
	$("#side-menu>li>ul>li>a").css({"background-color":"#404D5B"});
	$(e).css({"background-color":"#0099CC"});
	subMenuClick($(e).attr("data"));
}
//子菜单点击头跳转
function subMenuClick(url){
	$.ajax({
		url:url,
		type:"get",
		dataType:"html",
		success:function(data){
			$("#webContent").html(data);
		}
	});
	
}


//页面时间显示
function initWebTime(){
	with(new Date)
		$("#webTimer").html((getYear()+2000-100)+"年"+(getMonth()+1)+"月"+getDate()+"日 星期"+"日一二三四五六".charAt(getDay())+" "+getHours()+":"+getMinutes()+":"+getSeconds());
	setInterval(function(){
	with(new Date)
		$("#webTimer").html((getYear()+2000-100)+"年"+(getMonth()+1)+"月"+getDate()+"日 星期"+"日一二三四五六".charAt(getDay())+" "+getHours()+":"+getMinutes()+":"+getSeconds());
	},1000)   
}
