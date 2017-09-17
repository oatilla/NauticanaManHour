<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">

	<h3> ${PAGETITLE} </h3>
	<p> ${LANGCODE} : ${record.id} ${record.caption} <a class="btn btn-primary" href="#" onClick="doAjaxGet('language/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a></p>

    <h3> ${CAPTION_TRANSLATION} </h3>

	<a class="btn btn-primary" href="#" onClick="doAjaxGet('captionTranslation/new?parentKey=${langcode}');"> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${CAPTION}</th>
        <th>${LABELUPPER}</th>
        <th>${LABELLOWER}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="captionTranslation" items="${record.captionTranslations}" varStatus="status">
        <tr>
          <td>${captionTranslation.id.caption}</td>
          <td>${captionTranslation.labelupper}</td>
          <td>${captionTranslation.labellower}</td>
          <td>
          	<a class="btn btn-primary" href="#" onClick="doAjaxGet('captionTranslation/edit?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a> &nbsp;
          	<a class="btn btn-danger" href="#" onClick="doAjaxGet('captionTranslation/delete?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
</div>