<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
<style>
.form-inline label {
  width: 100px;
}
</style>
</head>
<body>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="container">
    <form class="form-horizontal" action="" method="post">
      <div class="form-group">
        <label class="col-sm-2 control-label" for="username">用户名:</label>
        <div class="col-sm-3">
          <input class="form-control" type="text" id="username" name="username">
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label" for="password">密码:</label>
        <div class="col-sm-3">
          <input class="form-control" type="password" id="password" name="password">
        </div>
      </div>
      <c:if test="${captchaEbabled}">
        <div class="form-group">
          <label class="col-sm-2 control-label" for="captchaCode">验证码:</label>
          <div class="col-sm-2">
            <input class="form-control" type="text" id="captchaCode" name="captchaCode">
          </div>
          <img class="col-sm-1 captchaimg" src="${globalURL }/images/captcha.jpg" style="height: 30px;" title="点击更换验证码">
          
        </div>
      </c:if>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <input class="btn btn-default" type="submit" value="<spring:message code="login" />"> <span class="error">${error}</span>
        </div>
      </div>

    </form>
  </div>
</body>
<script>
	$(function($) {
	  $(".captchaimg").click(function(){
	  	$(this).attr("src","${globalURL }/images/captcha.jpg?"+new Date().getTime());
	  });
  });
</script>
</html>
