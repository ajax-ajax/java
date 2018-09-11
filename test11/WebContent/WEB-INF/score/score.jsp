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
	$("#depoption").change(function{
		
		alert(1);
	})
	
	
	
	
	$("tr").dblclick(function(){		
		$(this).addClass("changeValue");
		 var value=$(this).children().eq(3).text();
		 $(this).children().eq(3).html("<input name='value' id='value' type='text' value='"+value+"'/>");		
		 $("#value").blur(function(){	
				var id=$(".changeValue").data("id");
				var e_id=$(".changeValue").data("e_id");
				var p_id=$(".changeValue").data("p_id");
				var value=$(".changeValue").find("[name=value]").val();		
				
		  $.ajax({
		    	url:"score",
		    	type:"post",
		    	data:{type:"input",e_id:e_id,p_id:p_id,id:id,value:value}, 
		    	dataType:"json", 
		    	success:function(data){	
		    		
		    		$(".changeValue").children().eq(3).html(data.value);
		    		$(".changeValue").children().eq(4).html(data.grade);
		    		$(".changeValue").data("id","data.id");
		    	}
		 })
		
		 }) 		 
	})
	
	
	
	$("#shou a").click(function(){ 
	    location.href="score?ys=1&name=${condition.emp.name}&depId=${condition.emp.dep.id}&proId=${condition.pro.id}&value=${condition.value}&grade=${condition.grade}"; 	    
	   })
 $("#wei a").click(function(){
	    location.href="score?ys=${p.maxPage}&name=${condition.emp.name}&depId=${condition.emp.dep.id}&proId=${condition.pro.id}&value=${condition.value}&grade=${condition.grade}";
	  
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


<div id="main">
  <form action="score" method="post" class="form-horizontal" role="form">
  <div class="form-group">
  <div class="col-sm-2">
      <input type="text" class="form-control"  name="name" placeholder="姓名" value="${condition.emp.name }">
    </div>
   <div class="col-sm-2">
      <select id="depoption" name="depId" class="form-control">
      <option value="">部门</option>
      <c:forEach items="${depList}" var="dep">
     <option value="${dep.id}" <c:if test="${condition.emp.dep.id==dep.id}">selected</c:if>>${dep.name}</option>
      </c:forEach> 
      </select>
    </div>
    
       <div class="col-sm-2">
      <select name="proId" class="form-control">
      <option value="">项目</option>
      <c:forEach items="${proList}" var="pro">
     <option value="${pro.id}" <c:if test="${condition.pro.id==pro.id}">selected</c:if>>${pro.name}</option>
      </c:forEach> 
      </select>
    </div>
  
     <div class="col-sm-2">
      <input type="text" class="form-control" name="value" placeholder="成绩" value="${condition.value!=-1?condition.value:''}">
    </div>
    
    <div class="col-sm-2">
      <input type="text" class="form-control" name="grade" placeholder="等级" value="${condition.grade}">
    </div>
    
    
     <div class="form-group">  
    <div class=" col-sm-2">
      <button type="submit" class="btn btn-primary">搜索</button>
    </div>
  </div>
  </div>

</form>
<table id="tableId" class="table table-striped table-bordered dep" >
<thead><tr><th>员工姓名</th><th>部门</th><th>项目名</th><th>成绩</th><th>等级</th><tr/><thead>
<tbody>
<c:forEach items="${scList}" var="sc">
<tr class="trClass" data-id="${sc.id }"data-e_id="${sc.emp.id}" data-p_id="${sc.pro.id}">

<td>${sc.emp.name}</td>  
<td>${sc.emp.dep.name}</td>              
<td>${sc.pro.name}</td>
<td>${sc.value}</td>
<td>${sc.grade}</td>
</tr>
</c:forEach>
</tbody>
</table>
<div id="ul">
<ul class="pagination">
      <li id="shou" ><a>首页</a></li>
    <li id="pre" <c:if test="${p.curPage<=1}"> class="disabled"</c:if>><a href="score?ys=${p.curPage-1}&name=${condition.emp.name}&depId=${condition.emp.dep.id}&proId=${condition.pro.id}&value=${condition.value!=-1?condition.value:''}&grade=${condition.grade}">上一页</a></li>
     
   <c:forEach begin="${p.start}" end="${p.end}" varStatus="status">
    <li id="y" <c:if test="${p.curPage==status.index}"> class="active" </c:if>><a href="score?ys=${status.index}&name=${condition.emp.name}&depId=${condition.emp.dep.id}&proId=${condition.pro.id}&value=${condition.value!=-1?condition.value:''}&grade=${condition.grade}">${status.index}</a></li>
    </c:forEach>
    <li id="next" <c:if test="${p.curPage>=p.maxPage}"> class="disabled"</c:if>><a href="score?ys=${p.curPage+1}&name=${condition.emp.name}&depId=${condition.emp.dep.id}&proId=${condition.pro.id}&value=${condition.value!=-1?condition.value:''}&grade=${condition.grade}">下一页</a></li>
    <li id="wei"><a>尾页</a></li>
</ul>
</div>

</div>


</body>
</html>