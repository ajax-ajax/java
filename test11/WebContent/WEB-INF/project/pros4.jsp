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
 <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script type="text/javascript">
$().ready(function(){
	$(".pro").click(function(){
		$(this).toggleClass("select");
	})
	
	$("#add").click(function(){
		var length=$("#noPro").find(".select").length;
		var p_ids="";
		if(length>0){
			$("#noPro").find(".select").each(function(index,element){
				p_ids+=$(this).data("id")+",";
		
			})
			
		 $.ajax({
		    	url:"pros",
		    	type:"post",
		    	data:{type:"addByDep3",d_id:${d_id},p_ids:p_ids}, 
		    	dataType:"text", 
		    	success:function(data){
		    		if(data=="true"){
		    			var pro=$("#noPro").find(".select");
		    			pro.removeClass("select");
		    			$("#pro").append(pro);
		    		}
		    	}
		 })
	}else{
		alert("请选择数据");
	}
	})
	
	
	$("#delete").click(function(){
		var length=$("#pro").find(".select").length;
		var p_ids="";
		if(length>0){
			$("#pro").find(".select").each(function(index,element){
				p_ids+=$(this).data("id")+",";
		
			})

		 $.ajax({
		    	url:"pros",
		    	type:"post",
		    	data:{type:"deleteByDep3",d_id:${d_id},p_ids:p_ids}, 
		    	dataType:"text", 
		    	success:function(data){
		    		if(data=="true"){
		    			var pro=$("#pro").find(".select");
		    			pro.removeClass("select");
		    			$("#noPro").append(pro);
		    		}
    		
		    	}
		 })
	}else{
		alert("请选择数据");
	}
	})
	
	
	var proLeft=$("#pro").offset().left;
	var proTop=$("#pro").offset().top;
	var proWidth=parseFloat($("#pro").css("width"));
	var proHeight=parseFloat($("#pro").css("height"));
	var noproLeft=$("#noPro").offset().left;
	var noproTop=$("#noPro").offset().top;
	var noproWidth=parseFloat($("#noPro").css("width"));
	var noproHeight=parseFloat($("#noPro").css("height"));
	var startLeft;
	var startTop;
$( ".pro" ).draggable({
	 start: function() {
		    startLeft=$(this).offset().left;
	    	 startTop=$(this).offset().top;
	       },

	      stop: function() {
	    	 stopLeft=$(this).offset().left;
	    	 stopTop=$(this).offset().top;
	    	  
	    	 if(startLeft>=noproLeft&&startLeft<=noproLeft+noproWidth&&startTop>=noproTop&&startTop<=noproTop+noproHeight){
	    	 
	    	 
	    	 if(stopLeft>=proLeft&&stopLeft<=proLeft+proWidth&&stopTop>=proTop&&stopTop<=proTop+proHeight){
	    		var pro=$(this);
	    		 var proId=$(this).data("id");
	    		
	 			$.ajax({
	 				url:"pros",
	 				type:"post",
	 				 data:{ type:"addByDep2",d_id:${d_id},p_id:proId},
	 			dataType:"text",
	 			success:function(data){
	 				if(data=="true"){
	 					pro.css("position","static");
	 					$("#pro").append(pro);
	 					pro.css("position","relative");
	 					pro.css("left","0");
	 					pro.css("top","0");
	 				}
	 					
	 				}
	 			})
	    		 
	    	 }else{
	    	
	    		 $(this).offset({left:startLeft,top:startTop})
	    	 }
	    	 }else if(startLeft>=proLeft&&startLeft<=proLeft+proWidth&&startTop>=proTop&&startTop<=proTop+proHeight){
	    		 
	    		 if(stopLeft>=noproLeft&&stopLeft<=noproLeft+noproWidth&&stopTop>=noproTop&&stopTop<=noproTop+noproHeight){
	 	    		var nopro=$(this);
	 	    		 var noproId=$(this).data("id");
	 	 			$.ajax({
	 	 				url:"pros",
	 	 				type:"post",
	 	 				 data:{ type:"deleteByDep2",d_id:${d_id},p_id:noproId},
	 	 			dataType:"text",
	 	 			success:function(data){
	 	 				if(data=="true"){
	 	 					nopro.css("position","static");
	 	 					$("#noPro").append(nopro);
	 	 					nopro.css("position","relative");
	 	 				nopro.css("left","0");
	 	 					nopro.css("top","0");
	 	 				}
	 	 					
	 	 				}
	 	 			})
	 	    		 
	 	    	 }else{
	 	    		 $(this).offset({left:nostartLeft,top:nostartTop})
	 	    	 }

	    	 }
	    }
	    });

	
	
	
	
})



</script>

<style>

#main{
width:550px;
margin:20px auto;
text-align: center;
}
#pro,#noPro{
width:550px;
height:200px;
border:1px solid #337ab7;
boder-radius:3px;

}
#btn{
width:250px;
margin:20px auto;
}
#add{
margin-right: 50px;
}
.pro{
 background:#337ab7; 
 height:30px;
 line-height:30px;
 float:left;
 margin-left:10px;
 color:#fff;
 padding: 0 20px;
 margin-top: 10px;
 border-radius: 3px;
}
.select{
background: #d9534f;
}
</style>
</head>
<body>
<div>

<div id="main">

<h1><label>${dName}</label></h1>
<div id="pro">
<c:forEach items="${pros}" var="pro">
<div class="pro" data-id="${pro.id}">${pro.name}</div>  
</c:forEach>
</div>
<div id="btn">
<button id="add"  class="btn btn-primary">上</button>
<button id="delete"  class="btn btn-primary">下</button>
</div>
<div id="noPro">
<c:forEach items="${proList}" var="pro">
<div class="pro" data-id="${pro.id}">${pro.name}</div>  
</c:forEach></div>
</div>


</body>
</html>