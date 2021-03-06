<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">  
<style>
#main{
width:400px;
margin:20px auto;
}
</style>
</head>
<body>
<div id="main">
<form action="emps?type=add" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">

  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">姓名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  name="name" placeholder="请输入姓名">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">性别</label>
    <div class="col-sm-10">
      <input type="radio"  name="sex" value="男">男
      <input type="radio" name="sex" value="女">女
    </div>
  </div>
   <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">年龄</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="age" placeholder="请输入年龄">
    </div>
  </div>
    <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">部门名</label>
    <div class="col-sm-10">
       <select name="depName" class="form-control">
      <option value="">请选择部门</option>
      <c:forEach items="${depList}" var="dep">
     <option value="${dep.id}" >${dep.name}</option>
      </c:forEach> 
      </select>
    </div>
  </div>
  <div class="form-group">
  <div class="col-sm-10">
  <input type="file" value="选择文件" name="myFile"/>
    </div>
 </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <input type="submit" class="btn btn-primary" value="确认"/>
    </div>
  </div>
</form>
</div>
</body>
</html>