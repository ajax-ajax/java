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
		location.href="emps?type=showAdd";
	})
	$("#showAdd2").click(function(){
		location.href="emps?type=showAdd2";
	})
	$("#showChange").click(function(){
		if(selectId>-1){
		location.href="emps?type=showChange&id="+selectId;
		}else{
			alert("请选中一行");
		}
	})
	$("tr").click(function(){
		//$("tr").removeClass("select");		
		$(this).toggleClass("select")
		selectId=$(this).data("id"); 		  
	})
	 
    //给这些单元格注册鼠标点击的事件
    $("tr").dblclick(function(){
        //找到当前鼠标点击的td,this对应的就是响应了click的那个td
        $(this).unbind("dblclick");
        $(this).unbind("click");
        $(this).addClass("changeEmp");
         var id=$(this).children().eq(0).text();
        $(this).children().eq(0).html("<input name='id' type='text' value='"+id+"'/>");
        
         var name=$(this).children().eq(1).text();
        $(this).children().eq(1).html("<input name='name' type='text' value='"+name+"'/>");
        
        var sex=$(this).children().eq(2).text();
        var select="";
    
        if(sex =="男"){
        	
        select="<select name='sex'><option selected value='男'>男</option><option value='女'>女</option></select>"
        }else{
        select="<select name='sex'><option selected value='女'>女</option><option value='男'>男</option></select>"	
        }
    
        $(this).children().eq(2).html(select);

        
         var age=$(this).children().eq(3).text();
        $(this).children().eq(3).html("<input name='age' type='text' value='"+age+"'/>");
        
         var depName=$(this).children().eq(4).text();
      
        $(this).children().eq(4).html(" <select name='depName'><option value=''>请选择部门</option><c:forEach items='${depList}' var='dep'><option value='${dep.id}' <c:if test='${dep.name=="+depName+"}'>selected</c:if>>${dep.name}</option></c:forEach> </select>");
    
       	$("#changeAll3").click(function(){
    		 var emps="";
    		 var array=new Array();
    		 $(".changeEmp").each(function(index,element){
       	 
        	 var id=$(this).data("id");
 			var name=$(this).find("[name=name]").val();
 			var sex=$(this).find("[name=sex]").val();
 			var age=$(this).find("[name=age]").val();
 			var d_id=$(this).find("[name=depName]").val();
   			
 			var emp={
 				id:id,
 				name:name,
 				sex:sex,
 				age:age,
 				d_id:d_id			
 			}
 			array.push(emp);
 			
   			//emps+=id+","+name+","+sex+","+age+","+d_id+";";
   		}) 
        var str=JSON.stringify(array);
    		 str=str.replace(/{/g,"%7b");
    		  str=str.replace(/}/g,"%7d");
   		//emps=emps.substring(0,emps.length-1);
   		
   		location.href="emps?type=changeAll3&emps="+str;
   });  
    });
	
	

	
	
	$("#delete").click(function(){
		if(selectId>-1){
		location.href="emps?type=delete&id="+selectId;
		}else{
			alert("请选中一行");
		}
	})
	function doBatch(type){
		var length=$("#tableId .select").length;
		//var ids="";
		var ids=new Array();
		if(length>0){
			$("#tableId .select").each(function(index,element){
				//ids+=$(this).data("id")+",";
				ids.push($(this).data("id"));
				
			})
			//ids=ids.substring(0,ids.length-1);
			
		location.href="emps?type="+type+"&ids="+ids
		}else{
			alert("请选中一行");
		}
	}
	$("#deleteAll").click(function(){
		doBatch("deleteAll");
		
	})
	$("#changeAll").click(function(){
		doBatch("showChangeAll");
	})
	$("#changeAll2").click(function(){
		doBatch("showChangeAll2");
	})

		   
	$("#shou a").click(function(){
		
		    
		    
		    location.href="emps?ys=1&name=${condition.name}&sex=${condition.sex}&age=${condition.age}&depName=${condition.dep.id}"; 
		    
		   })
     $("#wei a").click(function(){
		
    	
		    location.href="emps?ys=${p.maxPage}&name=${condition.name}&sex=${condition.sex}&age=${condition.age}&depName=${condition.dep.id}"; 
		  
		   })
		   
	 
		$("#img").hover(function(event){
			var photo=$(this).attr("src");
			$("#bigPhoto img").attr("src",photo);
			$("#bigPhoto").show();
			$("#bigPhoto").css({left:event.pageX+10,top:event.pageY+10});
		},function(){
			$("#bigPhoto").hide();
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
#img{
width:50px;
height:50px;
}
#bigPhoto{
display:none;
width:100px;
position:absolute;
}
#bigPhoto img{
width:200px;
height:200px;
}
</style>
</head>
<body>


