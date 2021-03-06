function Win(){
	this.mainId = 'mainWin';
	this.sureId = 'Btn-Sure';
	this.closeId = 'Btn-Close';
	this.titleId = 't-header';
	this.textId = 'dyText';
	this.title = '提示框';
	this.data = '确定要删除?';
}

Win.prototype = {
	init:function(){
		//设置标题
		this.setTitle();
		//设置弹窗在中间显示
		this.autoCenter();
		//设置内容
		this.setContent();
	},
	setTitle:function(){
		$('#' + this.titleId).text(this.title);
	},
	autoCenter:function(){
		//显示
		this.show();
		//屏幕的高和宽
		var bodyW = $(window).width();
		var bodyH = $(window).height();
		//element的高和宽
		var elW = $('#' + this.mainId).width();
		var elH = $('#' + this.mainId).height();
		$('#' + this.mainId).css({"left":(bodyW-elW)/2 + 'px',"top":(bodyH-elH)/2 +'px'});
	},
	setContent:function(){
		$('#' + this.textId).text(this.data);
	},
	show:function(){
		$('#' + this.mainId).fadeIn(1000);
	},
	close:function(){
		$('#' + this.mainId).hide();
	},
	setData:function(data){
		this.data = data;
	}
}