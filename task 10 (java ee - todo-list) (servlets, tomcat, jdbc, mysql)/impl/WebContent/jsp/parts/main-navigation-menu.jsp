<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="todayDate" value="<%=new java.util.Date()%>"/>
<c:set var="tomorrowDate" value="<%=new Date(new Date().getTime() + 60*60*24*1000)%>"/>
<fmt:formatDate pattern="dd.MM" value="${todayDate}" var="normalizeTodayDate"/>
<fmt:formatDate pattern="dd.MM" value="${tomorrowDate}" var="normalizeTomorrowDate"/>
<nav class="menu">
	<ul class="clearfix">
		<li class="float-left">
			<c:set var="todayLabel" value="Today ${normalizeTodayDate}"/>
			<a <c:if test="${sublist_type eq 'TODAY'}"> class="active" </c:if>
				 href="#" onclick="document.getElementById('allocation-today').submit();"
				 title="Today">${todayLabel}</a>
		</li>
		<li class="float-left">
			<c:set var="tomorrowLabel" value="Tomorrow ${normalizeTomorrowDate}"/>
			<a <c:if test="${sublist_type eq 'TOMORROW'}"> class="active" </c:if>
				href="#" title="Tomorrow" onclick="document.getElementById('allocation-tomorrow').submit();"
				title="Today">${tomorrowLabel}</a>
		</li>
		<li class="float-left">
			<a <c:if test="${sublist_type eq 'SOMEDAY'}"> class="active" </c:if>
				href="#" title="Someday"
				onclick="document.getElementById('allocation-someday').submit();">Someday</a>
		</li>
		<li class="float-left">
			<a <c:if test="${sublist_type eq 'FIXED'}"> class="active" </c:if>
				href="#" title="Fixed"
				onclick="document.getElementById('allocation-fixed').submit();">Fixed</a>
		</li>
		<li class="float-left">
			<a <c:if test="${sublist_type eq 'RECYCLE'}"> class="active" </c:if>
				href="#" title="Recycle Bin"
				onclick="document.getElementById('allocation-recycle').submit();">Recycle Bin</a>
		</li>
	</ul>
</nav><!--menu-->