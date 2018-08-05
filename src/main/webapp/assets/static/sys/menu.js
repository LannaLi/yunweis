$(document).ready(function(){
	loadMenuList();
	$('.navs').on('click','.navs-item>a',setAnimate);
	$('.navs').on('click','.reqUrl',loadContainer);
	function loadMenuList(){
		var url = 'permMenuLists.do';
		$.post(url,function(data){
			if (data.state == 1) {
				loadMenu(data.data);
			} else {
				alert(data.msg);
				return false;
			}
		});
	}
	
	function loadMenu(list){
		var menu = $('.navs');
		menu.empty();
		var ul = $('<ul></ul>');
		ul.append('<li class="navs-item">'+
				  	 '<a>'+
				  		'<span><i class="fa fa-dashboard"></i>&emsp;\u603B\u63A7\u53F0</span><i></i>'+
				  	 '</a>'+
				  '</li>');
		//加载一级菜单
		for(var i in list){
			if(list[i].parentid == null || list[i].parentid == ""){
				var temp = 	'<li class="navs-item">'+
								'<a>'+
						   			'<i></i>'+
						   			'<span><i class="fa fa-plus-square-o"></i>&emsp;'+list[i].permname+'</span>'+
						   		'</a>'+
						   		'<ul id="menu'+list[i].permid+'"></ul>'+
						   	'<li>';
				ul.append(temp);
				menu.append(ul);
			}
		};
		//加载二级菜单
		for(var i in list){
			if(list[i].parentid != null && list[i].parentid != ""){
				var parentEle = $('#menu'+list[i].parentid);
				var li = $('<li style="margin-left:16px;"></li>');
				li.data('url',list[i].permurl);
				li.append('<a class="reqUrl"><span><i class="fa fa-minus-square-o"></i>&emsp;'+list[i].permname+'</span></a>');
				parentEle.append(li);
			}
		};
	}
	
	function loadContainer(){
		var url = $(this).parent().data('url');
		url = url + '?t=;'+Math.random(1000);
		$('#page-wrapper').load(url);
	}
	
	
	function setAnimate(){
		if (!$('.navs').hasClass('navs-mini')) {
	        if ($(this).next().css('display') == "none") {
	            $('.navs-item').children('ul').slideUp(300);
	            $(this).next('ul').slideDown(300);
	            $(this).parent('li').addClass('navs-show').siblings('li').removeClass('navs-show');
	            $(this).children('span').children('i').removeClass('fa-plus-square-o').addClass('fa-minus-square-o');
	        }else{
	            $(this).next('ul').slideUp(300);
	            $('.navs-item.navs-show').removeClass('navs-show');
	            $(this).children('span').children('i').removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
	        }
	    }
	}
	
	function setAnimates(){
		if (!$('.navs').hasClass('navs-mini')) {
	        $('.navs-item.navs-show').removeClass('navs-show');
	        $('.navs-item').children('ul').removeAttr('style');
	        $('.navs').addClass('navs-mini');
	    }else{
	        $('.navs').removeClass('navs-mini');
	    }
	}
});