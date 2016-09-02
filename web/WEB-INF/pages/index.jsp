<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
    	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />	
    	<link rel="stylesheet" type="text/css" href="/static/base/bootstrap-3.3.5-dist/css/bootstrap.min.css"/>
    	<link rel="stylesheet" type="text/css" href="/static/base/bootstrap-3.3.5-dist/css/bootstrap-cerulean.min.css"/>
		<link rel="stylesheet" type="text/css" href="/static/base/bootstrap-3.3.5-dist/css/bootstrap.table.css"/>
		<link rel="stylesheet" type="text/css" href="/static/base/kindeditor/themes/default/default.css"> 
		
		<!-- 页面样式 -->
		<link rel="stylesheet" type="text/css" href="/static/css/index.css"/>
		<script src="/static/base/bootstrap-3.3.5-dist/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/static/base/bootstrap-3.3.5-dist/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/static/base/bootstrap-3.3.5-dist/js/bootstrap.table.js" type="text/javascript" charset="utf-8"></script>
		<script src="/static/base/bootstrap-3.3.5-dist/js/bootstrap.table.zh.js" type="text/javascript" charset="utf-8"></script>
		<script src="/static/base/kindeditor/kindeditor-min.js" type="text/javascript" charset="utf-8"></script>
		<!-- 页面样式 -->
		<script src="/static/js/index.js" type="text/javascript" charset="utf-8"></script>
		<script src="/static/base/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
		
	</head>
	<body >
		<div class="row" >
			<div class="col-xs-12" id="webTop">
				<span id="topLeft">
					<a href="http://baidu.com"><p>美都教育</p></a>
					<span id="webTimer">  </span>
				</span>
				<span id="topRight">
					<div id="userInfo" class="dropdown">
						<button id="dropdownMenu2" class=" dropdown-toggle " data-toggle="dropdown" ><p class="glyphicon glyphicon-user"> 超级管理员</p></button>
						<ul class="dropdown-menu  pull-right" role="menu">
					      <li role="presentation" >
					         <a role="menuitem" tabindex="-1" href="#">个人资料</a>
					      </li>
					      <li role="presentation">
					         <a role="menuitem" tabindex="-1" href="#">修改密码</a>
					      </li>
					      <li role="presentation">
					         <a role="menuitem" tabindex="-1" href="#">注销</a>
					      </li>
					   </ul>
					</div>
				</span>
				
			</div>
			<div class="col-xs-1" id="webNav">
				<div id="menuList" class="sidebar-nav navbar-collapse">
					<ul id="side-menu" class="nav in" style="display: block;">
						<li class="active" style="background-color: #003147;">
							<a style="color:#ffffff;background-color:#0099CC;" href="javaScript:void(location.reload());">首页</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-xs-11" id="webContent">
				
					
			</div>
		</div>
	</body>
</html>
