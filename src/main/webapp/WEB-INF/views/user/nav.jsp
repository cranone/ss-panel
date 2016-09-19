<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<ul class="nav nav-pills nav-stacked" id="left_nav">
  <li class="active" id="left_overview" role="presentation"><a href="${globalURL}/user/index"><spring:message code="overview" /></a></li>
  <li id="left_node" role="presentation"><a href="${globalURL}/user/node"><spring:message code="node" /></a></li>
  <%-- <li id="left_message" role="presentation"><a href="${globalURL}/user/message"><spring:message code="message" /></a></li>
 --%>
  <li id="left_security" role="presentation"><a href="${globalURL}/user/security"><spring:message code="security" /></a></li>
  <shiro:hasRole name="admin">
    <li><hr></li>
    <li id="left_log" role="presentation"><a href="${globalURL}/admin/log"><spring:message code="log" /></a></li>
  </shiro:hasRole>
</ul>