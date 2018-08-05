<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../taglib/indexHeadTag.jsp"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>${fnc:getSysName()}</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/login/css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/login/css/demo.css" />
	<!--必要样式-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/login/css/component.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/static/common/win.css" />
	<!--[if IE]>
	<script src="js/html5.js"></script>
	<![endif]-->
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h1>${fnc:getSysName()}</h1>
						<form name="f" class="login_form">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="logname" class="text tx_uname" id="username" style="color:#FFFFFF !important" type="text" placeholder="请输入账户"/>
								<i class="fa fa-question-circle" id="u_tips"></i>
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="logpass" class="text tx_pwd" id="passwd" style="color:#FFFFFF !important;position:absolute;z-index:100;"type="password" placeholder="请输入密码"/>
							</div>
							<div class="mb2">
								<a class="act-but submit" style="color:#FFFFFF;cursor:pointer">登录</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="${pageContext.request.contextPath}/assets/js/jquery-1.10.2.js"></script>
		<script src="${pageContext.request.contextPath}/assets/login/js/TweenLite.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/login/js/EasePack.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/login/js/rAF.js"></script>
		<script src="${pageContext.request.contextPath}/assets/login/js/demo-1.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/jquery.tips.js"></script>
		<script src="${pageContext.request.contextPath}/assets/static/common/win.js"></script>
		<script src="${pageContext.request.contextPath}/assets/static/login/login.js"></script>
		<div style="text-align:center;"></div>
		<div class="win win-Info" id="mainWin">
			<div class="win-header" id="mainHead">
				<h3 id="t-header"></h3>
			</div>
			<div class="win-text"   id="mainText">
				<div id="dyText"></div>			
			</div>
			<div class="win-footer" id="mainFoot">
				<input type="button" class="Btn Btn-Info Btn-Close"  value="close"/>
				<input type="button" class="Btn Btn-Info Btn-Sure"   value="sure"/>
			</div>
		</div>
	</body>
</html>