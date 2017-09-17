<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <p><a class="btn btn-primary" href="#" onClick="doAjaxGet('mainMenu/new');"> <i class="${NEW_ICON}"> ${NEW} </i> </a></p>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${MENU} </th>
        <th> ${CAPTION} </th>
        <th> ${DISPLAY_ORDER} </th>
  		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td> <a href="#" onClick="doAjaxGet('mainMenu/show?id=${record.id}');"> ${record.id} </a> </td>
          <td>${record.caption}</td>
          <td>${record.displayOrder}</td>
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('mainMenu/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('mainMenu/delete?id=${record.id}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
			</td>
        </tr>
      </c:forEach>             
    </table>
</div>