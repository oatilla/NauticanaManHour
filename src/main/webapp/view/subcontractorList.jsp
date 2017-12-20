<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<c:if test="${!empty INSERT_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/new');"> ${NEW} </a>
		</c:if>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover thin-list">
	  <thead>
      <tr>
        <th> ${SUBCONTRACTOR_ID} </th>
        <th> ${CAPTION} </th>
        <th> ${EXT_SUBCONTRACTOR} </th>
  		<th> &nbsp; </th>
      </tr>
      </thead>
	<tbody>
	     <c:forEach var="record" items="${records}" varStatus="status">
	        <tr>
	          <td> ${record.id} </td>
	          <td> <a href="#" onClick="doAjaxGet('subcontractor/show?id=${record.id}');"> ${record.caption} </a> </td>
	          <td> ${record.extSubcontractor} </td> 
				<td>
				<c:if test="${!empty UPDATE_ALLOWED}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/edit?id=${record.id}');"> ${EDIT} </a>
				</c:if>
				<c:if test="${!empty DELETE_ALLOWED}">
					<a class="btn btn-danger" href="#" onClick="doAjaxGet('subcontractor/delete?id=${record.id}');"> ${DELETE} </a>
				</c:if>
				</td>
	        </tr>
	     </c:forEach>  
      </tbody>           
    </table>
    </div>
</div>
<script type="text/javascript" src="/j/dataTables.style.js"> </script>
