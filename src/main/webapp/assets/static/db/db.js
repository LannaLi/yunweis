var count = 1;
function create(){
	var html = table(count);
	$('#tables').append(html);
	count++;
}

function table(count){
	var html = $('<div class="form-group">'
					+'<div class="col-sm-2">'
						+'<input type="text" id="name'+count+'" placeholder="字段或表名" class="form-control required">'
					+'</div>'
					+'<div class="col-sm-2">'
						+'<input type="text" id="colums'+count+'" placeholder="字段类型" class="form-control required">'
					+'</div>'
					+'<div class="col-sm-2">'
						+'<input type="text" id="comment'+count+'" placeholder="字段说明" class="form-control required">'
					+'</div>'
				+'</div>');
	return html;
}

function submit(){
	var len = $('#tables').children().length;
	if (len == 0) {
		alert("没有可以提交的数据");
		return false;
	} else if (len > 0 && len < 3) {
		alert("请填写该表中的除了主键字段外的字段");
		return false;
	}
	var array = [];
	for (var i=2;i<=len;i++) {
		var str = 'key='+i+'&'
				 +'name='+$('#name'+i).val()+'&'
				 +'colums='+$('#colums'+i).val()+'&'
				 +'comment='+$('#comment'+i).val();
		array.push(str);
	}
	
	var url = 'table/addTable.do',
		param = {'array':array,'tableName':$('#name1').val()};
	$.post(url,param,function(result){
		if (result.state == 1) {
			alert("创建表成功!");
			$('#page-wrapper').load("table/tablelist.do");
		} else {
			alert(result.msg);
		}
	});
}



