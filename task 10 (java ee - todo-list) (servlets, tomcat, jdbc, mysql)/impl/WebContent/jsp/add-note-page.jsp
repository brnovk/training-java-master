<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="by.training.constans.Constants"%>
<%@page import="by.training.constans.Controllers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${currentUser == null}">
	<c:redirect url="<%=Controllers.ALLOCATION_CONTROLLER%>"/>
</c:if>
<!doctype html>
<html lang="en">
<head>
	<title>Add note</title>
	<meta name="keywords" content="training, page, add, note, todo-list" />
	<meta name="description" content="Page of add note in application ToDo-List">
	<%@include file="parts/head-general-attributes.jsp"%>
</head>
<body>
	<%@include file="parts/no-script-message-block.html"%>
	
	<div class="additional-page-content-wrapper-add-note">
		<h1>Add a new note.</h1>
		<h2>
			The note will be added to the item:
			<strong>
				<c:choose>
					<c:when test="${sublist_type eq 'TODAY'}">
	                    Today
	                </c:when>
					<c:when test="${sublist_type eq 'TOMORROW'}">
	                    Tomorrow
	                </c:when>
					<c:when test="${sublist_type eq 'SOMEDAY'}">
	                    Someday
	                </c:when>
					<c:otherwise>
						Illegal list type !!!
					</c:otherwise>
				</c:choose>
			</strong>
		</h2>
		<div id="errBlock">
			<c:if test="${not empty errorMessage}">
				<div class="additional-page-main-error">
					<c:out value="${errorMessage}"/>
				</div>
			</c:if>
		</div>
		<form name="add-note-form" method="POST" accept-charset="UTF-8"
			action="<c:url value='<%= Controllers.ADD_NOTE_CONTROLLER %>'/>"
			onsubmit="return addNoteValidation('errBlock', '<%= Constants.KEY_NOTE_CONTENT %>');">
			<table>
				<tbody>
					<c:if test="${sublist_type eq 'SOMEDAY'}">
						<tr>
							<td colspan="3">
								<%= Constants.LABEL_NOTE_DATE %>
								<select name="somedayDayNote">
									<c:forEach begin="1" end="31" var="somedayDayNote">
										<option>
											<c:out value="${somedayDayNote}"/>
										</option>
									</c:forEach>
								</select>
								
								<select name="somedayMonthNote">
									<option value="01">January</option>
									<option value="02">February</option>
									<option value="03">March</option>
									<option value="04">April</option>
									<option value="05">May</option>
									<option value="06">June</option>
									<option value="07">July</option>
									<option value="08">August</option>
									<option value="09">September</option>
									<option value="10">October</option>
									<option value="11">November</option>
									<option value="12">December</option>
								</select>
								
								<select name="somedayYearNote">
									<c:forEach begin="2014" end="2034" var="somedayYearNote">
										<option>
											<c:out value="${somedayYearNote}"/>
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="3">
							<textarea id="<%= Constants.KEY_NOTE_CONTENT %>" 
								name="<%= Constants.KEY_NOTE_CONTENT %>" 
								<c:choose>
									<c:when test="${sublist_type eq 'TODAY' || sublist_type eq 'TOMORROW'}">
										class="today-tommorow-textarea"
									</c:when>
									<c:when test="${sublist_type eq 'SOMEDAY'}">
										class="someday-textarea"
									</c:when>
								</c:choose>
								placeholder="Write note here" 
								autofocus="autofocus" tabindex="1"></textarea>
						</td>
					</tr>
					<tr>
						<td>
							<a href="<c:url value='<%=Controllers.ALLOCATION_CONTROLLER%>'/>" 
							title="to the previous page" tabindex="3">&lt; back</a>
						</td>
						<td><input type="reset" name="reset" value="reset" tabindex="4"></td>
						<td>
							<input type="submit" name="submit" value="submit" tabindex="2">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div><!--additional-page-content-wrapper-->

</body>
</html>
