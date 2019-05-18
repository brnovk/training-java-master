<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="by.training.constans.Pages"%>
<%@page import="by.training.constans.Controllers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="header clearfix">
<div class="header-footer-content-wrapper">
	<div class="header-footer-column float-left">
		<span>
			User:
			<c:choose>
				<c:when test="${currentUser eq null}">
					Guest
				</c:when>
				<c:otherwise>
					<strong>${currentUser.login}</strong>
				</c:otherwise>
			</c:choose>
		</span>
	</div>
	<div class="header-footer-column float-center">
		<c:choose>
			<c:when test="${currentUser eq null}">
				<a href="<c:url value='<%=Pages.LOGIN_PAGE%>'/>" title="login"> login </a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='<%=Pages.PROFILE_PAGE%>'/>" title="profile"> profile </a>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="header-footer-column float-right">
		<c:choose>
			<c:when test="${currentUser eq null}">
				<a href="<c:url value='<%=Pages.REGISTER_PAGE%>'/>" title="register"> register </a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='<%=Controllers.LOGOUT_CONTROLLER%>'/>" title="logout"> logout </a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</header><!--header-->
