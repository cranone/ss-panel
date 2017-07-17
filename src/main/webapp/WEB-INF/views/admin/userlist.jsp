<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
<link rel="stylesheet" href="${resourceURL }/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css">
<link rel="stylesheet" href="${resourceURL }/bootstrap-table/1.11.1/bootstrap-table.min.css">

<script src="${resourceURL }/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.js"></script>
<script src="${resourceURL }/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="${resourceURL }/bootstrap-table/1.11.1/extensions/editable/bootstrap-table-editable.js"></script>
<script src="${resourceURL }/bootstrap-table/1.11.1/bootstrap-table-locale-all.min.js"></script>

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
          <td><spring:message code="user.expires" /></td>
          <td><spring:message code="message.operate" />&nbsp;<a class="add" href="##"><spring:message code="message.add" /></a></td>
        </tr>
        <c:forEach items="${page.list }" var="item">
          <tr>
            <td class="col-sm-2">${item.username }</td>
            <td class="col-sm-2">${item.email }</td>
            <td class="col-sm-2" data-transfer="${item.transferEnable/1024/1024/1024 }"><fmt:formatNumber minIntegerDigits="1" value="${(item.upload+item.download)/1024/1024/1024 }" pattern="#.00"/>&nbsp;/&nbsp;<fmt:formatNumber minIntegerDigits="1" value="${item.transferEnable/1024/1024/1024 }" pattern="#.00"/></td>
            <td class="col-sm-2" data-expires="${item.expiresDate }"><fmt:formatDate pattern="yyyy-MM-dd" value="${item.expiresDate }"/></td>
            <td><a class="update" href="##" data-id="${item.id }"><spring:message code="message.update" /></a></td>
          </tr>
        </c:forEach>
      </table>
      <ul id="pagination" class="pagination-sm"></ul>
      
      <table id="test">
      
      </table>
    </div>
  </div>
  <div class="container" style="width: 400px;">
    <form class="form-horizontal">
      <div class="form-group">
        <label class="col-sm-4 control-label" for="password"><spring:message code="password" />:</label>
          <div class="col-sm-8">
            <input class="form-control validate[minSize[6],maxSize[20]]" type="password" id="password" name="password">
          </div>
      </div>
      <div class="form-group">
        <label class="col-sm-4 control-label" for="transfer"><spring:message code="user.transfer" />(GB):</label>
          <div class="col-sm-8">
            <input class="form-control validate[number]" type="text" id="transfer" name="transferEnable">
          </div>
      </div>
      <div class="form-group">
        <label class="col-sm-4 control-label" for="expiresDate"><spring:message code="user.expires" />:</label>
          <div class="col-sm-8">
            <input class="form-control validate[custom[date]]" type="text" id="expiresDate" name="expiresDate">
          </div>
      </div>
    </form>
  </div>
</body>
<script>

  function createDiv(id,transfer,expiresDate){
  	var html='<div class="row"><form class="form-horizontal" id="edit">'+
  	'<input type="hidden" value='+id+' name="id">'+
  	'<div class="form-group">'+
        '<label class="col-sm-4 control-label" for="password"><spring:message code="password" />:</label>'+
          '<div class="col-sm-8">'+
            '<input class="form-control validate[minSize[6],maxSize[20]]" type="password" id="password" name="password" >'+
          '</div>'+
      '</div>'+
      '<div class="form-group">'+
        '<label class="col-sm-4 control-label" for="transfer"><spring:message code="user.transfer" />(GB):</label>'+
          '<div class="col-sm-8">'+
            '<input class="form-control validate[number]" type="text" id="transfer" name="transfer" value='+transfer+'>'+
          '</div>'+
      '</div>'+
      '<div class="form-group">'+
        '<label class="col-sm-4 control-label" for="expiresDate"><spring:message code="user.expires" />:</label>'+
          '<div class="col-sm-8">'+
            '<input class="form-control validate[custom[date]]" type="text" id="expiresDate" name="expiresDate" value='+expiresDate+'>'+
          '</div>'+
      '</div>'+
  	'</form></div>';
  	return html;
  }
  
  function ajaxEdit(url){
  	$.ajax({
  		url:url,
  		cache : false,
      dataType : "json",
      type : "post",
      data : $("#edit").serialize(),
      success : function(data) {
        swal({
          text:data.info,
          confirmButtonText:'<spring:message code="message.ok" />'
        });
      },
      error : function(data) {
        location.reload();
      }
  	});
  }
  
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
	  
	  $(".add").click(function(){
	  	swal({
	  	  title: 'ADD',
	  	  html: createDiv('','',''),
	  	  showCancelButton: true,
	  	  confirmButtonText: 'save',
	  	  cancelButtonText: 'cancel',
	  	}).then(function(isConfirm) {
	  	  if (isConfirm === true) {
	  	  	ajaxEdit("${globalURL }/admin/usersave");
	  	  }
	  	}).catch(swal.noop);
	  });
	  
	  $(".update").click(function(){
	  	var id=$(this).data("id");
	  	var tag=$(this).parent();
	  	var expires=tag.prev().data("expires");
	  	var transfer=tag.prev().prev().data("transfer");
	  	swal({
        title: 'UPDATE',
        html: createDiv(id,transfer,expires),
        showCancelButton: true,
        confirmButtonText: 'save',
        cancelButtonText: 'cancel',
      }).then(function(isConfirm) {
        if (isConfirm === true) {
        	ajaxEdit("${globalURL }/admin/useredit");
        }
      }, function(dismiss) {
        if (dismiss === 'cancel') { // you might also handle 'close' or 'timer' if you used those
          // ignore
        } else {
          throw dismiss;
        }
      });
	  });
	  $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales["${cookie.locale.value==null?'zh_CN':cookie.locale.value}".replace("_","-")]);
	  $("#test").bootstrapTable({
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
      columns: [{
        checkbox: true
      }, {
        field: "username",
        title: "<spring:message code='username' />",
        editable: {
            type: 'text',
            validate: function (v) {
                if (!v) return '用户名不能为空';
            }
        }
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
        	return value/1024/1024/1024;
        },
        editable: {
            type: 'text'
        }
      },{
        field: "download",
        title: "<spring:message code='user.download' />(GB)",
        formatter: function (value, row, index) {
            return value/1024/1024/1024;
        },
        editable: {
            type: 'text'
        }
      },{
        field: "transferEnable",
        title: "<spring:message code='user.transfer' />(GB)",
        formatter: function (value, row, index) {
          return value/1024/1024/1024;
        },
        editable: {
            type: 'text'
        }
      },{
        field: "expiresDate",
        title: "<spring:message code='user.expires' />",
        formatter: function (value, row, index) {
          var date =new Date(value);
          return date.getFullYear()+"-"+((date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1))+"-"+(date.getDate()<10?"0"+date.getDate():date.getDate());
        },
        editable: {
            type: 'date'
        }
      }]
      
    });
	  
  });
</script>
</html>
