<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="by.training.constans.Pages"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main class="content">
	<div class="welcome-block">
		<h1>Welcome</h1>
		<p>
			This site is intended for journaling to-do list.<br>
			To continue further work,
			<a href="<c:url value='<%=Pages.LOGIN_PAGE%>'/>" title="log on">log on</a>
			to the website using your account or
			<a href="<c:url value='<%=Pages.REGISTER_PAGE%>'/>" title="register">register</a>.
		</p>
	</div>
</main><!--content-->