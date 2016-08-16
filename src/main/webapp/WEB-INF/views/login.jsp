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
    <h2>this is LOGIN!!!</h2>
    <div class="error">${error}</div>
    <form action="" method="post">
      <span>用户名:</span><input type="text" name="username">
      <br>
      <span>密码:</span><input type="password" name="password">
      <br>
      <input type="submit" value="登录">
    </form>
  </div>
</body>
<script>
	$(function($) {

  });
</script>
</html>
