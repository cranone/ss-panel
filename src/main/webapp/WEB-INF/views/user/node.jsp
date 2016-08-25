<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
<script src="${globalURL }/resource/project/js/usernav.js"></script>
</head>
<body>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="row container" id="user_container" data-nav="node">
    <div class="col-sm-2">
      <%@include file="/WEB-INF/views/user/nav.jsp"%>
    </div>

    <div class="col-sm-8 col-sm-offset-1">
      <h3><spring:message code="node.info" /></h3>
      <div>
        <span class="col-sm-3"><label><spring:message code="node.port" />:</label>${user.port }</span>
        <span class="col-sm-3"><label><spring:message code="node.pwd" />:</label>${user.passwd }</span>
        <span class="col-sm-3"><label><spring:message code="node.transfer" />(GB):</label><fmt:formatNumber minIntegerDigits="1" value="${(user.upload+user.download)/1024/1024/1024 }" pattern="#.00"/>&nbsp;/&nbsp;<fmt:formatNumber minIntegerDigits="1" value="${user.transferEnable/1024/1024/1024 }" pattern="#.00"/></span>
      </div>
      <br>
      <h3><spring:message code="node.list" /></h3>
      <table class="table">
        <tr>
          <td><spring:message code="node.position" /></td>
          <td><spring:message code="node.ip" /></td>
          <td><spring:message code="node.status" /></td>
        </tr>
        <tr>
          <td>香港</td>
          <td>47.89.43.227</td>
          <td>正常</td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>
