$(document).ready(function(){
	setSettingData();
	function setSettingData(){
		$('#pCenter').text('个人中心');
		$('#pSetting').text('系统设置');
		$('#quit').text('退出');
	}
	loadDefaultPage();
	function loadDefaultPage(){
		$('#page-wrapper').load('loadDefaultPage.do');
	}
});