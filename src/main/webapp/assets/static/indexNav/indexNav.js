$(document).ready(function(){
	//个人中心
	$('.top-navbar').on('click','#pCenter',perSetting);
	function perSetting(){
		$('#page-wrapper').load('editUser.do');
	}
});