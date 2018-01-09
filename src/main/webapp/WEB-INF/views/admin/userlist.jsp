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
    <div id="copy" class="hidden">
    <form class="formdata">
        <div class="form-group">
          <label class="col-sm-3 control-label" for="username"><spring:message code="username" />:</label>
          <div class="col-sm-8">
            <input class="form-control validate[required,minSize[4],maxSize[20]]" type="text" name="username">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-3 control-label" for="password"><spring:message code="password" />:</label>
          <div class="col-sm-8">
            <input class="form-control validate[required,minSize[6],maxSize[20]]" type="text" name="pass">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-3 control-label" for="email"><spring:message code="email" />:</label>
          <div class="col-sm-8">
            <input class="form-control validate[required,minSize[4],maxSize[20]]" type="text" name="email">
          </div>
        </div>
    </form>
    </div>
    <div id="toolbar"><input class="btn btn-default" id="adduser" type="button" value="<spring:message code='user.list.add' />" /></div>
    <div class="col-sm-9">
      <table id="list"></table>
    </div>

  </div>
  <script>
  $(function($) {
	  $("#adduser").click(function(){
		  swal({
			  title: 'Add User',
			  html:$("#copy").html(),
              confirmButtonText:'<spring:message code="message.ok" />',
              showCancelButton: true,
              cancelButtonText:'<spring:message code="message.cancel" />',
              preConfirm: () => {
            	  return $.ajax({
                      url:"${globalURL }/admin/useraddajax",
                      dataType : "json",
                      type : "post",
                      data:$(".formdata:eq(1)").serialize()
                  });
              }
          }).then(function(data) {
        	  if(data.dismiss){
                  return;
              }else if(data.value.status==200){
                  swal({
                      type: 'success',
                      text: '<spring:message code="message.success" />',
                      confirmButtonText:'<spring:message code="message.ok" />'
                  });
              }else{
                  swal({
                      type: 'error',
                      text: '<spring:message code="message.error" />',
                      confirmButtonText:'<spring:message code="message.ok" />'
                  });
              }
          },function(){
              swal({
                  text:'<spring:message code="message.error" />',
                  confirmButtonText:'<spring:message code="message.ok" />'
                });
          });;
	  });
	  
	  
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales["${cookie.locale.value==null?'zh_CN':cookie.locale.value}".replace("_","-")]);
    $("#list").bootstrapTable({
      toolbar: "#toolbar",
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
      url: "${globalURL }/admin/userlistajax",
      onEditableSave:function(field, row, oldValue, $el){
        var data={};
        data.field=field;
        data[field]=row[field];
        data.id=row.id;
        if(field=="upload"||field=="download"||field=="transferEnable"){
            data[field]=parseInt(data[field]*1024*1024*1024);
        }
        $.ajax({
            type:"post",
            url:"${globalURL }/admin/useredit",
            data: data,
            dataType: 'JSON'
        }).done(function(data){
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
        }).fail(function(){
        	swal({
                text:'<spring:message code="message.error" />',
                confirmButtonText:'<spring:message code="message.ok" />'
              });
        })
        ;
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
        formatter: function (value, row, index) {
            return value.substring(0,10);
        },
        editable: {
            type: 'date',
            placement:'bottom'
        }
      }]
      
    });
    
  });
</script>
</body>
</html>
