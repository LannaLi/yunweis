<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../taglib/indexHeadTag.jsp"%>
<div class="header"> 
	<h3 class="page-header">${fnc:getCompanyName()}</h3>
	<ol class="breadcrumb" id="header-nav">
		<li><a>个人中心</a></li>
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
									<form class="form-horizontal" id="userForm" user-id="${user.userid}">
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="用户名:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.username}" id="username">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="密码:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="password" value="${user.password}" id="password">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="姓名:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.realname==null?'':user.realname}" id="realname">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="电话号码:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.phone==null?'':user.phone}" id="phone">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="邮箱:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.email==null?'':user.email}" id="email">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="微信:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.weichat==null?'':user.weichat}" id="weichat">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="QQ:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.qqcode==null?'':user.qqcode}" id="qqcode">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="性别:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.sex==null?'':user.sex}" id="sex">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="出生日期:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.birthdate==null?'':user.birthdate}" id="birthdate">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="是否在职:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.onduty==null?'':user.onduty}" id="onduty" disabled="disabled">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="text" value="负责区域:" disabled="disabled">
											</div>
											<div class="col-sm-2">
												<input type="text" value="${user.belarea==null?'':user.belarea}" id="belarea" disabled="disabled">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-2">
												<input type="button" class="btn btn-info btn-b btn-sure" value="确定">
											</div>
											<div class="col-sm-2">
												<input type="button" class="btn btn-info btn-b btn-back" value="返回">
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
	</div>
</div>							
<script src="${pageContext.request.contextPath}/assets/static/sys/user_edit.js"></script>