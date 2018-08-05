<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../taglib/indexHeadTag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${fnc:getSysName()}</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"  media="screen,projection" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/static/plugins/jquery-ui/jquery-ui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-table.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/static/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/static/plugins/treegrid/jquery.treegrid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/custom-styles.css"/>
    <script src="${pageContext.request.contextPath}/assets/js/jquery-1.10.2.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap-table.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/static/plugins/jquery-ui/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/static/plugins/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/assets/static/plugins/treegrid/jquery.treegrid.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/static/plugins/treegrid/jquery.treegrid.bootstrap3.js"></script>
	<script src="${pageContext.request.contextPath}/assets/static/plugins/treegrid/jquery.treegrid.extension.js"></script>
	<script src="${pageContext.request.contextPath}/assets/static/plugins/treegrid/tree.table.js"></script>
	<script src="${pageContext.request.contextPath}/assets/static/plugins/ztree/jquery.ztree.all.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.tips.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/custom-scripts.js"></script>
	<script src="${pageContext.request.contextPath}/assets/static/common/common.js"></script>
</head>
<body>
	<div id="wrapper">
		 <!-- 顶部的导航条 -->
		 <%@ include file="headNav.jsp" %>
		 <!-- 左边导航菜单 -->
		 <%@ include file="menu.jsp" %>
		<!-- 内容区 -->
 		<div id="page-wrapper"></div>
	</div>
	<script src="${pageContext.request.contextPath}/assets/static/sys/index.js"></script>
</body>
</html>