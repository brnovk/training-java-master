<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="by.training.constans.Constants"%>
<%@page import="by.training.constans.Controllers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
	<title>Login</title>
	<meta name="keywords" content="training, page, authorization, todo-list" />
	<meta name="description" content="Page of authorization in application ToDo-List">
	<%@include file="parts/head-general-attributes.jsp"%>
</head>
<body>
	<%@include file="parts/no-script-message-block.html"%>

	<div class="additional-page-content-wrapper-login-page">
		<h1>Login page.</h1>
		<h2>Enter your username and password to access the site.</h2>
		<div id="errBlock">
			<c:if test="${not empty errorMessage}">
				<div class="additional-page-main-error">
					<c:out value="${errorMessage}" />
				</div>
			</c:if>
		</div>
		
		<form method="POST" accept-charset="UTF-8"
			action="<c:url value='<%= Controllers.LOGIN_CONTROLLER %>'/>"
			onsubmit="return loginValidation('errBlock', 'lodinId', 'login', 'passId', 'password');">
			<table>
				<tbody>
					<tr>
						<td>
							<%= Constants.LABEL_ENTRANCE_LOGIN %>
						</td>
						<td colspan="2">
							<input type="text" name="<%= Constants.KEY_ENTRANCE_LOGIN %>"
								id="lodinId" placeholder="login" tabindex="1" autofocus="autofocus">
						</td>
					</tr>
					<tr>
						<td>
							<%= Constants.LABEL_ENTRANCE_PASSWORD %>
						</td>
						<td colspan="2">
							<input type="password" name="<%= Constants.KEY_ENTRANCE_PASSWORD %>"
								id="passId" tabindex="2" placeholder="password">
						</td>
					</tr>
					<tr>
						<td><a href="<c:url value='<%=Controllers.ALLOCATION_CONTROLLER%>'/>" 
							tabindex="5" title="to the previous page">&lt; back</a></td>
						<td><input type="reset" name="" value="reset" tabindex="4"></td>
						<td><input type="submit" name="" value="submit" tabindex="3"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
