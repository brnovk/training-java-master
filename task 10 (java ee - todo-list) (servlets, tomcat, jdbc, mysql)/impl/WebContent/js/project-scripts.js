/**
 * Clean up unnecessary files fields. 
 * You want to reduce the size of a pass-through query.
 * 
 * @param FormName the name of the form.
 * @param idFileField id of all fields in the file.(Must be the same).
 * @param idNoteUpload id selected for the uploading note.
 */
function clearUnnecessaryFileFields(FormName, idFileField, idNoteUpload) {
	if (!document.forms[FormName]) {
		return;
	}
	var form = document.forms[FormName];
	var objFileFields = form.querySelectorAll(idFileField);
	//	var objFileFields = document.forms[FormName].elements[idFileField]; 
	// document.querySelectorAll('.verdana14.toAdd').
	if (!objFileFields) {
		return;
	}
	var selectUploadId = document.forms[FormName].elements[idNoteUpload].value;
	var countFileFields = objFileFields.length;
	for (var i = 0; i < countFileFields; i++) {
		if (selectUploadId != objFileFields[i].name) {
			objFileFields[i].value = "";
		}
	}
}


/**
 * Select or clear all flags checkboxes.
 * @param FormName the name of the form.
 * @param FieldName field name checkboxes.
 * @param isChecked true - if you want to install the flags, false - if removed.
 */
function setAllCheckBoxes(FormName, fieldName, isChecked)
{
	if(!document.forms[FormName])
		return;
	var objCheckBoxes = document.forms[FormName].elements[fieldName];
	
	if(!objCheckBoxes)
		return;
	var countCheckBoxes = objCheckBoxes.length;
	if(!countCheckBoxes)
		objCheckBoxes.checked = isChecked;
	else
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = isChecked;
}


/**
 * Sets the binary encoding form.
 * @param formName the name of the form.
 */
function setBinaryEnctype(formName) {
	if (!document.forms[formName])
		return;
	document.forms[formName].enctype = 'multipart/form-data';
}



/**
 * Sets the new value of the specified field.
 */
function setFieldValue(formName, fieldName, fieldValue) {
	if (!document.forms[formName])
		return;
	document.forms[formName][fieldName].value = fieldValue;
}



/**
 * Sends the specified form of the specified controller.
 * @param formName the name of the form.
 * @param controller controller-handler.
 */
function submitToController(formName, controller) {
	if (!document.forms[formName])
		return;
	document.forms[formName].action = controller;
	document.forms[formName].submit();
}

/**
 * trim helper for ie8 with non-breaking space
 */
function trimStr(str) {
	return str.replace(/^(\s|\u00A0)+|(\s|\u00A0)+$/g, '');
}

/**
 * Validation of the registration form fields.
 * @param idErrBlock ID block for placement of error message.
 * @param idLoginField ID field login.
 * @param idPassField ID field password.
 * @param idRepeatPassField ID field repeat of password.
 * @param idEmailField ID field email.
 * @returns {Boolean} true - if all fields meet the requirements, false - otherwise.
 */
function registerValidation(idErrBlock, idLoginField, idPassField, 
		idRepeatPassField, idEmailField) {
	var errBlock = document.getElementById(idErrBlock);
	errBlock.innerHTML = '';
	var fieldLogin = document.getElementById(idLoginField);
	var trimmedLogin = trimStr(fieldLogin.value);
	if (trimmedLogin == '') {
		errBlock.innerHTML = '<div class="additional-page-main-error">'
			+ 'Field login is empty or consists only of spaces</div>';
		fieldLogin.value = trimmedLogin;
		return false;
	}
	var fieldPass = document.getElementById(idPassField);
	var trimmedPass = trimStr(fieldPass.value);
	if (trimmedPass == '') {
		errBlock.innerHTML = '<div class="additional-page-main-error">'
			+ 'Field password is empty or consists only of spaces</div>';
		fieldPass.value = trimmedPass;
		return false;
	}
	var fieldRepeatPass = document.getElementById(idRepeatPassField);
	var trimmedRepeatPass = trimStr(fieldRepeatPass.value);
	if (trimmedRepeatPass == '') {
		errBlock.innerHTML = '<div class="additional-page-main-error">'
			+ 'Field repeat password is empty or consists only of spaces</div>';
		fieldRepeatPass.value = trimmedRepeatPass;
		return false;
	}
	var fieldEmail = document.getElementById(idEmailField);
	var trimmedEmail = trimStr(fieldEmail.value);
	if (trimmedEmail == '') {
		errBlock.innerHTML = '<div class="additional-page-main-error">'
			+ 'Field email is empty or consists only of spaces</div>';
		fieldEmail.value = trimmedEmail;
		return false;
	}
	if (trimmedPass != trimmedRepeatPass) {
		errBlock.innerHTML = '<div class="additional-page-main-error">'
			+ 'Passwords do not match</div>';
		return false;
	}
	return true;
}

/**
 * Validation of the login form fields.
 * @param idErrBlock ID block for placement of error message.
 * @param idField1 ID first field.
 * @param fieldName1 name first field.
 * @param idField2 ID second field.
 * @param fieldName2 name second field.
 * @returns {Boolean} true - if all fields meet the requirements, false - otherwise.
 */
function loginValidation(idErrBlock, idField1, fieldName1, idField2, fieldName2) {
	var errBlock = document.getElementById(idErrBlock);
	errBlock.innerHTML = '';
	var field1 = document.getElementById(idField1);
	var fieldTrimmedValue1 = trimStr(field1.value);
	if (fieldTrimmedValue1 == '') {
		errBlock.innerHTML = '<div class="additional-page-main-error">Field ' 
			+ fieldName1 + ' is empty or consists only of spaces</div>';
		field1.value = fieldTrimmedValue1;
		return false;
	}
	var field2 = document.getElementById(idField2);
	var fieldTrimmedValue2 = trimStr(field2.value);
	if (fieldTrimmedValue2 == '') {
		errBlock.innerHTML = '<div class="additional-page-main-error">Field '
			+ fieldName2 + ' is empty or consists only of spaces</div>';
		field2.value = fieldTrimmedValue2;
		return false;
	}
	return true;
}

/**
 * Validation of the field note in add-note-page.jsp
 * @param idErrBlock
 * @param idNote
 * @returns {Boolean}
 */
function addNoteValidation(idErrBlock, idNote) {
	var errBlock = document.getElementById(idErrBlock);
	errBlock.innerHTML = '';
	var note = document.getElementById(idNote);
	var trimmedNoteValue = trimStr(note.value);
	if (trimmedNoteValue == '') {
		errBlock.innerHTML = '<div class="additional-page-main-error">' 
			+ 'You need to enter text to field.</div>';
		note.value = trimmedNoteValue;
		return false;
	}
	return true;
}
