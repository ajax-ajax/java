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
	var flag=1;
	var selectId=-1;
	$(document).on("click","tr",function(){
		$(this).removeClass("select");
		$(this).toggleClass("select");
		selectId=$(this).data("id");
	})
 
	$("#addBtn").bind("click",add);
	
	
	function add(){
		var p_id=$("#p_id").val();
		var i=0;
	
		//location.href="pros?type=addByDep&d_id=${d_id}&p_id="+p_id;
      $.ajax({
    	url:"pros",
    	type:"post",
    	data:{type:"addByDep2",d_id:${d_id},p_id:p_id}, 
    	dataType:"text", 
    	success:function(data){
    		
    		if(data=="true"){
    			var proName="";
    			$("#p_id").children().each(function(index,element){
    				if($(this).val()==p_id){
    					proName=$(this).text();
    					i=index;
    				}
    				
    			})
    			var tr="<tr  data-id="+p_id+"><td>"+p_id+"</td><td>"+proName+"</td></tr>";
    			$("#tableId").append(tr);
    			$("#p_id").children().eq(i).remove();
    			
    			if($("#p_id").children().length==0){
    				flag=0;
    				$("#addBtn").unbind("click");
    				$("#addBtn").addClass("disabled");
    			}
    			  
    		}
    	}
    })
		
	}  
	
	if($("#p_id").children().length==0){
		flag=0;
		$("#addBtn").unbind("click");
		$("#addBtn").addClass("disabled");
	}else{
		flag=1;
	}
	

	$("#deleteBtn").click(function(){
		
		if(selectId>-1){
			$.ajax({
		    	url:"pros",
		    	type:"post",
		    	data:{type:"deleteByDep2",d_id:${d_id},p_id:selectId},
		    	dataType:"text",
		    	success:function(data){
		    		 
		    		if(data=="true"){
		    			var proName="";
		    			$("tr").each(function(index,element){
		    				if($(this).data("id")==selectId){
		    					proName=$(this).children().eq(1).text();
		    					i=index;
		    				}
		    				
		    				
		    			})
		    			var option="<option value="+selectId+">"+proName+"</option>";
		    			$("#p_id").append(option);
		    			$("tr").eq(i).remove();
		    			
		    			if(flag==0){
		    				flag=1;
		    				$("#addBtn").bind("click",add);
		    				$("#addBtn").removeClass("disabled");
		    		     selectId=-1;
		    				
		    			}
		    		}
		    	}
		    })
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
text-align: center;
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

<h1><label>${dName}</label></h1>
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


<div id="button">

 <div  class="col-sm-4">
      <select id="p_id" class="form-control">
      <c:forEach items="${proList}" var="pro">
     <option value="${pro.id}">${pro.name}</option>
      </c:forEach> 
      </select>
   </div>
   
<button  id="addBtn"  class="btn btn-primary col-sm-2" >添加</button> 

<button  id="deleteBtn" class="btn btn-primary col-sm-2">删除</button>

</div>
</div>


</body>
</html>