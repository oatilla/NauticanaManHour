<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
    <p><a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/new');"> <i class="${NEW_ICON}"> ${NEW} </i> </a></p>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${SUBCONTRACTOR_ID} </th>
        <th> ${CAPTION} </th>
  		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td> ${record.id} </td>
          <td> <a href="#" onClick="doAjaxGet('subcontractor/show?id=${record.id}');"> ${record.caption} </a> </td>
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('subcontractor/delete?id=${record.id}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
			</td>
        </tr>
      </c:forEach>             
    </table>
</div>