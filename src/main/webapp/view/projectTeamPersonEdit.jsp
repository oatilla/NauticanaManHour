<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${PAGETITLE}" /></title>
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
			<th><c:out value="${PROJECT_ID}" /></th>
			<td><c:out value="${projectId}" /> <c:out value="${caption}" /></td>
		</tr>
		<tr>
			<th><c:out value="${TEAM_ID}" /></th>
			<td><c:out value="${teamId}" /> <c:out value="${teamCaption}" /></td>
		</tr>
		<tr>
			<th><c:out value="${DATE}" /></th>
			<td><c:out value="${begDate}" /> .. <c:out value="${endDate}" /></td>
		</tr>
	</table>
  
    <form:form name="f" method="post" modelAttribute="record">
    <table>
      <tr>
        <th><c:out value="${WORKER_ID}" /></th>
        <td><c:out value="${record.teamId}"/> <form:hidden path="teamId" /></td>
      </tr>
      <tr>
        <th><c:out value="${TEAM_LEAD}" /></th>
        <td><form:checkbox path="teamLead" /></td>
      </tr>
      <tr>
        <th><c:out value="${PERSON_ID}" /></th>
        <td><form:input path="personId" />
        	<input type="button" onClick="if (callSelectionPersonnel();) then {document.f.SUBCONTRACTOR_ID.value=''; document.f.SUBCONTRACTOR_CAPTION.value='';}" value='<c:out value="${CHOOSE}" />'>
        	<form:input path="personCaption" />
        </td>
      </tr>
      <tr>
        <th><c:out value="${SUBCONTRACTOR_ID}" /></th>
        <td><form:input path="subcontractorId" />
        	<input type="button" onClick="if (callSelectionSubcontractor();) then {document.f.PERSON_ID.value=''; document.f.PERSON_CAPTION.value='';}" value='<c:out value="${CHOOSE}" />'>
        	<form:input path="subcontractorCaption" />
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value='<c:out value="${SAVE}" />'></td>
      </tr>
    </table>
    </form:form>
  </div>
    
</body>
</html>
