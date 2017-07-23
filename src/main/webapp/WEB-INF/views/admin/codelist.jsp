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
    <div class="col-sm-9">
      <div>
        <form class="form-horizontal" id="dataform" action="" method="post">
          <div class="form-group">
            <label class="col-sm-2 control-label" for="codeType"><spring:message code="code.type" />:</label>
            <div class="col-sm-4">
              <select class="form-control validate[required]" id="codeType" name="codeType">
                <c:forEach items="${codeType }" var="item">
                  <option value="${item }">${item.name }</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label" for="num"><spring:message code="code.num" />:</label>
            <div class="col-sm-4">
              <input class="form-control validate[required,custom[integer]]" id="num" name="num" />
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label" for="amount"><spring:message code="code.amount" />:</label>
            <div class="col-sm-4">
              <input class="form-control validate[required,custom[integer]]" id="amount" name="amount" />
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <input class="btn btn-default" id="create" type="button" value="<spring:message code="code.cteate" />">
            </div>
          </div>
        </form>
      </div>
      <div id="toolbar">
        <form class="form-inline" id="searchform">
          <label class="control-label" for="listCodeType"><spring:message code="code.type" />:</label>
          <select class="form-control" id="listCodeType" name="codeType">
            <option value=""><spring:message code="code.all" /></option>
            <c:forEach items="${codeType }" var="item">
              <option value="${item }">${item.name }</option>
            </c:forEach>
          </select>
          <label class="control-label" for="listUsed"><spring:message code="code.use.state" />:</label>
          <select class="form-control" id="listUsed" name="codeUse">
            <option value=""><spring:message code="code.all" /></option>
            <option value="0"><spring:message code="code.unused" /></option>
            <option value="1"><spring:message code="code.used" /></option>
          </select>
          <input class="btn btn-default" id="search" type="button" class="control-label" value="search">
        </form>
      </div>
      <table id="list"></table>
    </div>

  </div>
</body>
<script>
	$(function($) {
	  $("#dataform").validationEngine("attach", {
	    promptPosition : "centerRight",
	    maxErrorsPerField : 1,
	    validationEventTrigger : ""
	  });

	  $("#create").click(function() {
		  if (!$("#dataform").validationEngine("validate")) {
			  return false;
		  }
		  $.ajax({
		    url : "${globalURL }/admin/createCode",
		    dataType : "json",
		    type : "post",
		    data : $("#dataform").serialize(),
		    success : function(data) {
			    if (data.status == 200) {
				    swal({
				      type : 'success',
				      text : '<spring:message code="code.create.success" />'
				    });
			    } else {
				    swal({
				      type : 'error',
				      text : '<spring:message code="code.create.fail" />'
				    });
			    }
		    }
		  });
	  });
	  
	  $('#search').click(function() {  
	  	var params = $('#list').bootstrapTable('getOptions');  
      params.queryParams=function(params){
      //遍历form 组装json  
        $.each($("#searchform").serializeArray(), function(i, field) {  
          console.info(field.name + ":" + field.value + " ");  
          //可以添加提交验证  
          params[field.name] = field.value;  
        });  
        params.currentPage=params.offset;
        params.sizePage=params.limit;
        return params;  
      };
      $('#list').bootstrapTable('refresh');  
      console.info(params);  
	  });

	  $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales["${cookie.locale.value==null?'zh_CN':cookie.locale.value}".replace("_", "-")]);
	  $("#list").bootstrapTable({
	    toolbar : "#toolbar",
	    idField : "id",
	    pagination : true,
	    sidePagination : "server",
	    showRefresh : true,
	    search : false,
	    clickToSelect : false,
	    method : "post",
	    contentType : "application/x-www-form-urlencoded",
	    pageNumber : 1,
	    pageSize : 10,
	    smartDisplay : false,
	    pageList : [ 10, 25, 50, 100 ],
	    queryParams : function(params) {
	    	params.currentPage=params.offset;
	      params.sizePage=params.limit;
	      return params;
	    },
	    url : "${globalURL }/admin/codelistajax",
	    columns : [ {
		    checkbox : true
	    }, {
	      field : "code",
	      title : "<spring:message code='code.code' />"
	    }, {
	      field : "codeType",
	      title : "<spring:message code='code.type' />",
	      formatter : function(value, row, index) {
		      return $("option[value='" + value + "']").html();
	      }
	    }, {
	      field : "amount",
	      title : "<spring:message code='code.amount' />"
	    }, {
	      field : "consumer.username",
	      title : "<spring:message code='code.consumer' />"
	    }, {
	      field : "consumeDate",
	      title : "<spring:message code='code.consume.date' />"
	    } ]

	  });

  });
</script>
</html>
