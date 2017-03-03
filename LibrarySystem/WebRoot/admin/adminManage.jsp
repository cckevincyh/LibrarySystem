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
            
            <script src="${pageContext.request.contextPath}/ajax-lib/ajaxutils.js"></script>
            <script src="${pageContext.request.contextPath}/js/adminUpdateInfo.js"></script>
             <script src="${pageContext.request.contextPath}/js/adminUpdatePwd.js"></script>
              <script src="${pageContext.request.contextPath}/js/updateAdmin.js"></script>
       
</head>



<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


<body class="bootstrap-admin-with-small-navbar">
    <nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="collapse navbar-collapse main-navbar-collapse">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/admin.jsp"><strong>欢迎使用图书馆管理系统</strong></a>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i> 欢迎您， <s:property value="#session.admin.name"/> <i class="caret"></i></a>
                            
                                 <ul class="dropdown-menu">
                                 <li><a href="#updateinfo" data-toggle="modal">个人资料</a></li>
                                      <li role="presentation" class="divider"></li>
                                    <li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
                                     <li role="presentation" class="divider"></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/adminLoginAction_logout.action">退出</a></li>
                                </ul>
                                
                            </li>
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
                        <a href="/library/admin/book"><i class="glyphicon glyphicon-chevron-right"></i> 图书管理</a>
                    </li>
                    <li>
                        <a href="/library/admin/bookType"><i class="glyphicon glyphicon-chevron-right"></i> 图书分类管理</a>
                    </li>
                    <li >
                        <a href="/library/admin/borrow"><i class="glyphicon glyphicon-chevron-right"></i> 图书借阅</a>
                    </li>
                    <li>
                        <a href="/library/admin/return"><i class="glyphicon glyphicon-chevron-right"></i> 图书归还</a>
                    </li>
                    
                    <li>
                        <a href="/library/admin/borrowInfo"><i class="glyphicon glyphicon-chevron-right"></i> 借阅查询</a>
                    </li>
                     <li>
                        <a href="/library/admin/return"><i class="glyphicon glyphicon-chevron-right"></i> 罚金管理</a>
                    </li>
               <s:if test="#session.admin.type==0"><!-- 对超级管理员和普通管理员进行权限区分 -->
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/admin/adminManageAction_getAllAdmins.action"><i class="glyphicon glyphicon-chevron-right"></i> 管理员管理</a>
                    </li>
               </s:if>
                    <li>
                        <a href="/library/admin/student"><i class="glyphicon glyphicon-chevron-right"></i> 读者管理</a>
                    </li>
                   
                </ul>
            </div>

           <!-- content -->
            <div class="col-md-10">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">查询</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal">
                                    <div class="col-lg-5 form-group">
                                        <label class="col-lg-4 control-label" for="query_ano">管理员编号</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="query_ano" type="text" value="">
                                            <label class="control-label" for="query_ano" style="display: none;"></label>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 form-group">
                                        <label class="col-lg-4 control-label" for="query_aname">管理员名称</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="query_aname" type="text" value="">
                                            <label class="control-label" for="query_aname" style="display: none;"></label>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 form-group">
                                        <button type="button" class="btn btn-primary" id="btn_query" onclick="query()">查询</button>
                                        <button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" data-target="#addModal">添加</button>          
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>管理员用户名</th>
                                <th>管理员姓名</th>
                                <th>联系号码</th>
                                <th>密码</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            
                            
                            <!---在此插入信息-->
                            <s:iterator value="#session.admins" var="admins">
                             <tbody>
	                         	   <td><s:property value="#admins.username"/></td>
	                                <td><s:property value="#admins.name"/></td>
	                                <td><s:property value="#admins.phone"/></td>
	                                <td><s:property value="#admins.pwd"/></td>
	                                <td>
	                                	<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateModal" onclick="updateAdmin(<s:property value="#admins.username"/>)">修改</button>
	                                	<button type="button" class="btn btn-danger btn-xs">删除</button>
	                                		
	                               	</td>                                              
                            </tbody>
                            </s:iterator>
                            
                        </table>
                                   
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
    
    
    
    
    
    
     <!--------------------------------------添加的模糊框------------------------>  
                                 <form class="form-horizontal">   <!--保证样式水平不混乱-->   
                                        <!-- 模态框（Modal） -->
									<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="myModalLabel">
														添加新管理员
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">用户名</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addUsername"  placeholder="请输入管理员用户名">
												
												</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">密码</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" id="addPwd"  placeholder="请输入密码">

											</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">真实姓名</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addName"  placeholder="请输入管理员真实姓名">
												
												</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">联系电话</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="aaPhone"  placeholder="请输入管理员联系电话">
												
												</div>
										</div>
										<!---------------------表单-------------------->
									</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" class="btn btn-primary">
														添加
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>

                                 </form>	
 								<!--------------------------------------添加的模糊框------------------------>  
 
 
 
 
     
                                     <!-- 修改模态框（Modal） -->
                                     <!-------------------------------------------------------------->  
                                
                                        <!-- 修改模态框（Modal） -->
                               <form class="form-horizontal">   <!--保证样式水平不混乱-->   
									<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="updateModalLabel">
														修改管理员信息
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">用户名</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateUsername" readonly="readonly">
												
												</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">真实姓名</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateName"  placeholder="请输入管理员真实姓名">
												
												</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">联系电话</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updatePhone"  placeholder="请输入管理员联系电话">
												
												</div>
										</div>
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">密码</label>
											<div class="col-sm-7">
												<input type="password" class="form-control" id="updatePwd"  placeholder="请输入密码">

											</div>
										</div>
										
										<!---------------------表单-------------------->
															
										</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" class="btn btn-primary" id="updateAdmin">
														修改
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
	
                                 </form>
                                   <!-------------------------------------------------------------->
 
    
    
    
    
 
 
 
 
    
    <!------------------------------修改密码模糊框-------------------------------->  
                 
                   <form class="form-horizontal">   <!--保证样式水平不混乱-->                  
                                     <!-- 模态框（Modal） -->
				<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									修改密码
								</h4>
							</div>
							
							<div class="modal-body">
							 
								<!--正文-->
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">原密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" id="oldPwd"  placeholder="请输入原密码">
												
								</div>
							</div>	
							
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">新密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" id="newPwd"  placeholder="请输入新密码">
												
								</div>
							</div>	
							
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">确认密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" id="confirmPwd"  placeholder="请输入确认密码">
												
								</div>
							</div>	
								<!--正文-->
								
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type="button" class="btn btn-primary" id="update_adminPwd">
									修改
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>

				</form>	
                                   <!-------------------------------------------------------------->
                                   
                                   
                                   
                                   
                                   
                                   
                                   
                                   
                                   
                                   <!-------------------------个人资料模糊框------------------------------------->  
                 
                   <form class="form-horizontal">   <!--保证样式水平不混乱-->                  
                                     <!-- 模态框（Modal） -->
				<div class="modal fade" id="updateinfo" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="ModalLabel">
									个人资料
								</h4>
							</div>
							
							<div class="modal-body">
							 
								<!--正文-->
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">用户名</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="username"  readonly="readonly" value='<s:property value="#session.admin.username"/>'>
												
								</div>
							</div>				
								
								
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">真实姓名</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="name"  placeholder="请输入您的真实姓名" value='<s:property value="#session.admin.name"/>'>
												
								</div>
							</div>	
							
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">联系号码</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="phone"  placeholder="请输入您的联系号码" value='<s:property value="#session.admin.phone"/>'>
												
								</div>
							</div>	
							
								<!--正文-->
								
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type="button" class="btn btn-primary" id="admin_updateInfo" >
									修改
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>

				</form>	
                                   <!-------------------------------------------------------------->
    
    
    
    
				    <div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
				    <div class="modal-dialog" role="document">
				        <div class="modal-content">
				            <div class="modal-header">
				                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				                <h4 class="modal-title" id="infoModalLabel">提示</h4>
				            </div>
				            <div class="modal-body">
				                <div class="row">
				                    <div class="col-lg-12" id="div_info"></div>
				                </div>
				            </div>
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
				            </div>
				        </div>
				    </div>
				</div>
				    
    
 
 
 

 
</body>
</html>
