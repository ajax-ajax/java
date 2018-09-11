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
$().ready(function(){
	$("#zhuce").click(function(){
		var word1=$("#password1").val();
		var word2=$("#password2").val();
		reg=/^\w*[A-Z]+\w*$/;
		if(word1==""){
			alert("密码不能为空");
				return false;
			}
		if(word2==""){
				alert("确认密码不能为空");
				return false;
			}
		if(reg.test(word1)==false){
			alert("密码必须包含大写");
			return false;
		}

		if(word1.length<6){
			alert("密码长度过短");
				return false;
		}
		if(word1!=word2){
			alert("两次密码输入不相等");
				return false;
		}
	})
	
})

</script>
<style>
#d1{
width:500px; 
text-align: center;
margin:20px auto;
}88
</style>
</head>
<body>
<div id="d1">
<form action="user?type=doRegister" method="post" class="form-horizontal" role="form">
<div class="form-group">
<div class="col-xs-12">
<input type="text" class="form-control"  name="username" placeholder="请输入账号" value="${name}"/>
</div>
</div>
<div class="form-group">
<div class="col-xs-12">
<input type="password" class="form-control" id="password1" name="password"  placeholder="请输入密码" />
</div>
</div>
<div class="form-group">
<div class="col-xs-12">
<input type="password" class="form-control" id="password2"  name="password"  placeholder="确认密码" />
</div>
</div>

  <input  type="submit" id="zhuce" class="btn btn-primary" value="注册"/>
  </form>
</div>

</body>
</html>