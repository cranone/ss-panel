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
    <div class="col-sm-9"><table id="list"></table></div>
    
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
          currentPage:params.offset+1,
          sizePage:params.limit
        };
      },
      url: "${globalURL }/admin/userListAjax",
      onEditableSave:function(field, row, oldValue, $el){
      	row.field=field;
      	$.ajax({
      		type:"post",
      		url:"${globalURL }/admin/useredit",
      		data: row,
      		dataType: 'JSON',
      		success: function (data) {
            if(data.status==200){
            	swal({
                text:'<spring:message code="message.success" />',
                confirmButtonText:'<spring:message code="message.ok" />'
              });
            }else{
            	swal({
                text:data.info,
                confirmButtonText:'<spring:message code="message.ok" />'
              });
            }
          },
          error: function () {
          	swal({
              text:'<spring:message code="message.error" />',
              confirmButtonText:'<spring:message code="message.ok" />'
            });
          }
      	});
      },
      columns: [{
        checkbox: true
      }, {
        field: "username",
        title: "<spring:message code='username' />"
      },{
        field: "pass",
        title: "<spring:message code='password' />",
        formatter: function (value, row, index) {
            return "";
        },
        editable: {
            type: 'text',
            emptytext:"******"
        }
      },{
        field: "upload",
        title: "<spring:message code='user.upload' />(GB)",
        formatter: function (value, row, index) {
        	var num=parseInt(value/1024/1024/1024*100)/100;
          return num.toFixed(2);
        },
        editable: {
            type: 'text'
        }
      },{
        field: "download",
        title: "<spring:message code='user.download' />(GB)",
        formatter: function (value, row, index) {
        	var num=parseInt(value/1024/1024/1024*100)/100;
          return num.toFixed(2);
        },
        editable: {
            type: 'text'
        }
      },{
        field: "transferEnable",
        title: "<spring:message code='user.transfer' />(GB)",
        formatter: function (value, row, index) {
        	var num=parseInt(value/1024/1024/1024*100)/100;
          return num.toFixed(2);
        },
        editable: {
            type: 'text'
        }
      },{
        field: "expiresDate",
        title: "<spring:message code='user.expires' />",
        editable: {
            type: 'date'
        }
      }]
      
    });
    
  });
</script>
</html>
