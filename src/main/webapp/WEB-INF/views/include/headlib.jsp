<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<title><spring:message code="title" /></title>
<link rel="stylesheet" href="${resourceURL }/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="${resourceURL }/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${resourceURL }/jQuery-Validation-Engine/2.6.4/validationEngine.jquery.min.css">
<link rel="stylesheet" href="${resourceURL }/limonte-sweetalert2/6.4.2/sweetalert2.min.css">
<link rel="stylesheet" href="${resourceURL }/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css">
<link rel="stylesheet" href="${resourceURL }/bootstrap-table/1.11.1/bootstrap-table.min.css">

<script src="${resourceURL }/jquery/1.11.3/jquery.min.js"></script>
<script src="${resourceURL }/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="${resourceURL }/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="${resourceURL }/jQuery-Validation-Engine/2.6.4/jquery.validationEngine.js"></script>
<script src="${resourceURL }/jQuery-Validation-Engine/2.6.4/languages/jquery.validationEngine-${cookie.locale.value==null?'zh_CN':cookie.locale.value}.min.js"></script>
<script src="${resourceURL }/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="${resourceURL }/limonte-sweetalert2/6.4.2/sweetalert2.min.js"></script>
<script src="${resourceURL }/twbs-pagination/1.3.1/jquery.twbsPagination.js"></script>
<script src="${resourceURL }/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script src="${resourceURL }/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="${resourceURL }/bootstrap-table/1.11.1/extensions/editable/bootstrap-table-editable.js"></script>
<script src="${resourceURL }/bootstrap-table/1.11.1/bootstrap-table-locale-all.min.js"></script>

<link rel="stylesheet" href="${globalURL }/resource/project/css/global.css">
<link rel="stylesheet" href="${globalURL }/resource/project/css/header.css">

<script src="${globalURL }/resource/project/js/global.js"></script>
