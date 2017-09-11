<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
    <h3><a href="#" onclick="doAjaxPost('userAccount/edit?id=${record.id}');"> ${EDIT} </a></h3>
    
	<table>
		<tr>
			<th> ${USERNAME} </th>
			<td> ${record.id} ${record.caption} </td>
		</tr>
	</table>

    <h3><c:out value="${USER_AUTHORIZATION}" /></h3>

	<a href="#" onclick="doAjaxPost('/userAuthorization/new?parentKey=${record.id}');"> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${AUTHORITY_GROUP} </th>
        <th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="userAuthorization" items="${record.userAuthorizations}" varStatus="status">
        <tr>
          <td>${userAuthorization.id.authorityGroup}</td>
          <td><a href="#" onclick="doAjaxPost('/userAuthorization/delete?id=${userAuthorization.id.username},${userAuthorization.id.authorityGroup}');"> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>

</div>