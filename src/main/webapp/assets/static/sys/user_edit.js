$(document).ready(function(){
	$('#userForm').on('click','.btn-b',function(){
		if ($(this).hasClass('btn-back')) {
			$('#page-wrapper').load('loadDefaultPage.do');
		}
		if ($(this).hasClass('btn-sure')) {
			var param = getParam();
			$.ajax({
				type:'POST',
				url:'user/updateU.do',
				data:param,
				dataType:'json',
				success:function(result){
					if (result.state == 1) {
						alert('修改成功!');
						$('#page-wrapper').load('editUser.do');
					} else {
						alert("发生了错误:"+result.msg)
					}
				}
			});
		}
	});
	
	//获取参数
	function getParam(){
		var param = {
			'userid':$('#userForm').attr('user-id'),
			'username':$('#username').val(),
			'password':$('#password').val(),
			'realname':$('#realname').val(),
			'phone':$('#phone').val(),
			'email':$('#email').val(),
			'weichat':$('#weichat').val(),
			'qqcode':$('#qqcode').val(),
			'sex':$('#sex').val(),
			'birthdate':$('#birthdate').val(),
			'onduty':$('#onduty').val(),
			'belarea':$('#belarea').val()
		}
		return param;
	}
	
})