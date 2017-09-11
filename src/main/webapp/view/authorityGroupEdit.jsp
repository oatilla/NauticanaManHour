<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1> ${PAGETITLE}</h1>
  <div align="center">
    
    <form:form class="form-horizontal" method="post" modelAttribute="record" >
     <div class="box-body">
      <div class="form-group has-feedback">
      	  <label class="col-sm-2 control-label" for="authGroup">${AUTHORITY_GROUP}</label>
	      <div class="col-sm-10">  
	        <form:input placeholder=""  id="authGroup" name="authGroup" class="form-control" path="id"/>
	      </div>
      </div>
      <div class="form-group has-feedback">
  		    <label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
        	<div class="col-sm-10"> 
       			 <form:input placeholder="" id="caption" name="authCaption" class="form-control" path="caption"/>
       		</div>
      </div>
      </div>
       <div class="box-footer">
      
	          <button type="submit" class="btn btn-primary btn-block btn-flat" value="${SAVE}">Save</button>
	    </div>
    </form:form>
  </div>
