<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/new');"> ${NEW} </a>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${SUBCONTRACTOR_ID} </th>
        <th> ${CAPTION} </th>
        <th> ${EXT_SUBCONTRACTOR} </th>
  		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td> ${record.id} </td>
          <td> <a href="#" onClick="doAjaxGet('subcontractor/show?id=${record.id}');"> ${record.caption} </a> </td>
          <td> ${record.extSubcontractor} </td> 
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/edit?id=${record.id}');"> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('subcontractor/delete?id=${record.id}');"> ${DELETE} </a>
			</td>
        </tr>
      </c:forEach>             
    </table>
    </div>
</div>