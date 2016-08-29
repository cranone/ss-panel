<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<meta HTTP-EQUIV="Expires" CONTENT="0"> 
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
<script src="${globalURL }/resource/project/js/captcha.js"></script>
<style>
.form-inline label {
  width: 100px;
}
</style>
</head>
<body>
<shiro:authenticated>
<script>
location="${globalURL}/user/";
</script>
</shiro:authenticated>
<shiro:guest>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="container">
    <form class="form-horizontal" action="" method="post">
      <div class="form-group">
        <label class="col-sm-2 control-label" for="username"><spring:message code="username" />:</label>
        <div class="col-sm-3">
          <input class="form-control" type="text" id="username" name="username" value="${username }" placeholder="<spring:message code="username.placeholder" />">
        </div>
      </div>
      <div class="form-group">
        <label class="col-sm-2 control-label" for="password"><spring:message code="password" />:</label>
        <div class="col-sm-3">
          <input class="form-control" type="password" id="password" name="password">
        </div>
      </div>
      <c:if test="${captchaEbabled}">
        <div class="form-group">
          <label class="col-sm-2 control-label" for="captchaCode"><spring:message code="captcha" />:</label>
          <div class="col-sm-2">
            <input class="form-control" type="text" id="captchaCode" name="captchaCode">
          </div>
          <img class="col-sm-1" id="captchaimg" src="${globalURL }/images/captcha.jpg" style="height: 30px;min-width: 100px;" title="<spring:message code="captcha.change" />">
          
        </div>
      </c:if>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <input class="btn btn-default" type="submit" value="<spring:message code="login" />"> <span class="error">${error}</span>
        </div>
      </div>

    </form>
  </div>
</shiro:guest>
</body>
</html>
