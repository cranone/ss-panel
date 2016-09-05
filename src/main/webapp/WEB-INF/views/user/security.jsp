<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
<script src="${globalURL }/resource/project/js/usernav.js"></script>
<script src="${globalURL }/resource/project/js/captcha.js"></script>
<style>
.formErrorContent{
  background-color: white !important;
  color: red !important;
  font-size: 12px !important;
}
</style>
</head>
<body>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="row container" id="user_container" data-nav="security">
    <div class="col-sm-2">
      <%@include file="/WEB-INF/views/user/nav.jsp"%>
    </div>
    
    <div class="col-sm-8 col-sm-offset-1">
      <h3>修改密码</h3>
      <form class="form-horizontal changepassword" action="" method="post">
        <div class="form-group">
          <label class="col-sm-2 control-label" for="oldpassword"><spring:message code="password.old" />:</label>
          <div class="col-sm-4">
            <input class="form-control validate[required,minSize[6],maxSize[20]]" type="password" id="oldpassword" name="oldpassword">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label" for="password"><spring:message code="password" />:</label>
          <div class="col-sm-4">
            <input class="form-control validate[required,minSize[6],maxSize[20]]" type="password" id="password" name="password">
          </div>
        </div>
        <div class="form-group">
          <label class="col-sm-2 control-label" for="passwordcheck"><spring:message code="password.check" />:</label>
          <div class="col-sm-4">
            <input class="form-control validate[required,equals[password]]" type="password" id="passwordcheck">
          </div>
        </div>
        <c:if test="${captchaEbabled}">
          <div class="form-group">
            <label class="col-sm-2 control-label" for="captchaCode"><spring:message code="captcha" />:</label>
            <div class="col-sm-2">
              <input class="form-control validate[required,minSize[4],maxSize[4]]" type="text" id="captchaCode" name="captchaCode" data-prompt-position="centerRight:100,0">
            </div>
            <img class="col-sm-1" id="captchaimg" src="${globalURL }/images/captcha.jpg" style="height: 30px; min-width: 100px;" title="<spring:message code="captcha.change" />">

          </div>
        </c:if>
        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <input class="btn btn-default ajax_submit" type="button" value="<spring:message code="passwrod.change.ok" />"> <span class="error">${error}</span>
          </div>
        </div>
      </form>
    </div>
  </div>
</body>
<script>
	$(function($) {
	  $(".ajax_submit").click(function() {
	  	if(!$(".changepassword").validationEngine("validate")){
	  		return false;
	  	}
		  $.ajax({
		    url : "${globalURL}/user/changepassword",
		    cache : false,
		    dataType : "json",
		    type : "post",
		    data : $(".changepassword").serialize(),
		    success : function(data) {
		    	if(data.status=="0"){
		    		$(".changepassword")[0].reset();
		    	}
		    	swal({
		    		text:data.info,
		    		confirmButtonText:'<spring:message code="message.ok" />'
		    	});
		    },
		    error : function(data) {
		    	location.reload();
		    },
		    complete:function(){
		    	captchaRefresh($("#captchaimg"));
		    	$("#captchaCode").val("");
		    }
		  });
	  });
	  $(".changepassword").validationEngine("attach",{
	    promptPosition: "centerRight",
	    maxErrorsPerField:1
	  });
  });
</script>
</html>
