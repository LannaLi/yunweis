$(document).ready(function(){
	//去掉下航线
	$('#pagination').find('a').css('text-decoration','none');
	//点击事件
	$('#pagination').on('click','.page',goPage);
});

//设置页面
function setPage(page){
	$('#pagination').data('currentPage',page.currentPage);
	$('#pagination').data('pageCount',page.pageCount);
}

function goPage () {
	var currentPage = $('#pagination').data('currentPage');
	var pageCount = $('#pagination').data('pageCount');
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
		if (currentPage > pageCount) {
			currentPage = pageCount;
		}
	}
	if ($(this).hasClass('page-last')) {
		currentPage = pageCount;
	}
	//重新绑定数据
	$('#pagination').data('currentPage',currentPage);
	doQueryPageObject();
}


