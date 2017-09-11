<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3> ${PAGETITLE} </h3>

<div align="center">
    <form:form method="post" modelAttribute="record">
    <input type=hidden name=nextpage value="mainMenu/show?id=${record.menu.menu}">
	<table>
		<tr>
			<th> ${MENU} </th>
	        <td> ${record.menu.menu} <form:input type="hidden" path="menu"  /></td>
		</tr>
		<tr>
			<th> ${PAGENAME} </th>
			<td><form:input path="id" /></td>
		</tr>
		<tr>
			<th> ${CAPTION} </th>
			<td><form:input path="caption" /></td>
		</tr>
		<tr>
			<th> ${ICON} </th>
			<td><form:input path="icon" /></td>
		</tr>
		<tr>
			<th> ${URL} </th>
			<td><form:input path="url" /></td>
		</tr>
		<tr>
			<th> ${DISPLAY_ORDER} </th>
			<td><form:input path="displayOrder" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="${SAVE}"> </td>
		</tr>
	</table>
	</form:form>
</div>