<div id="main">
  <form action="emps" method="post" class="form-horizontal" role="form">
<input type="hidden" />
  <div class="form-group">
  
    <div class="col-sm-2">
      <input type="text" class="form-control"  name="name" placeholder="姓名" value="${condition.name }">
    </div>
      <div class="col-sm-2">
     <select name="sex" class="form-control">
     <option value="">性别</option>
     <option value="男" <c:if test="${condition.sex=='男'}">selected</c:if>>男</option>
     <option value="女" <c:if test="${condition.sex=='女'}">selected</c:if>>女</option>
     </select>
    </div>
     <div class="col-sm-2">
      <input type="text" class="form-control" name="age" placeholder="年龄" value="${condition.age!=-1?condition.age:'' }">
    </div>
      <div class="col-sm-4">
      <select name="depName" class="form-control">
      <option value="">请选择部门</option>
      <c:forEach items="${depList}" var="dep">
     <option value="${dep.id}" <c:if test="${condition.dep.id==dep.id}">selected</c:if>>${dep.name}</option>
      </c:forEach> 
      </select>
    </div>
     <div class="form-group">  
    <div class=" col-sm-2">
      <button type="submit" class="btn btn-primary">搜索</button>
    </div>
  </div>
  </div>

</form>
<table id="tableId" class="table table-striped table-bordered emp" >
<thead><tr><th>id</th><th>姓名</th><th>性别</th><th>年龄</th><th>部门名</th><th>头像</th><tr/><thead>
<tbody>
<c:forEach items="${list}" var="emp">
<tr class="trClass" data-id="${emp.id}">

<td>${emp.id}</td>  
<td>${emp.name}</td>              
<td>${emp.sex}</td>
<td>${emp.age}</td>
<td>${emp.dep.name}</td>
<td><c:if test="${not empty emp.pic}" > <img id="img" src="pic/${emp.pic}"></c:if></td>
</tr>
</c:forEach>
</tbody>
</table>
<div id="ul">
<ul class="pagination">
     <li id="shou" ><a>首页</a></li>
    <li id="pre" <c:if test="${p.curPage<=1}"> class="disabled"</c:if>><a href="emps?ys=${p.curPage-1}&name=${condition.name}&sex=${condition.sex}&age=${condition.age}&depName=${condition.dep.id}">上一页</a></li>
     
   <c:forEach begin="${p.start}" end="${p.end}" varStatus="status">
    <li id="y" <c:if test="${p.curPage==status.index}"> class="active" </c:if>><a href="emps?ys=${status.index}&name=${condition.name}&sex=${condition.sex}&age=${condition.age}&depName=${condition.dep.id}">${status.index}</a></li>
    </c:forEach>
    <li id="next" <c:if test="${p.curPage>=p.maxPage}"> class="disabled"</c:if>><a href="emps?ys=${p.curPage+1}&name=${condition.name}&sex=${condition.sex}&age=${condition.age}&depName=${condition.dep.id}">下一页</a></li>
    <li id="wei"><a>尾页</a></li>
</ul>
</div>


<div id="button">
<button id="btn" type="button" class="btn btn-primary">添加</button>

<button id="showAdd2" type="button" class="btn btn-primary">添加2</button>

<button id="delete"  class="btn btn-primary">删除</button>


<button id="showChange"  class="btn btn-primary">修改</button>

<button id="deleteAll" class="btn btn-primary">批量删除</button>

<button id="changeAll"  class="btn btn-primary">批量修改</button>

<button id="changeAll2"  class="btn btn-primary">全部修改</button>

<button id="changeAll3"  class="btn btn-primary">批量修改3</button>

</div>
</div>
 <div id="bigPhoto">
 <img src="" />

 </div>

</body>
</html>