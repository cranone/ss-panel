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
  <div class="row container" id="user_container" data-nav="codelist">
    <div class="col-sm-2">
      <%@include file="/WEB-INF/views/user/nav.jsp"%>
    </div>
    <div>
      <input class="btn btn-default" id="create" type="button" value="<spring:message code="code.cteate" />" >
    </div>
    <div class="col-sm-9"><table id="list"></table></div>
    
  </div>
</body>
<script>
  $(function($) {
  	
  	$("#create").click(function(){
  		$.ajax({
  			url:"${globalURL }/admin/createCode",
        dataType : "json",
        type : "post",
        data:{
        	num:1,
        	type:1,
        	amount:1
        },
        success:function(data){
        	if(data.status==200){
            swal({
              type: 'success',
              text: '<spring:message code="user.recharge.success" />'
            });
          }else{
            swal({
              type: 'error',
              text: '<spring:message code="user.recharge.fail" />'
            });
          }
        }
  		});
  	});
  	
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
          currentPage:params.offset+1,
          sizePage:params.limit
        };
      },
      url: "${globalURL }/admin/codelistajax",
      columns: [{
        checkbox: true
      }, {
        field: "code",
        title: "<spring:message code='code.code' />"
      }, {
        field: "codeType",
        title: "<spring:message code='code.type' />"
      }, {
        field: "amount",
        title: "<spring:message code='code.amount' />"
      }]
      
    });
    
  });
</script>
</html>
