<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${PAGETITLE}" /></title>
<script type="text/javascript" language="JavaScript">
function callSelectionProjectWBS(id) {
  item0 = document.f.WBS_IDS;
  selectWindow = window.open("select?id=" + id, "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
}
</script>
</head>
<body>

<div align="center">
	<table>
		<tr>
			<th><c:out value="${PROJECT_ID}" /></th>
			<td><c:out value="${projectId}" /></td>
		</tr>
		<tr>
			<th><c:out value="${CAPTION}" /></th>
			<td><c:out value="${caption}" /></td>
		</tr>
	</table>
</div>

<div align="center">
	<p> <c:out value="${PROJECT_WBS}" /> </p>
	<form name=f method=post>
	<input type="button" onClick="if (callSelectionProjectWBS('${projectId}');) then submit();" value='<c:out value="${CHOOSE}" />'>
	<input type=hidden name=PROJECT_ID value="${projectId}">
	<input type=hidden name=WBS_IDS value="">
	</form>
	<table>
		<tr>
			<th><c:out value="${TREE_CODE}" /></th>
			<th><c:out value="${CAPTION}" /></th>
			<th><c:out value="${UNIT}" /></th>
			<th><c:out value="${METRIC}" /></th>
			<th><c:out value="${WORKFORCE}" /></th>
			<th><c:out value="${PUP_METRIC}" /></th>
			<th><c:out value="${PUP_WORKFORCE}" /></th>
		</tr>
		<c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${record.treeCode}</td>
			<td>${record.caption}</td>
			<td>${record.unit}</td>
			<td>${record.metrif}</td>
			<td>${record.workforce}</td>
			<td>${record.pupMetric}</td>
			<td>${record.pupWorkforce}</td>
			<td>
			 <a href="edit?projectId=${record.projectId}&id=${record.categoryId}"> <c:out value="${EDIT}" /> </a> &nbsp;
			 <a href="delete?projectId=${record.projectId}&id=${record.categoryId}"> <c:out value="${DELETE}" /> </a></td>
		</tr>
		</c:forEach>             
	</table>
  </div>

</body>
</html>
