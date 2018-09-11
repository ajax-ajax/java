<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">  
<script type="text/javascript" src="js/jquery.js" ></script>
<script type="text/javascript">
if(self!=top){
	top.location="user?type=showLogin";
}
$().ready(function(){
	$("#image").click(function(){
		$(this).attr("src","user?type=randomImage&"+Math.random());
	})
}
)
</script>
<style>
#d1{
width:500px;
text-align: center;
margin:20px auto;
}
</style>
</head>
<body>
<div id="d1">
<form action="user?type=doLogin" method="post" class="form-horizontal" role="form">
<div class="form-group">
<div class="col-xs-12">
<input type="text" class="form-control"  name="username" placeholder="请输入账号" value="${name}"/>
</div>
</div>
<div class="form-group">
<div class="col-xs-12">
<input type="password" class="form-control"  name="password"  placeholder="请输入密码" />
</div>
</div>
<div class="form-group">
<div class="col-xs-8">
<input type="text" class="form-control"  name="random"  placeholder="请输入验证码" />
</div>
<div class="col-xs-4">
<img id="image" src="user?type=randomImage"/>
</div>
</div>
<div id="mes" style="height:40px;color:red;">${mes}</div>
  <input  type="submit" class="btn btn-primary" value="登录"/>
  </form>

  
</div>

</body>
</html>