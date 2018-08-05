<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib/indexHeadTag.jsp"%>
<div class="header"> 
	<h3 class="page-header">${fnc:getCompanyName()}</h3>
	<ol class="breadcrumb" id="header-nav">
		<li><a>数据库管理</a></li>
		<li><a>数据库表管理</a></li>
	</ol> 
</div>
<div id="page-inner pages">
	<div class="dashboard-cards"> 
   		<div class="row">
       		<div class="col-xs-12 col-sm-12 col-md-12" class="contentPage" id="content">
       			<div class="card horizontal cardIcon waves-effect waves-dark">
					<div class="card-stacked orange">
						<div class="card-content">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12">
									<ul class="list-unstyled list-inline left">
										<li class='O2'>
											<button type="button" class="btn btn-info" onclick="create()">添加</button>
											<button type="button" class="btn btn-info" onclick="submit()">提交</button>
										</li>
									</ul>
								</div>
								<div class="col-xs-12 col-sm-12 col-md-12">
									<form class="form-horizontal" id="tables"></form>
								</div>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>					
</div>


<script src="${pageContext.request.contextPath}/assets/static/db/db.js"></script>