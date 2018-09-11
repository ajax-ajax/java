<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">  

<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.js" ></script>
<script type="text/javascript">
$().ready(function(){
	
	var selectId=-1;

	$("#btn").click(function(){
		location.href="deps?type=showAdd";
	})
	$("#showChange").click(function(){
		if(selectId>-1){
		location.href="deps?type=showChange&id="+selectId;
		}else{
			alert("请选中一行");
		}
	})
	
	$("#showByDep2").click(function(){
		if(selectId>-1){
		location.href="pros?type=showByDep2&d_id="+selectId;
		}else{
			alert("请选中一行");
		}
	})
	$("#showByDep3").click(function(){
		if(selectId>-1){
		location.href="pros?type=showByDep3&d_id="+selectId;
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
		location.href="deps?type=delete&id="+selectId;
		}else{
			alert("请选中一行");
		}
	})
	 
	
	$("#btn3").click(function(){
		if(selectId>-1){
			
			$(this).attr("data-toggle","modal");
			$(this).attr("data-target","#myModal");
			$("#right").attr("src","pros?type=showByDep3&d_id="+selectId);
			
			
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
#right{
width:550px;
height:600px;
}

</style>
</head>
<body>


<div id="main">
  <form action="deps" method="post" class="form-horizontal" role="form">
<input type="hidden" />
  <div class="form-group">
  
    <div class="col-sm-4">
      <input type="text" class="form-control"  name="name" placeholder="请输入部门名" value="${condition.name }">
    </div>
  
     <div class="col-sm-4">
      <input type="text" class="form-control" name="emp_count" placeholder="请输入员工数量" value="${condition.emp_count!=-1?condition.emp_count:'' }">
    </div>
     <div class="form-group">  
    <div class=" col-sm-4">
      <button type="submit" class="btn btn-primary">搜索</button>
    </div>
  </div>
  </div>

</form>
<table id="tableId" class="table table-striped table-bordered dep" >
<thead><tr><th>id</th><th>部门名</th><th>员工数量</th><tr/><thead>
<tbody>
<c:forEach items="${deps}" var="dep">
<tr class="trClass" data-id="${dep.id}">

<td>${dep.id}</td>  
<td><a href="pros?type=showByDep&d_id=${dep.id}">${dep.name}</a></td>              
<td><a href="emps?depName=${dep.id}">${dep.emp_count}</a></td>
</tr>
</c:forEach>
</tbody>
</table>
<div id="ul">
<ul class="pagination">
     
    <li id="pre" <c:if test="${p.curPage<=1}"> class="disabled"</c:if>><a href="deps?ys=${p.curPage-1}&name=${condition.name}&emp_count=${condition.emp_count}">上一页</a></li>
     
   <c:forEach begin="${p.start}" end="${p.end}" varStatus="status">
    <li id="y" <c:if test="${p.curPage==status.index}"> class="active" </c:if>><a href="deps?ys=${status.index}&name=${condition.name}&emp_count=${condition.emp_count}">${status.index}</a></li>
    </c:forEach>
    <li id="next" <c:if test="${p.curPage>=p.maxPage}"> class="disabled"</c:if>><a href="deps?ys=${p.curPage+1}&name=${condition.name}&emp_count=${condition.emp_count}">下一页</a></li>
    
</ul>
</div>


<div id="button">
<button id="btn" type="button" class="btn btn-primary">添加</button>



<button id="delete"  class="btn btn-primary">删除</button>


<button id="showChange"  class="btn btn-primary">修改</button>

<button id="showByDep2"  class="btn btn-primary">管理项目</button>
<button id="showByDep3"  class="btn btn-primary">管理项目2</button>
<button id="btn3" class="btn btn-primary" >
	管理项目3
</button>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel"></h4>
					</div>
					<div class="modal-body">
						<iframe id="right"  scrolling="no" frameborder="0"> 
				
						</iframe>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

</div>
</div>
</div>


</body>
</html>