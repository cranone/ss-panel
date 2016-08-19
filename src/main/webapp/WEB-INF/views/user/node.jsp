<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="/WEB-INF/views/include/headlib.jsp"%>
</head>
<body>
  <%@include file="/WEB-INF/views/include/header.jsp"%>
  <div class="row container">
    <div class="col-sm-2">
      <%@include file="/WEB-INF/views/user/header.jsp"%>
    </div>

    <div class="col-sm-8 col-sm-offset-1">
      <h3>节点信息</h3>
      <div>
        <span class="col-sm-3"><label>端口:</label>50000</span>
        <span class="col-sm-3"><label>密码:</label>123456</span>
        <span class="col-sm-3"><label>流量(MB):</label>100/1000</span>
      </div>
      <br>
      <h3>节点列表</h3>
      <table class="table">
        <tr>
          <td>节点位置</td>
          <td>节点IP</td>
          <td>节点状态</td>
        </tr>
        <tr>
          <td>香港</td>
          <td>45.xx</td>
          <td>正常</td>
        </tr>
      </table>
    </div>
  </div>
</body>
<script>
	$(function($) {
		var nav=$("#left_nav");
		
  });
</script>
</html>
