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
  <div class="row container" id="user_container" data-nav="userlist">
    <div class="col-sm-2">
      <%@include file="/WEB-INF/views/user/nav.jsp"%>
    </div>

    <div class="col-sm-8 col-sm-offset-1">
      <table class="table">
        <tr>
          <td><spring:message code="username" /></td>
          <td><spring:message code="email" /></td>
          <td><spring:message code="user.transfer" />(GB)</td>
        </tr>
        <c:forEach items="${page.list }" var="item">
          <tr>
            <td class="col-sm-2">${item.username }</td>
            <td class="col-sm-2">${item.email }</td>
            <td class="col-sm-2"><fmt:formatNumber minIntegerDigits="1" value="${(item.upload+item.download)/1024/1024/1024 }" pattern="#.00"/>&nbsp;/&nbsp;<fmt:formatNumber minIntegerDigits="1" value="${item.transferEnable/1024/1024/1024 }" pattern="#.00"/></td>
          </tr>
        </c:forEach>
      </table>
      <ul id="pagination" class="pagination-sm"></ul>
    </div>
  </div>
</body>
<script>
	$(function($) {
		var startPage="${page.currentPage}"*1;
	  $('#pagination').twbsPagination({
	    totalPages : "${page.totalPage}",
	    startPage : startPage,
	    visiblePages : 10,
	    first : '<spring:message code="page.first" />',
	    last : '<spring:message code="page.last" />',
	    prev : null,
	    next : null,
	    href : "${globalURL }/admin/userlist?currentPage={{number}}",
	    onPageClick : function(event, page) {
		    $('#page-content').text('Page ' + page);
	    }
	  });
  });
</script>
</html>
