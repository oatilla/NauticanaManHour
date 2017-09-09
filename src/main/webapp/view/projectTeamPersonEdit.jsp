<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ${PAGETITLE} </title>

<script type="text/javascript" language="JavaScript">
function callSelectionPersonnel() {
  item0 = document.f.PERSON_ID;
  item1 = document.f.PERSON_CAPTION;
  selectWindow = window.open("personnelSelect", "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
  selectWindow.item1 = item1;
}

function callSelectionSubcontractor() {
  item0 = document.f.SUBCONTRACTOR_ID;
  item1 = document.f.SUBCONTRACTOR_CAPTION;
  selectWindow = window.open("subcontractorSelect", "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
  selectWindow.item1 = item1;
}
</script>

</head>
<body>

  <div align="center">
	<table>
		<tr>
			<th> ${PROJECT_ID} </th>
			<td> ${projectId} ${projectCaption} </td>
		</tr>
		<tr>
			<th> ${TEAM_ID} </th>
			<td> ${teamId} ${teamCaption} </td>
		</tr>
		<tr>
			<th> ${DATE} </th>
			<td> ${begDate} .. ${endDate} </td>
		</tr>
	</table>
  
    <form:form name="f" method="post" modelAttribute="record">
    <table>
      <tr>
        <th> ${WORKER_ID}</th>
        <td> ${record.teamId} <form:hidden path="teamId" /></td>
      </tr>
      <tr>
        <th> ${TEAM_LEAD} </th>
        <td> ${record.teamLead} <form:hidden path="teamLead" /></td>
      </tr>
      <tr>
        <th> ${PERSON_ID} </th>
        <td><form:input path="personId" />
        	<input type="button" onClick="if (callSelectionPersonnel();) then {document.f.SUBCONTRACTOR_ID.value=''; document.f.SUBCONTRACTOR_CAPTION.value='';}" value="${CHOOSE}">
        	<form:input path="personCaption" />
        </td>
      </tr>
      <tr>
        <th> ${SUBCONTRACTOR_ID} </th>
        <td><form:input path="subcontractor.id" />
        	<input type="button" onClick="if (callSelectionSubcontractor();) then {document.f.PERSON_ID.value=''; document.f.PERSON_CAPTION.value='';}" value="${CHOOSE}">
        	<form:input path="subcontractor.caption" />
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="${SAVE}"></td>
      </tr>
    </table>
    </form:form>
  </div>
    
</body>
</html>
