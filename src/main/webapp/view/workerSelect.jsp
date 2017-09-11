<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
function findSelected(cname) {
	var c = document.getElementsByName(cname);
	var x = "";
	for (var i in c) {
		if (c[i].checked)
			x = x + "," + c[i].value;
	}
	document.f.workerIds.value = x;
	document.f.operation.value='CHOOSE';
}
</script>

<h3> ${PAGETITLE} </h3>

<div align="center">

    <form name="f">
    
	<input type=hidden name=nextpage value="${nextpage}">
	<input type=hidden name=parentKey value="${parentKey}">
	<input type=hidden name=workerIds value="">
	<input type=hidden name=operation value="SEARCH">

	<select name=subcontractorId onChange="submit();">
	
    <c:forEach var="subcontractor" items="${subcontractors}" varStatus="status">
	<c:choose>
		<c:when test="${subcontractorId == subcontractor.id}">
			<option value="${subcontractor.id}" selected> ${subcontractor.caption} </option>
		</c:when>
		<c:otherwise>
			<option value="${subcontractor.id}"> ${subcontractor.caption} </option>
		</c:otherwise>
	</c:choose>
	</c:forEach>
	
	</select>
    
	<c:choose>
	<c:when test="${empty workers}">
	</c:when>
	<c:otherwise>
	<table>
		<tr>
			<th>${CAPTION} </th>
			<th> &nbsp; </th>
		</tr>

 		<c:forEach var="worker" items="${workers}" varStatus="status">
		<tr>
			<td>${worker.caption}</td>
			<td><input type=checkbox name="WORKER_ID" value="${worker.id}" /></td>
		</tr>
		</c:forEach>             
	</table>
	</c:otherwise>
	</c:choose>

	<input type="button" onClick="findSelected('WORKER_ID');submit();" value='<c:out value="${OK}" />'>
	<input type="button" onClick="history.back();" value='<c:out value="${CANCEL}" />'>
    </form>
    
</div>
