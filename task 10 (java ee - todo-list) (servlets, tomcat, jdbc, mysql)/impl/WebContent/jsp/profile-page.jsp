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
	<title>Profile</title>
	<meta name="keywords" content="training, page, user, profile, todo-list" />
	<meta name="description" content="Page of user profile in application ToDo-List">
	<%@include file="parts/head-general-attributes.jsp"%>
</head>
<body>
	<%@include file="parts/no-script-message-block.html"%>
	
	<div class="additional-page-content-wrapper-profile">
		<h1>Information about the current user.</h1>
		<table>
			<tbody>
				<tr class="row-underline">
					<td class="label-column">
						Login:
					</td>
					<td>
		<c:out value="${currentUser eq null ? 'Error retrieving information': currentUser.login }"/>
					</td>
				</tr>
				<tr class="row-underline">
					<td class="label-column">
						Password:
					</td>
					<td>
		<c:out value="${currentUser eq null ? 'Error retrieving information': currentUser.password}"/>
					</td>
				</tr>
				<tr class="row-underline">
					<td class="label-column">
						Email:
					</td>
					<td>
		<c:out value="${currentUser eq null ? 'Information is not available': currentUser.email}"/>
					</td>
				</tr>
	
				<tr>
					<td>
						<a href="<c:url value='<%=Controllers.ALLOCATION_CONTROLLER%>'/>" 
							title="to the previous page">&lt; back</a></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>
