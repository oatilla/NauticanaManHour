<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
    <h3><a href="#" onclick="doAjaxPost('screenPage/edit?id=${record.id}');"> ${EDIT} </a></h3>
    
	<table>
		<tr>
			<th> ${PAGENAME} </th>
			<td> ${record.id} ${record.caption}  ${record.url} </td>
		</tr>
	</table>

    <h3><c:out value="${PAGE_AUTHORIZATION}" /></h3>

	<a href="#" onclick="doAjaxPost('pageAuthorization/new?parentKey=${record.id}');"> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${AUTHORITY_GROUP} </th>
        <th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="pageAuthorization" items="${record.pageAuthorizations}" varStatus="status">
        <tr>
          <td>${pageAuthorization.id.authorityGroup}</td>
          <td>
          	<a href="#" onclick="doAjaxPost('pageAuthorization/delete?id=${pageAuthorization.id.authorityGroup},${pageAuthorization.id.pagename}');"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>

</div>