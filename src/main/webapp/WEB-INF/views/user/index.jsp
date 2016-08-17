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
      <ul class="nav nav-pills nav-stacked">
        <li role="presentation" class="active"><a href="#"><spring:message code="overview" /></a></li>
        <li role="presentation"><a href="#"><spring:message code="node" /></a></li>
        <li role="presentation"><a href="#"><spring:message code="message" /></a></li>
        <li role="presentation"><a href="#"><spring:message code="security" /></a></li>
      </ul>
    </div>

    <div class="col-sm-4 col-sm-offset-1">
      <table class="table">
        <tr>
          <td>用户名:</td>
          <td></td>
        </tr>
        
        <tr>
          <td>到期时间:</td>
          <td></td>
        </tr>
        <tr>
          <td>流量使用(MB):</td>
          <td>??/??</td>
        </tr>
      </table>
    </div>
    <div class="col-sm-4 col-sm-offset-1">
    <h5>最近公告</h5>
      <ul class="list-unstyled">
        <li></li>
        <li><a href="#">公告</a>&nbsp;&nbsp;&nbsp;&nbsp;2016-08-17</li>
      </ul>
    </div>
  </div>
</body>
<script>
	$(function($) {
  });
</script>
</html>
