<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
<script src="${globalURL }/resource/project/js/usernav.js"></script>
<style type="text/css">
.table{
    table-layout: fixed;
}
td{  
   
    overflow:hidden;/* 内容超出宽度时隐藏超出部分的内容 */  
    text-overflow:ellipsis;/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用*/  
}  
</style>
</head>
<body>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="row container" id="user_container" data-nav="log">
    <div class="col-sm-2">
      <%@include file="/WEB-INF/views/user/nav.jsp"%>
    </div>

    <div class="col-sm-8 col-sm-offset-1">
      <table id="list"></table>
    </div>
  </div>
</body>
<script>
	$(function($) {
	  $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales["${cookie.locale.value==null?'zh_CN':cookie.locale.value}".replace("_","-")]);
    $("#list").bootstrapTable({
      //toolbar: "#toolbar",
      idField: "id",
      pagination: true,
      sidePagination: "server",
      showRefresh: true,
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
      url: "${globalURL }/admin/loglistajax",
      columns: [{
      	field: "description",
        title: "<spring:message code='log.description' />"
      },{
        field: "operateContent",
        title: "<spring:message code='log.operate.content' />",
        width:"50%"
      },{
        field: "operator",
        title: "<spring:message code='log.operator' />"
      },{
        field: "ip",
        title: "<spring:message code='log.ip' />"
      },{
        field: "date",
        title: "<spring:message code='log.date' />"
      }]
    });
  });
</script>
</html>
