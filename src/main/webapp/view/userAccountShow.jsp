<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
	<p> ${USERNAME} : ${record.id} ${record.caption} <a class="btn btn-primary" href="#" onClick="doAjaxGet('userAccount/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a></p>

    <h3> ${USER_AUTHORIZATION} </h3>

	<a class="btn btn-primary" href="#" onClick="doAjaxGet('userAuthorization/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"> </i> ${NEW} </a>

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
          <td><a class="btn btn-danger" href="#" onClick="doAjaxGet('userAuthorization/delete?id=${userAuthorization.id.username},${userAuthorization.id.authorityGroup}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>
</div>