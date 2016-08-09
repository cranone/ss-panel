<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="modal alert" role="alertdialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code="message.operate"/></h4>
      </div>
      
      <div class="modal-body">
      
      </div>
      
      <div class="modal-footer">
        <button class="btn btn-primary ok" type="button"><spring:message code="message.ok"/></button>
      </div>
    </div>
  </div>
</div>


<div class="modal confirm" role="alertdialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><spring:message code="message.operate"/></h4>
      </div>
      
      <div class="modal-body">
      
      </div>
      
      <div class="modal-footer">
        <button class="btn btn-primary ok" type="button" ><spring:message code="message.ok"/></button>
        <button class="btn btn-default" type="button" data-dismiss="modal"><spring:message code="message.cancel"/></button>
      </div>
    </div>
  </div>
</div>
