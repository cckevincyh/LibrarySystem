 <%@ page language="java"  pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />  
<title>您有问题~</title>
<style>
*{margin:0;padding:0;outline:none;font-family:\5FAE\8F6F\96C5\9ED1,宋体;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;-khtml-user-select:none;user-select:none;cursor:default;font-weight:lighter;}
.center{margin:0 auto;}
.whole{width:100%;height:100%;line-height:100%;position:fixed;bottom:0;left:0;z-index:-1000;overflow:hidden;}
.whole img{width:100%;height:100%;}
.mask{width:100%;height:100%;position:absolute;top:0;left:0;opacity:0.6;filter:alpha(opacity=60);}
.b{width:100%;text-align:center;height:400px;position:absolute;top:40%;margin-top:-230px}.a{width:150px;height:50px;margin-top:30px}.a a{display:block;float:left;width:150px;height:50px;background:#fff;text-align:center;line-height:50px;font-size:18px;border-radius:25px;color:#333}.a a:hover{color:#000;box-shadow:#fff 0 0 20px}
p{color:#fff;margin-top:40px;font-size:24px;}
#num{margin:0 5px;font-weight:bold;}
</style>
<script type="text/javascript">
	var num=4;
	function redirect(){
		num--;
		document.getElementById("num").innerHTML=num;
		if(num<0){
			document.getElementById("num").innerHTML=0;
			location.href='<c:url value="/admin/admin.jsp"></c:url>';
			}
		}
	setInterval("redirect()", 1000);
</script>
<style type="text/css">
.tb960x90 {display:none!important;display:none}</style>
</head>

<body onLoad="redirect();">
<div class="whole">
	<img src="<c:url value="/img/nopass.png"></c:url>" />
    <div class="mask"></div>
</div>
<div class="b">
		<p>
			抱歉,您拥有的权限不能使用该功能...<br>
            <span id="num"></span>秒后自动跳转到主页
		</p>
	</div>

</body>
</html>

