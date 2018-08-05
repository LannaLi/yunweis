<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../taglib/indexHeadTag.jsp"%>
<div class="header"> 
	<h3 class="page-header">${fnc:getCompanyName()}</h3>
	<ol class="breadcrumb" id="header-nav">
		<li><a>系统设置</a></li>
		<li><a>用户管理</a></li>
	</ol> 
</div>
<div id="page-inner">
 	<div class="dashboard-cards"> 
   		<div class="row">
       		<div class="col-xs-12 col-sm-12 col-md-12" class="contentPage" id="content">
       			<div class="card horizontal cardIcon waves-effect waves-dark">
					<div class="card-stacked">
						<div class="card-content">
							<form id="menuManager">
								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12">
										<table id="menuTable" data-click-to-select="true"></table>
										<shiro:hasPermission name="sys:menu:add">
										<ul class="list-unstyled list-inline left">
											<li class='O2'>
												<button type="button" class="btn btn-info btn-add">创建菜单或按钮</button>
											</li>
										</ul>
										</shiro:hasPermission>
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
<div id="menuDiv" style="display:none;padding: 0px 0px 0px 0px;">
	<form class="form-horizontal" role="form" id="menuForm">
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="name" class="col-sm-3 control-label">名称:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="name" placeholder="请输入名称" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="url" class="col-sm-3 control-label">路径:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="url" placeholder="请输入路径" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="pid" class="col-sm-3 control-label">上级菜单:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="pid" placeholder="请输入上级菜单" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="code" class="col-sm-3 control-label">标识号:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="code" placeholder="请输入标识号" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="icon" class="col-sm-3 control-label">图标:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="icon" placeholder="请输入图标" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="types" class="col-sm-3 control-label">类型:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="types" placeholder="请输入类型" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="sort" class="col-sm-3 control-label">排序号:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="sort" placeholder="请输入排序号" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
		<div class="form-group" style="margin: 0px 0px 0px 0px;">
			<label for="remark" class="col-sm-3 control-label">备注:</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="remark" placeholder="请输入备注" style="padding: 0px 0px 0px 0px;">
			</div>
		</div>
	</form>
</div>
<div class="layui-layer layui-layer-page layui-layer-molv layer-anim" id="menuLayer" type="page" times="2" showtime="0" contype="object"
		style="z-index:19891016;width:300px;height:450px;top:100px;left:500px;display:none">
	<div class="layui-layer-title" style="cursor: move;">选择菜单</div>
	<div class="layui-layer-content" style="height: 358px;">
		<div style="padding: 10px;" class="layui-layer-wrap">
			<ul id="menuTree" class="ztree"></ul>    <!-- 动态加载树 -->
		</div>
	</div>
	<span class="layui-layer-setwin"> <a class="layui-layer-ico layui-layer-close layui-layer-close1 layer-btn btn_cancle" ></a></span>
	<div class="layui-layer-btn layui-layer-btn-">
		<a class="layui-layer-btn0 layer-btn btn_confirm">确定</a>
		<a class="layui-layer-btn1 layer-btn btn_cancle">取消</a>
	</div>
</div>
<script src="${pageContext.request.contextPath}/assets/static/sys/menu_list.js"></script>

