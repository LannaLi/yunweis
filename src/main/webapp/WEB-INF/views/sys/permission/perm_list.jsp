<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../taglib/indexHeadTag.jsp"%>
<div class="header"> 
	<h3 class="page-header">${fnc:getCompanyName()}</h3>
	<ol class="breadcrumb" id="header-nav">
		<li><a>系统设置</a></li>
		<li><a>权限管理</a></li>
	</ol> 
</div>
<div id="page-inner pages">
 	<div class="dashboard-cards"> 
   		<div class="row">
       		<div class="col-xs-12 col-sm-12 col-md-12" class="contentPage" id="content">
       			<div class="card horizontal cardIcon waves-effect waves-dark">
					<div class="card-stacked orange">
						<div class="card-content">
							<form id="permManager">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12" id="roleTable">
										<shiro:hasPermission name="sys:perm:view">
											<shiro:hasPermission name="sys:perm:add">
											<ul class="list-unstyled list-inline left">
												<li><input type="text" class="btn btn-info btn-srole" placeholder="请输入角色名称" id="rname"></li>
												<li><input type="text" class="btn btn-info btn-srole" placeholder="请输入角色编号" id="rnumber"></li>
											</ul>
											</shiro:hasPermission>
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>角色名称</th>
														<th>角色编号</th>
														<th>状态</th>
														<shiro:hasPermission name="sys:perm:add">
															<th>增</th>
														</shiro:hasPermission>	
														<shiro:hasPermission name="sys:perm:delete">
															<th>删</th>
														</shiro:hasPermission>	
														<shiro:hasPermission name="sys:perm:update">
															<th>改</th>
														</shiro:hasPermission>	
														<shiro:hasPermission name="sys:perm:add">
															<th>查</th>
														</shiro:hasPermission>	
														<shiro:hasPermission name="sys:perm:add">
															<th>操作</th>
														</shiro:hasPermission>
													</tr>
												</thead>
												<tbody id="roleBody"></tbody>
											</table>
										</shiro:hasPermission>
										<shiro:hasPermission name="sys:perm:add">
										<ul class="list-unstyled list-inline left">
											<li class='O2'>
												<button type="button" class="btn btn-info btn-createRole">创建角色</button>
											</li>
										</ul>
										</shiro:hasPermission>
										<%@include file="../../page/pagenation.jsp" %>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
       		</div>
        </div>
	</div>
</div>
<div id="permDiv" style="display:none;padding: 0px 0px 0px 0px;">
	<form class="form-horizontal" role="form" id="permForm">
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="name" class="col-sm-3 control-label">用户名:</label>
			<div class="col-sm-7">
				<input type="text" class="form-control" id="rolename" placeholder="请输入角色名" style="padding: 0px 0px 0px 0px;">
			</div>
			<i class="glyphicon glyphicon-question-sign rolename_tips" style="margin-top:10px;font-size:18px;cursor:pointer;" id="rolename_tips"></i>
		</div>
	</form>
</div>
<div class="layui-layer layui-layer-page layui-layer-molv layer-anim" id="menuLayer" type="page" times="2" showtime="0" contype="object"
		style="z-index: 19891016; width: 300px; height: 450px; top: 100px; left: 500px; display:none">
	<div class="layui-layer-title" style="cursor: move;" id="permTitle"></div>
	<div class="layui-layer-content" style="height: 358px;">
		<div style="padding:10px;" class="layui-layer-wrap">
			<ul id="permTree" class="ztree"></ul>    <!-- 动态加载树 -->
		</div>
	</div>
	<span class="layui-layer-setwin"> 
		<a class="layui-layer-ico layui-layer-close layui-layer-close1 layer-btn btn_cancle" ></a>
	</span>
	<div class="layui-layer-btn layui-layer-btn-">
		<a class="layui-layer-btn0 layer-btn btn_confirm"></a>
		<a class="layui-layer-btn1 layer-btn btn_cancle">取消</a>
	</div>
</div>
<script src="${pageContext.request.contextPath}/assets/static/sys/perm_list.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/model.css"/>
<script>
doQueryPageObject();
function doQueryPageObject() {
	var rParam = getRParam ();
	var currentPage = $('#pagination').data('currentPage');
	if (currentPage) {rParam.currentPage = currentPage};
	$.ajax({
		type:'POST',
		url:'perm/queryR.do',
		data:rParam,
		dataType:'json',
		success:function(result){
			if (result.state == 1) {
				var page = result.data.pagination;
				setPage(page);
				var list = result.data.roleList;
				$('#roleBody').empty();
				for (var i in list) {
					var tr = $('<tr></tr>');
					tr.append('<td data-id="'+list[i].roleid+'">'+list[i].rolename+'</td>');
					tr.append('<td>'+list[i].rolenumber+'</td>');
					tr.append('<td>'+list[i].status+'</td>');
					<shiro:hasPermission name="sys:perm:add">
					tr.append('<td>'+
								'<span class="label label-info">'+
									'<i class="glyphicon glyphicon-menu glyphicon-plus plus"></i>'+
								'</span>'+
							  '</td>');
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:perm:delete">
					tr.append('<td>'+
								'<span class="label label-warning">'+
									'<i class="glyphicon glyphicon-menu glyphicon-remove remove"></i>'+
								'</span>'+
							  '</td>');
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:perm:update">
					tr.append('<td>'+
								'<span class="label label-info">'+
									'<i class="glyphicon glyphicon-menu glyphicon-pencil pencil"></i>'+
								'</span>'+
							  '</td>');
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:perm:add">
					tr.append('<td>'+
								'<span class="label label-success">'+
									'<i class="glyphicon glyphicon-menu glyphicon-eye-open open"></i>'+
								'</span>'+
							  '</td>');
					</shiro:hasPermission>
					<shiro:hasPermission name="sys:perm:add">
					tr.append('<td>'+
								'<ul class="list-unstyled list-inline">'+
									'<li><span class="label label-info label-handler recover">恢复</span></li>'+
									'<li><span class="label label-warning label-handler freezeup">冻结</span></li>'+
								'</ul>'+
							  '</td>');
					</shiro:hasPermission>
					
					$('#roleBody').append(tr);
				}
			} else {
				alert(result.msg);
			}
		}
	});
}

function getRParam () {
	return {
		'rolename':$('#rname').val(),
		'rolenumber':$('#rnumber').val()
	}
}

$(function () {$('#permManager').on('','.btn-srole',queryRP)});
function queryRP(){doQueryPageObject();};

</script>
<script src="${pageContext.request.contextPath}/assets/static/page/page.js"></script>








