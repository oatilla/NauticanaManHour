<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <p><a class="btn btn-primary" href="#" onClick="doAjaxGet('authorityGroup/new');"> <i class="${NEW_ICON}"></i> ${NEW} </a></p>
    <table id="dataTable1" class="table table-bordered table-hover">
      <thead>
      <tr>
        <th>${AUTORITY_GROUP}</th>
        <th>${CAPTION}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td> <a href="#" onClick="doAjaxGet('authorityGroup/show?id=${record.id}');"> ${record.id} </a> </td>
          <td>${record.caption}</td>
          <td>
			<a href="#" onClick="doAjaxGet('authorityGroup/edit?id=${record.id}');" class="btn btn-primary"> <i class="${EDIT_ICON}"></i> ${EDIT} </a> 
			<a href="#" onClick="doAjaxGet('authorityGroup/delete?id=${record.id}');" class="btn btn-danger"> <i class="${DELETE_ICON}"></i> ${DELETE} </a> 
        </tr>
      </c:forEach>             
    </table>
</div>
