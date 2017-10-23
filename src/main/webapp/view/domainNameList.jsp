<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<c:if test="${!empty INSERT_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainName/new');"> ${NEW} </a>
		</c:if>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th><c:out value="${DOMAIN}" /></th>
        <th><c:out value="${CAPTION}" /></th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td> <a href="#" onClick="doAjaxGet('domainName/show?id=${record.id}');"> ${record.id} </a> </td>
			<td>${record.caption}</td>
			<td>
				<c:if test="${empty UPDATE_ALLOWED}">
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainName/edit?id=${record.id}');"> ${EDIT} </a>
				</c:if>
				<c:if test="${empty DELETE_ALLOWED}">
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('domainName/delete?id=${record.id}');"> ${DELETE} </a>
				</c:if>
			</td>
		</tr>
      </c:forEach>             
    </table>
    </div>
</div>
