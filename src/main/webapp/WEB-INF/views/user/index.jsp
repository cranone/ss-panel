<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
</head>
<body>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="row container">
    <div class="col-sm-2">
      <%@include file="/WEB-INF/views/user/nav.jsp"%>
    </div>

    <div class="col-sm-4 col-sm-offset-1">
      <table class="table">
        <tr>
          <td><spring:message code="username" />:</td>
          <td>${user.email }</td>
        </tr>
        <tr>
          <td><spring:message code="user.expires" />:</td>
          <td>/</td>
        </tr>
        <tr>
          <td><spring:message code="user.transfer" />(GB):</td>
          <td><fmt:formatNumber minIntegerDigits="1" value="${(user.upload+user.download)/1024/1024/1024 }" pattern="#.00"/>&nbsp;/&nbsp;<fmt:formatNumber minIntegerDigits="1" value="${user.transferEnable/1024/1024/1024 }" pattern="#.00"/></td>
        </tr>
      </table>
    </div>
    <div class="col-sm-4 col-sm-offset-1">
    <h5><spring:message code="message" /></h5>
      <ul class="list-unstyled">
        <li></li>
        <li><a href="#"><spring:message code="message" /></a>&nbsp;&nbsp;&nbsp;&nbsp;2016-08-17</li>
      </ul>
    </div>
  </div>
</body>
<script>
	$(function($) {
  });
</script>
</html>
