<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="by.training.constans.Pages"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="action-list">
	<ul class="clearfix">
		<c:choose>
			<c:when test="${sublist_type eq 'TODAY' || sublist_type eq 'TOMORROW' || sublist_type eq 'SOMEDAY'}">
				<li class="float-left">
					<a href="<c:url value='<%=Pages.ADD_NOTE_PAGE%>'/>" title="Add task">Add task</a>
				</li>
				<li class="float-center">
					<input type="submit" name="performed" value="Fix" title="Fix" class="link">
				</li>
				<li class="float-right">
					<input type="submit" name="recycle" value="Remove to recycle" title="Remove to recycle" class="link">
				</li>
			</c:when>
			<c:when test="${sublist_type eq 'FIXED'}">
				<li class="float-center">
					<input type="submit" name="recycle" value="Remove to recycle" class="link">
				</li>
			</c:when>
			<c:when test="${sublist_type eq 'RECYCLE'}">
				<li class="float-left">
					<input type="submit" name="restore" value="Restore to list 'Today'" title="Restore to list 'Today'" class="link">
				</li>
				<li class="float-center">
					<input type="submit" name="remove" value="Erasing" title="Erasing" class="link">
				</li>
				<li class="float-right">
					<input type="submit" name="remove" value="Erasing all" title="Erasing all" class="link"
										onclick="setAllCheckBoxes('bodyForm', 'selectedItem', true);">
				</li>
			</c:when>
		</c:choose>
	</ul>
</div><!--action-list-->