<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${PAGETITLE}" /></title>

<script type="text/javascript" src="selectCB.js"></script>

<script type="text/javascript" language="JavaScript">
function findSelected() {
	var checkBoxes = document.getElementsByName(cname);
	var x = "";
	for (var i in checkBoxes) {
		if (checkBoxes[i].checked) then
			x = x + "," + checkBoxes[i].vlue;
	}
	item0.value = x;
	return true;
}
</script>

</head>


<body onLoad="self.focus();" onmouseout="self.focus();">

<div align="center">
	<input type=button id=sa1 value="select all" onClick="selectAll('WBS')" />
	<input type=button id=sa2 value="de-select all" onClick="deSelectAll('WBS')" />
	<input type=button id=sa3 value="toggle select" onClick="toggleSelect('WBS')" />
	<table>
		<tr>
			<th><c:out value="${CATEGORY_ID}" /></th>
			<th><c:out value="${PARENT_ID}" /></th>
			<th><c:out value="${TREE_CODE}" /></th>
			<th><c:out value="${CAPTION}" /></th>
			<th><c:out value="${SELECT}" /></th>
		</tr>
		<c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${record.categoryId}</td>
			<td>${record.parentId}</td>
			<td>${record.treeCode}</td>
			<td>${record.caption}</td>
			<td><input type=checkbox name="WBS" value="${record.categoryId}" checked=${record.projectId > 0}/></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="center">
				<input type="button" onClick="return findSelected();" value='<c:out value="${OK}" />'>
				<input type="button" onClick="return false;" value='<c:out value="${CANCEL}" />'>
			</td>
		</tr>
	</table>
</div>
    
</body>
</html>
