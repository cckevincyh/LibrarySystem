<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"   prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
	<meta charset="UTF-8">
	<title>图书馆管理系统</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-admin-theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-admin-theme.css">
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/jQuery/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap-dropdown.min.js"></script>
              <script src="${pageContext.request.contextPath}/js/reader.js"></script>

</head>



<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


<body class="bootstrap-admin-with-small-navbar">
<nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <a class="navbar-brand" href="#"><strong>欢迎使用图书馆管理系统</strong></a>
                    <ul class="nav navbar-nav navbar-right">
                    <s:if test="#session.reader.name!=null">
                        <li class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i>     欢迎您，<s:property value="#session.reader.name"/><i class="caret"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="/library/student/student">个人资料</a></li>
                                 <li role="presentation" class="divider"></li>
                                <li><a href="/library/logout">修改密码</a></li>
                                <li role="presentation" class="divider"></li>
                                <li><a href="${pageContext.request.contextPath}/reader/readerLoginAction_logout.action">退出</a></li>
                            </ul>
                        </li>
                        </s:if>
                        <s:else>
                        	<button type="button" class="btn btn-default btn-sm "  id="btn_login" style="margin: 10" data-dismiss="modal">登录</button>
                        </s:else>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">
        <!-- left, vertical navbar -->
        <div class="col-md-2 bootstrap-admin-col-left">
            <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
                <li>
                    <a href="/library/student/book"><i class="glyphicon glyphicon-chevron-right"></i> 图书查询</a>
                </li>
                <s:if test="#session.reader.name!=null">
	                <li>
	                    <a href="/library/student/borrowInfo"><i class="glyphicon glyphicon-chevron-right"></i> 借阅信息</a>
	                </li>
                </s:if>
                
                
                    
                
            </ul>
        </div>

        <!-- content -->
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">图书查询</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>根据图书编号、图书名称查询图书信息</li>
                                <li>可查询图书的编号、名称、分类、作者、价格、在馆数量等</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        <s:if test="#session.reader.name!=null">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">借阅信息</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>根据图书编号、图书名称查询自己借阅的图书信息</li>
                                <li>可查询除图书的基本信息、借阅日期、截止还书日期、超期天数等</li>
                            </ul>
                        </div>
                    </div>
                </div>
                         
                
            </div>
          </s:if> 
            
        </div>
    </div>
</div>


</body>
</html>