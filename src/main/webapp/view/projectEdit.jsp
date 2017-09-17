<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

         
	     <form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
			<div class="box-body">
			<h1> ${PAGETITLE}</h1>
			     <div class="form-group">
		                  <label class="control-label col-sm-2" for="caption">${CAPTION}</label>
		                  <div class="col-sm-6">
		                  		<form:input type="text" class="form-control" id="caption" path="caption"/>
		                  </div>
	                </div>
	                <div class="form-group">
		                  <label class="control-label col-sm-2" for="country">${COUNTRY}</label>
		                  <div class="col-sm-6">
			                  <form:select class="form-control select2" id="country" items="${COUNTRY_LIST}" path="country" />
			                
		                  </div>
               		</div>
                   	<div class="form-group">
		                <label class="control-label col-sm-2" for="stsrtDate">Date</label>
		                <div class="input-group date col-sm-5">
			                  <form:input type="text" class="form-control pull-right" id="startDate" path="startDate"/>
			                  <div class="input-group-addon">
		    	                <i class="fa fa-calendar"></i>
		        	          </div>
	             	  	</div>
             		</div>
		            <div class="form-group">
		                  <label class="control-label col-sm-2" for="duration">${DURATION}</label>
		                  <div class="col-sm-6">
		                 		 <form:input type="text" class="form-control" id="duration" path="duration"/>
		                  </div>
		            </div>
		            
		            <div class="form-group" style="visibility: hidden">
		                  <label class="control-label" for="id">${PROJECT_ID}</label>
		                  <form:input type="text" class="form-control" id="id" path="id" />
	                </div>
		           
		              <!-- /.box-body -->
		              
		           <div class="form-group">
		           <div class="col-sm-6">
		                  <a href="#" onclick="doAjaxPost('project/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
	   			    </div>
	   			   </div>
                	
                	 </div>
	        
            </form:form>

