<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">

	<p> ${LANGCODE} : ${record.id} ${record.caption} <a href="language/edit?id=${record.id}"> ${EDIT} </a> </p>

    <h3> ${CAPTION_TRANSLATION} </h3>

	<a href="captionTranslation/new?parentKey=${langcode}"> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${CAPTION}</th>
        <th>${LABEL_UPPER}</th>
        <th>${LABEL_LOWER}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="captionTranslation" items="${record.captionTranslations}" varStatus="status">
        <tr>
          <td>${captionTranslation.id.caption}</td>
          <td>${captionTranslation.labelupper}</td>
          <td>${captionTranslation.labellower}</td>
          <td>
          	<a href="captionTranslation/edit?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}"> ${EDIT}" </a> &nbsp;
          	<a href="captionTranslation/delete?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    
</div>