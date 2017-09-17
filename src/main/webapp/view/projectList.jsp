<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
    <p><a class="btn btn-primary" href="#" onClick="doAjaxGet('project/new');"> <i class="${NEW_ICON}"> ${NEW} </i> </a></p>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${PROJECT_ID}</th>
        <th>${CAPTION}</th>
        <th>${COUNTRY}</th>
        <th>${START_DATE}</th>
        <th>${DURATION}</th>
 		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.id}</td>
          <td><a href="#" onClick="doAjaxGet('project/show?id=${record.id}');">${record.caption}</a></td>
          <td>${record.country}</td>
          <td>${record.startDate}</td>
          <td>${record.duration}</td>
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('project/delete?id=${record.id}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
				<a href="#" onClick="doAjaxGet('project/projectWbs/list?projectId=${record.id}');"> ${PROJECT_WBS} </a>
				<a href="#" onClick="doAjaxGet('project/projectTeam/list?projectId=${record.id}');"> ${PROJECT_TEAM} </a>
		  </td>
        </tr>
      </c:forEach>
    </table>
</div>