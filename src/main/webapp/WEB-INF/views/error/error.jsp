<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@include file="/WEB-INF/views/include/headlib.jsp"%>
    
  </head>
  <body>
    <h1><spring:message code="error" />!${param.code }</h1>
  </body>
  <script>
    $(function($) {
      console.log(globalURL);
    });
  </script>
</html>
