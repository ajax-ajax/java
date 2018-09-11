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
	 $("#save").click(function(){
		 var emps="";
		$(".emp").each(function(index,element){
			
			var id=$(this).find("[name=id]").val();
			var name=$(this).find("[name=name]").val();
			var sex=$(this).find("[name=sex]:checked").val();
			var age=$(this).find("[name=age]").val();
			var d_id=$(this).find("[name=depName]").val();
			emps+=id+","+name+","+sex+","+age+","+d_id+";";
		}) 
		emps=emps.substring(0,emps.length-1);
		location.href="emps?type=changeAll2&emps="+emps;
	 })
 })
 </script>
<style>
#main{
width:900px;
margin:20px auto;
}
.emp{
width:400px;
float:left;
margin:20px 50px 10px 0;
border:1px dashed #ccc;
padding:20px 20px 10px 0;
}
#saveBtn{
clear:both;
text-align: center;
}
</style>
</head>
<body>



<div id="main">
<c:forEach items="${list}" var="emp">
<form action="emps" method="post" class="form-horizontal  emp" >

<input type="hidden" name="id" value="${emp.id}"/>
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">姓名</label>
    <div class="col-sm-10">
      <input type="text" class="form-control"  name="name" value="${emp.name }">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">性别</label>
    <div class="col-sm-10">
      <input type="radio"  name="sex" <c:if test="${emp.sex=='男'}">checked</c:if> value="男">男
      <input type="radio" name="sex" <c:if test="${emp.sex=='女'}">checked</c:if> value="女">女
    </div>
  </div>
   <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">年龄</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="age" value="${emp.age }">
    </div>
  </div>
   <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">部门名</label>
    <div class="col-sm-10">
       <select name="depName" class="form-control">
      <option value="">请选择部门</option>
      <c:forEach items="${depList}" var="dep">
     <option value="${dep.id}" <c:if test="${emp.dep.name==dep.name}">selected</c:if>>${dep.name}</option>
      </c:forEach> 
      </select>
    </div>
  </div>
 
</form>
</c:forEach>
  
   <div id="saveBtn">
    
      <button id="save" type="button" class="btn btn-primary">确认</button>
    
  </div>
  </div>

</body>
</html>