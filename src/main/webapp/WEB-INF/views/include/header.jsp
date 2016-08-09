<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/include/message.jsp"%>
<div class="mdy-toolbar">
  <!-- <img class="logo" alt="logo" src=""> -->
  <div class="loginbar">
    <ul class="list-inline">
      <li><a class="login" href="javascript:void(0)"><spring:message code="login" /></a>|<a href="##"><spring:message code="register" /></a></li>
      <li><a href="##">search</a></li>
      <li>
        <div class="dropdown">
          <span class="dropdown-toggle dropdown white" id="dropdownMenu" data-toggle="dropdown" aria-expanded="true"><spring:message code="language" /> <span class="caret"></span></span>
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
<form class="modal form-login" role="alertdialog">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title">
          <spring:message code="login" />
        </h4>
      </div>

      <div class="modal-body">
        <div class="input-group">
          <span class="input-group-addon" id="username"><spring:message code="username.title" /></span> <input type="text" class="form-control" placeholder='<spring:message code="username.title" />' aria-describedby="username">
        </div>
        <div class="input-group">
          <span class="input-group-addon" id="password"><spring:message code="password.title" /></span> <input type="text" class="form-control" placeholder='<spring:message code="password.title" />' aria-describedby="password">
        </div>
        <div class="input-group">
          <span class="input-group-addon" id="verifycode"><spring:message code="verifycode.title" /></span> <input type="text" class="form-control verifycode" placeholder='<spring:message code="verifycode.title" />' aria-describedby="verifycode">
        </div>
      </div>

      <div class="modal-footer">
        <button class="btn btn-default ok"><spring:message code="user.login" /></button>
        <button class="btn btn-default"  data-dismiss="modal"><spring:message code="message.cancel" /></button>
      </div>
    </div>
  </div>
</form>