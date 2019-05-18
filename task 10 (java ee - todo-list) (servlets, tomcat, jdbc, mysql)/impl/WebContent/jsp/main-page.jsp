<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
	<title>Main page</title>
	<meta name="keywords" content="training, page, main, list, notes, todo-list"/>
	<meta name="description" content="Main page in application ToDo-List">
	<%@include file="parts/head-general-attributes.jsp"%>
</head>
<body>
<%@include file="parts/no-script-message-block.html"%>
	
<c:if test="${currentUser != null}">
	<%-- Crutch --%>
	<form style="display: none;" action="allocation-controller" id="allocation-today"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="today"></form>
	<form style="display: none;" action="allocation-controller" id="allocation-tomorrow"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="tomorrow"></form>
	<form style="display: none;" action="allocation-controller" id="allocation-someday"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="someday"></form>
	<form style="display: none;" action="allocation-controller" id="allocation-fixed"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="fixed"></form>
	<form style="display: none;" action="allocation-controller" id="allocation-recycle"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="recycle"></form>
</c:if>

<form id="mainFormId" style="display: inline;"  name="bodyForm" method="POST" accept-charset="UTF-8"
	action="<c:url value='<%= Controllers.ACTION_CONTROLLER %>'/>">
	<input type="hidden" style="border: none;" id="idNoteUpload" name="idNoteUpload" value="" />
	<input type="hidden" id="downloadId" name="downloadNoteId" value="" />
	<input type="hidden" id="deleteFileId" name="deleteFileNoteId" value="" />
	<div class="wrapper">
		<%@include file="parts/header.jsp"%>
		<c:choose>
			<c:when test="${currentUser != null}">
				<main class="content-with-action">
					<%@include file="parts/main-navigation-menu.jsp"%>
					<div class="current-message">
						Current list: <strong><c:out value="${sublist_type}"/></strong>
					</div>
					<c:if test="${not empty errorMessage}">
						<div class="error-page-block">
							<strong><c:out value="${errorMessage}"/></strong>
						</div>
					</c:if>
					<%@include file="parts/main-task-list-table.jsp"%>
				</main><!--content-->
			</c:when>
			<c:otherwise>
				<%@include file="parts/main-welcom-message-block.jsp"%>
			</c:otherwise>
		</c:choose>
	</div><!--wrapper-->
	<c:choose>
		<c:when test="${currentUser != null}">
			<footer class="footer-with-action">
			<%@include file="parts/footer-action-panel.jsp"%>
			<%@include file="parts/footer-about-panel.html"%>
			</footer><!--footer-->
		</c:when>
		<c:otherwise>
			<footer class="footer clearfix">
				<%@include file="parts/footer-about-panel.html"%>
			</footer><!--footer-->
		</c:otherwise>
	</c:choose>
</form>
</body>
</html>
