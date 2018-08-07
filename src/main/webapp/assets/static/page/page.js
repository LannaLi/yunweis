$(document).ready(function(){
	//去掉下航线
	$('#pagination').find('a').css('text-decoration','none');
	//点击事件
	$('#pagination').on('click','.page',goPage);
});

//设置页面
function setPage(page){
	$('#pagination').data('currentPage',page.currentPage);
	$('#pagination').data('rowCount',page.rowCount);
}

function goPage () {
	var currentPage = $('#pagination').data('currentPage');
	var rowCount = $('#pagination').data('rowCount');
	if ($(this).hasClass('page-first')) {
		currentPage = 1;
	}
	if ($(this).hasClass('page-pre')) {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
		}
	}
	if ($(this).hasClass('page-next')) {
		currentPage++;
		if (currentPage > rowCount) {
			currentPage = rowCount;
		}
	}
	if ($(this).hasClass('page-last')) {
		currentPage = rowCount;
	}
	//重新绑定数据
	$('#pagination').data('currentPage',currentPage);
	doQueryPageObject();
}



