<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
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

<div align="center">
	<h3> ${PAGETITLE} </h3>
	<table class="table table-condensed">
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
  
    <form:form name="f" method="post" modelAttribute="record" id="f">
    <table class="table table-condensed">
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
        	<input type="button" onClick="if (callSelectionPersonnel()) {document.f.SUBCONTRACTOR_ID.value=''; document.f.SUBCONTRACTOR_CAPTION.value='';}" value="${CHOOSE}">
        	<form:input path="personCaption" />
        </td>
      </tr>
      <tr>
        <th> ${SUBCONTRACTOR_ID} </th>
        <td><form:input path="subcontractor.id" />
        	<input type="button" onClick="if (callSelectionSubcontractor()) {document.f.PERSON_ID.value=''; document.f.PERSON_CAPTION.value='';}" value="${CHOOSE}">
        	<form:input path="subcontractor.caption" />
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
		<a href="#" onclick="doAjaxPost('projectTeamPerson/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
			<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
        </td>
      </tr>
    </table>
    </form:form>
</div>
