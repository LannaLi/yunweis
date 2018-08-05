	
	$(document).ready(function(){
		$('.submit').css('display','none');
		//键盘enter事件
		$(document).keyup(function(event){
			if (event.keyCode == 13) {
				$('.submit').trigger('click');
			}
		});
		$('.mb2').on('click','.submit',Log);
		//悬浮事件
		$('#u_tips').hover(
				function(){
					$('#u_tips').addClass('tips');
					$('#u_tips').tips({
						msg:'不能用数字,汉字和特殊字符开头',
						bg:'orange',
						side:2,
						time:1
					});
				},
				function(){
					$('#u_tips').removeClass('tips');
				}
		);
		//用户名绑定失去焦点事件
		$('#username').blur(function(){
			var uname = $('#username').val();
			var up = uname + '&' + 'username';
			validLega(up);
		});
		//密码绑定键盘事件
		$('#passwd').keyup(function(){
			var pwd = $('#passwd').val();
			var	p = pwd + '&' + 'passwd';
			if(validPwdLega(p)){
				$('.submit').css('display','block');
			} else {
				$('.submit').css('display','none');
			}
		});
		
		function Log(){
			var username = $('#username').val();
			var pwd = $('#passwd').val();
			if ((username == '' || username.length == 0)
					||(pwd == '' || pwd.length == 0)) {
				return false;
			}
			var param = {'username':username,'password':pwd};
			$.post('signIn.do',param,function(data){
				
				if (data.state == 1) {
					if (data.data == "success") {
						alert('登陆成功');
						window.location.href = 'index.do';
					} else {
						alert('您的账号已经被'+data.data+',请联系管理员');
						return false;
					}
				} else {
					if (data.msg == "PWDERROR") {
						$('#passwd').tips({
							msg:"密码错误",
							side:2,
							bg:'orange'
						});
						$('#passwd').focus();
						return false;
					} else if (data.msg == "SERVERERROR") {
						$('#passwd').tips({
							msg:"服务器繁忙,请稍后再试!",
							side:2,
							bg:'orange'
						});
						$('#passwd').focus();
						return false;
					}
				}
			});
		}
		
		
		/**
		 * 
		 * @param s  用户名的参数  'username&id'
		 * @returns
		 */
		function validLega(s){
			var val = s.split('&')[0],
				id = s.split('&')[1];
			
			//用户名不能为空
			if (val.length == 0) {
				$('#'+ id).tips({
					msg:'用户不能为空',
					side:2,
					bg:'orange'
				});
				$('#'+ id).focus();
				return false;
			}
			//第一个字符是否为数字或者特殊符号
			var v = val.substring(0,1);
			//数字正则
			var regNun = /^[-+]?\d*$/;
			//汉字正则
			var regHan = /^[\u0391-\uFFE5]+$/;
			//特殊符号正则
			var regTeS = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
			switch (true) {
			case regNun.test(v):
			case regHan.test(v):
			case regTeS.test(v):
				$('#'+ id).tips({
					msg:'请输入正确的用户名',
					side:2,
					bg:'orange'
				});
				$('#'+ id).focus();
				return false;
				break;
			default:
				break;
			}
			
			//验证用户名的唯一性
			$.post('user/isHasUser.do',{'username':val},function(data){
				if (data.state == 1) {
					if (data.data == "false") {
						$('#'+ id).tips({
							msg:'用户不存在,请联系管理员',
							side:2,
							bg:'orange'
						});
						$('#'+ id).focus();
						return false;
					}
				} else {
					alert(data.msg);
				}
			});
		}
		
		
		
		/**
		 * 验证合法性
		 * @param s  pwd + '&' + 'passwd'
		 */
		function validPwdLega(s){
			var val = s.split('&')[0],
				id = s.split('&')[1];
			
			if (val.length == 0) {
				$('#'+ id).tips({
					msg:'请输入密码',
					side:2,
					bg:'orange'
				});
				$('#'+ id).focus();
				return false;
			} else if (val.length < 6) {
				$('#'+ id).tips({
					msg:'长度不能少于6位',
					side:2,
					bg:'orange'
				});
				$('#'+ id).focus();
				return false;
			} else if (val.length > 8) {
				$('#'+ id).tips({
					msg:'长度不能多于8位',
					side:2,
					bg:'orange'
				});
				$('#'+ id).focus();
				return false;
			}
			return true;
		}
	
	});