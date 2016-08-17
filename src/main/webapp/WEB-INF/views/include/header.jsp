<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/include/message.jsp"%>
<div class="mdy-toolbar">
  <!-- <img class="logo" alt="logo" src=""> -->
  <div class="loginbar">
    <ul class="list-inline">
      <shiro:guest>
        <li><a class="login" href="${globalURL}/index/login"><spring:message code="login" /></a>|<a href="##"><spring:message code="register" /></a></li>
      </shiro:guest>
      <shiro:authenticated>
        <li><a class="login" href="${globalURL}/index/logout"><spring:message code="logout" /></a></li>
      </shiro:authenticated>
        <li><a class="login" href="https://github.com/shadowsocks/shadowsocks-windows/releases" target="blank"><spring:message code="download" /></a></li>
      <li>
        <div class="dropdown">
          <span class="dropdown-toggle dropdown" id="dropdownMenu" data-toggle="dropdown" aria-expanded="true"><spring:message code="language" /> <span class="caret"></span></span>
          <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
            <li role="presentation"><a class="i18n" data-lan="${globalURL}/index/language/zh" href="##">简体中文</a></li>
            <li role="presentation"><a class="i18n" data-lan="${globalURL}/index/language/en" href="##">English</a></li>
            <li role="presentation"><a class="i18n" data-lan="${globalURL}/index/language/ja" href="##">日本語</a></li>
          </ul>
        </div>
      </li>
    </ul>
  </div>
</div>
