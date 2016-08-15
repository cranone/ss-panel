<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
<link rel="stylesheet"
  href="${globalURL }/resource/project/css/index.css">
</head>
<body>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="row container">
    <div class="col-xs-3 subcontainer">
      <h1>${param.id},${param.name }</h1>

    </div>
    <div class="col-xs-9 subcontainer">
      <h2>
        <spring:message code="main.title" />
      </h2>
      <!-- <iframe></iframe> -->
    </div>

  </div>
</body>
<script>
	$(function($) {
	  //$.message_confirm('<spring:message code="message.operate"/>');
	  console.log(globalURL);
	  console.log("${globalURL}");
	  $(".login").click(function(){
	  	$(".form-login").message();
	  });
	  /* $.ajax({
	  	type:"post",
	    url:"${globalURL}/ajax",
	    dataType:"json",
	    success:function(data){
	    	console.log(data.info);
	    },
	    complete:function(){
	    	
	    }
	  }); */
  });
</script>
</html>
