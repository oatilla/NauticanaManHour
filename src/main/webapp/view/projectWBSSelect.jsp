<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="../j/selectCB.js"></script>

<script type="text/javascript">
function findSelected(cname) {
	var checkBoxes = document.getElementsByName(cname);
	var x = "";
	for (var i in checkBoxes) {
		if (checkBoxes[i].checked)
			x = x + "," + checkBoxes[i].value;
	}
	document.f.WBS_IDS.value = x;
}
</script>

<div align="center">
	<h3> ${PAGETITLE} </h3>
	<input type=button id=sa1 value="select all" onClick="selectAll('WBS')" />
	<input type=button id=sa2 value="de-select all" onClick="deSelectAll('WBS')" />
	<input type=button id=sa3 value="toggle select" onClick="toggleSelect('WBS')" />
	
	<table>
		<tr>
			<th> ${CATEGORY_ID} </th>
			<th> ${PARENT_ID} </th>
			<th> ${TREE_CODE} </th>
			<th> ${CAPTION} </th>
			<th> ${SELECT} </th>
		</tr>
		<c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${record.categoryId}</td>
			<td>${record.parentId}</td>
			<td>${record.treeCode}</td>
			<td>${record.caption}</td>
			<c:choose>
				<c:when test="${record.projectId > 0}">
					<td><input type=checkbox name="WBS" value="${record.categoryId}" checked="checked"/></td>
				</c:when>
				<c:otherwise>
					<td><input type=checkbox name="WBS" value="${record.categoryId}" /></td>
				</c:otherwise>
			</c:choose>	
			
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center">
				<form name=f method=post>
					<input type=hidden name=PROJECT_ID value="${projectId}">
					<input type=hidden name=WBS_IDS value="">
					<input type="button" onClick="findSelected('WBS');submit();" value="${OK}">
					<input type="button" onClick="history.back();" value="${CANCEL}">
				</form>
			</td>
		</tr>
	</table>
</div>