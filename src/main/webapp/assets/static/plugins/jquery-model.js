;(function($,document){
	"use strict";
	
	function createForm(option){
		var key = [];
		for(var i in option){
			key.push(i);
		}
		var menuform = $('<form class="form-horizontal" role="form"></form>');
		for(var i=0;i<key.length;i++){
			var k = key[i];
			var div = $('<div class="form-group" style="margin: 0px 0px 0px 0px;">'+
							'<label for="'+k+'" class="col-sm-3 control-label">'+option[k]+'</label>'+
							'<div class="col-sm-8">'+
								'<input type="text" class="form-control" id="class'+k+'" placeholder="请输入'+option[k]+'" style="padding: 0px 0px 0px 0px;">'+
							'</div>'+
						'</div>');
			menuform.append(div);
		};
		return menuform;
	}
})(jQuery,document)