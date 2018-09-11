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
	
	var selectId=-1;

	$("#btn").click(function(){
		location.href="pros?type=showAdd";
	})
	$("#showChange").click(function(){
		if(selectId>-1){
		location.href="pros?type=showChange&id="+selectId;
		}else{
			alert("请选中一行");
		}
	})
	$("tr").click(function(){
		$("tr").removeClass("select");
		$(this).toggleClass("select");
		selectId=$(this).data("id");
	})
	 
    //给这些单元格注册鼠标点击的事件


	$("#delete").click(function(){
		if(selectId>-1){
		location.href="pros?type=delete&id="+selectId;
		}else{
			alert("请选中一行");
		}
	})
	 
		
})
		 
   


</script>

<style>

#main{
width:600px;
margin:20px auto;
}
#tableId .select{
background:#337ab7;
}
#tableId td{
width:200px;
}
#tableId input{
width:100px;
}
#tableId select{
width:100px;
height:25px;
}
li{
cursor: pointer;
}
#ul{
width:600px;
text-align: center;
}
#button{
width:600px;
text-align: center;
}


</style>
</head>
<body>
<div>

<div id="main">
  <form action="pros" method="post" class="form-horizontal" role="form">
<input type="hidden" />
  <div class="form-group">
  
    <div class="col-sm-6">
      <input type="text" class="form-control"  name="name" placeholder="请输入项目名" value="${condition.name }">
    </div>
  
    
     <div class="form-group">  
    <div class=" col-sm-6">
      <button type="submit" class="btn btn-primary">搜索</button>
    </div>
  </div>
  </div>

</form>
<table id="tableId" class="table table-striped table-bordered pro" >
<thead><tr><th>id</th><th>项目名</th><tr/><thead>
<tbody>
<c:forEach items="${pros}" var="pro">
<tr class="trClass" data-id="${pro.id}">

<td>${pro.id}</td>  
<td>${pro.name}</td>              

</tr>
</c:forEach>
</tbody>
</table>
<div id="ul">
<ul class="pagination">
     
    <li id="pre" <c:if test="${p.curPage<=1}"> class="disabled"</c:if>><a href="pros?ys=${p.curPage-1}&name=${condition.name}">上一页</a></li>
     
   <c:forEach begin="${p.start}" end="${p.end}" varStatus="status">
    <li id="y" <c:if test="${p.curPage==status.index}"> class="active" </c:if>><a href="pros?ys=${status.index}&name=${condition.name}">${status.index}</a></li>
    </c:forEach>
    <li id="next" <c:if test="${p.curPage>=p.maxPage}"> class="disabled"</c:if>><a href="pros?ys=${p.curPage+1}&name=${condition.name}">下一页</a></li>
    
</ul>
</div>


<div id="button">
<button id="btn" type="button" class="btn btn-primary">添加</button>



<button id="delete"  class="btn btn-primary">删除</button>


<button id="showChange"  class="btn btn-primary">修改</button>





</div>
</div>


</body>
</html>