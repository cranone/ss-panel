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

    <div class="col-sm-9 col-sm-offset-1 row">
      <h3><spring:message code="node.info" /></h3>
      <div>
        <span class="col-sm-4"><label><spring:message code="node.port" />:</label>${user.port }</span>
        <span class="col-sm-4"><label><spring:message code="node.pwd" />:</label>${user.passwd }</span>
        <span class="col-sm-4"><label><spring:message code="node.transfer" />(GB):</label><fmt:formatNumber minIntegerDigits="1" value="${(user.upload+user.download)/1024/1024/1024 }" pattern="#.00"/>&nbsp;/&nbsp;<fmt:formatNumber minIntegerDigits="1" value="${user.transferEnable/1024/1024/1024 }" pattern="#.00"/></span>
      </div>
      <div>
        <span class="col-sm-4"><label><spring:message code="node.method" />:</label>aes-256-cfb</span>
        <span class="col-sm-4"><label><spring:message code="node.protocol" />:</label>auth_sha1_v4_compatible</span>
        <span class="col-sm-4"><label><spring:message code="node.obfs" />:</label>tls1.2_ticket_auth_compatible</span>
      </div>
      <br>
      <h3><spring:message code="node.list" /></h3>
      <table id="list"></table>
    </div>
  </div>
</body>
<script>
$(function($) {
	$.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales["${cookie.locale.value==null?'zh_CN':cookie.locale.value}".replace("_","-")]);
	$("#list").bootstrapTable({
    idField: "id",
    pagination: true,
    sidePagination: "server",
    search: false,
    clickToSelect: false,
    method: "post",
    contentType:"application/x-www-form-urlencoded",
    pageNumber:1,
    pageSize: 10,
    smartDisplay:false,
    pageList: [10, 25, 50, 100],
    queryParams:function(params){
      return {
        currentPage:params.offset,
        sizePage:params.limit
      };
    },
    url: "${globalURL }/user/nodelistajax",
    columns: [{
    	field: "name",
      title: "<spring:message code='node.name' />"
    },{
      field: "addr",
      title: "<spring:message code='node.ip' />"
    },{
      field: "info",
      title: "<spring:message code='node.status' />"
    },{
      field: "trafficRate",
      title: "<spring:message code='node.rate' />"
    }]
	});
});
</script>
</html>
