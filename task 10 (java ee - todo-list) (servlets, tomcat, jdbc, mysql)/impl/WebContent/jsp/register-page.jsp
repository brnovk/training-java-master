<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="by.training.constans.Constants"%>
<%@page import="by.training.constans.Controllers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
	<title>Register</title>
	<meta name="keywords" content="training, page, registration, todo-list" />
	<meta name="description" content="Registration page in application ToDo-List">
	<%@include file="parts/head-general-attributes.jsp"%>
</head>
<body>
	<%@include file="parts/no-script-message-block.html"%>
	
	<div class="additional-page-content-wrapper-register-page">
		<h1>Registration page.</h1>
		<h2>Enter data in the fields to register.</h2>
		<form name="register-form" method="POST" accept-charset="UTF-8"
			action="<c:url value='<%= Controllers.REGISTER_CONTROLLER %>'/>" 
			onsubmit="return registerValidation('errBlock', 'lodinId', 
				'passId', 'repeatPassId', 'emailId');">
			<div id="errBlock">
				<c:if test="${not empty errorMessage}">
					<div class="additional-page-main-error">
						<c:out value="${errorMessage}" />
					</div>
				</c:if>
			</div>
			<table>
				<tbody>
					<tr>
						<td>
							<%= Constants.LABEL_REGISTER_LOGIN %>
						</td>
						<td colspan="2">
							<input type="text" name="<%= Constants.KEY_REGISTER_LOGIN %>"
								id="lodinId" placeholder="login" tabindex="1" autofocus="autofocus">
						</td>
					</tr>
					<tr>
						<td>
							<%= Constants.LABEL_REGISTER_PASSWORD %>
						</td>
						<td colspan="2">
							<input type="password" name="<%= Constants.KEY_REGISTER_PASSWORD %>"
								id="passId" tabindex="2" placeholder="password">
						</td>
					</tr>
					<tr>
						<td>
							<%= Constants.LABEL_REGISTER_RETYPE_PASSWORD %>
						</td>
						<td colspan="2">
							<input type="password" name="<%= Constants.KEY_REGISTER_RETYPE_PASSWORD %>"
								id="repeatPassId" tabindex="3" placeholder="repeat password">
						</td>
					</tr>
					<tr>
						<td>
							<%= Constants.LABEL_REGISTER_EMAIL %>
						</td>
						<td colspan="2">
							<input type="text" name="<%= Constants.KEY_REGISTER_EMAIL %>"
								id="emailId" placeholder="email" tabindex="4">
						</td>
					</tr>
					<tr>
						<td><a href="<c:url value='<%=Controllers.ALLOCATION_CONTROLLER%>'/>" 
							tabindex="6" title="to the previous page">&lt; back</a></td>
						<td><input type="reset" name="" value="reset" tabindex="7"></td>
						<td><input type="submit" name="" value="submit" tabindex="5"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
