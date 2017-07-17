<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container ">
    <div class="navbar-header">
      <a class="navbar-brand" href="${globalURL}/index">Shadow</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <shiro:guest>
          <li><a href="${globalURL}/index/login"><spring:message code="login" /></a></li>
          <li><a href="##"><spring:message code="register" /></a></li>
        </shiro:guest>
        <shiro:authenticated>
          <li><a href="${globalURL}/user/index"><shiro:principal/></a></li>
          <li><a href="${globalURL}/index/logout"><spring:message code="logout" /></a></li>
        </shiro:authenticated>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message
              code="download" /> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a class="login" href="https://github.com/shadowsocks/shadowsocks-windows/releases" target="blank"><spring:message code="download.client" /></a></li>
            <li><a class="login" href="https://github.com/Deathencyclopedia/ss-panel" target="blank"><spring:message code="download.server" /></a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message
              code="language" /> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a class="i18n" data-lan="${globalURL}/index/language/zh_CN" href="#">简体中文</a></li>
            <li><a class="i18n" data-lan="${globalURL}/index/language/en" href="#">English</a></li>
            <li><a class="i18n" data-lan="${globalURL}/index/language/ja" href="#">日本語</a></li>
          </ul></li>
      </ul>
    </div>
  </div>
</nav>