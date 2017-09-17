<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
${DATATABLE2}
${DATATABLE3}

<div align="center">
	<h3> ${PAGETITLE} </h3>

	<p>${AUTHORITY_GROUP} : ${record.id} ${record.caption} <a class="btn btn-primary" href="#" onClick="doAjaxGet('authorityGroup/edit?id=${record.id}');"> <i class="${EDIT_ICON}"></i> ${EDIT} </a></p>
    
    <h3>${TABLE_AUTHORIZATION}</h3>

	<a class="btn btn-primary" href="#" onClick="doAjaxGet('tableAuthorization/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"></i> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${TABLENAME}</th>
        <th>${ALLOW_SELECT}</th>
        <th>${ALLOW_INSERT}</th>
        <th>${ALLOW_UPDATE}</th>
        <th>${ALLOW_DELETE}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="tableAuthorization" items="${record.tableAuthorizations}" varStatus="status">
        <tr>
          <td>${tableAuthorization.id.tablename}</td>
          <td>${tableAuthorization.allowSelect}</td>
          <td>${tableAuthorization.allowInsert}</td>
          <td>${tableAuthorization.allowUpdate}</td>
          <td>${tableAuthorization.allowDelete}</td>
          <td>
          	<a class="btn btn-primary" href="#" onClick="doAjaxGet('tableAuthorization/edit?id=${tableAuthorization.id.authorityGroup},${tableAuthorization.id.tablename}');"> <i class="${EDIT_ICON}"></i> ${EDIT} </a> &nbsp;
          	<a class="btn btn-danger" href="#" onClick="doAjaxGet('tableAuthorization/delete?id=${tableAuthorization.id.authorityGroup},${tableAuthorization.id.tablename}');"> <i class="${DELETE_ICON}"></i> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    
    <h3>${USER_AUTHORIZATION}</h3>

    <table id="dataTable2" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${USERNAME}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="userAuthorization" items="${record.userAuthorizations}" varStatus="status">
        <tr>
          <td>${userAuthorization.id.username}</td>
          <td><a class="btn btn-danger" href="#" onClick="doAjaxGet('userAuthorization/delete?id=${userAuthorization.id.username},${userAuthorization.id.authorityGroup}');"> <i class="${DELETE_ICON}"></i> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>
    
    <h3>${PAGE_AUTHORIZATION}</h3>

	<table id="dataTable3" class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>${PAGENAME}</th>
				<th> &nbsp; </th>
			</tr>
		</thead>
		
		<c:forEach var="pageAuthorization" items="${record.pageAuthorizations}" varStatus="status">
		<tr>
			<td>${pageAuthorization.id.pagename}</td>
			<td><a class="btn btn-danger" href="#" onClick="doAjaxGet('pageAuthorization/delete?id=${pageAuthorization.id.authorityGroup},${pageAuthorization.id.pagename}');"> <i class="${DELETE_ICON}"></i> ${DELETE} </a></td>
		</tr>
		</c:forEach>
	</table>

</div>
