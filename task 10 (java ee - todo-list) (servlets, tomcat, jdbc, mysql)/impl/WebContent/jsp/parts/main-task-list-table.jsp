<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="task-list">
	<thead>
		<tr class="head">
			<c:if test="${sublist_type eq 'SOMEDAY' || sublist_type eq 'FIXED' || sublist_type eq 'RECYCLE'}">
				<th class="date-column">Date</th>
			</c:if>
			<th class="file-column">File</th>
			<th>Task</th>
			<th class="small-column">Mark</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="note" items="${sublist}" varStatus="index">
		<tr>
			<c:if test="${sublist_type eq 'SOMEDAY' || sublist_type eq 'FIXED' || sublist_type eq 'RECYCLE'}">
				<td class="date-column">
					${note.date}
				</td>
			</c:if>
			<td class="file-column clearfix">
				<c:choose>
					<c:when test="${note.file eq null}">
					
						<input class="float-left upload-delete-button" name="upl${note.id}" 
							id="${note.id}" type="button" value="Upload"
							onclick="setFieldValue('bodyForm', 'idNoteUpload', ${note.id}); 
								clearUnnecessaryFileFields('bodyForm', 'ff', 'idNoteUpload'); 
								setBinaryEnctype('bodyForm'); 
								submitToController('bodyForm', 'upload-controller');"/>
								
						<label class="button-choise-file float-left" 
							onclick="document.getElementById('fileinput${note.id}').click();">
						    Choise file
						</label>
						<input id="fileinput${note.id}" class="invisible ff" type="file" name="${note.id}"
							onchange="document.getElementById('filenameblock${note.id}').innerHTML = 
								document.getElementById('fileinput${note.id}').value;"/>
						<div id="filenameblock${note.id}" class="filename-block float-left">
							&lt;file is not selected&gt;
						</div>
					</c:when>
					<c:otherwise>
						<input class="float-left upload-delete-button" type="button" 
							name="upl${note.id}" value="Delete" id="${note.id}"
							onclick="setFieldValue('bodyForm', 'deleteFileId', ${note.id}); 
								submitToController('bodyForm', 'delete-controller');"/>
						<a href="#" class="float-left" title="${note.file.name}"
							onclick="setFieldValue('bodyForm', 'downloadId', ${note.id}); 
								submitToController('bodyForm', 'download-controller');">
							${note.file.name}
						</a>
					</c:otherwise>
				</c:choose>
			</td>
			<td>
				${note.content}
			</td>
			<td class="small-column">
				<input type="checkbox" name="selectedItem" value="${note.id}">
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>