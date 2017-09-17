<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
	<p> ${PAGENAME} : ${record.id} ${record.caption} ${record.url} <a class="btn btn-primary" href="#" onClick="doAjaxGet('screenPage/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a></p>

    <h3> ${PAGE_AUTHORIZATION} </h3>

	<a class="btn btn-primary" href="#" onClick="doAjaxGet('pageAuthorization/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"> </i> ${NEW} </a>

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
          <td><a class="btn btn-danger" href="#" onClick="doAjaxGet('pageAuthorization/delete?id=${pageAuthorization.id.authorityGroup},${pageAuthorization.id.pagename}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>
</div>