
USE db_todolist;

-- ------------------------ filling of notes [demo] ---------------------------

INSERT INTO db_todolist.notes(content, `date`, performed, recycle, fileId, userId) 
VALUES ('to need creating and testing on scripts of build', '2016-08-21', 1, 0, null, 
	(SELECT id FROM users WHERE login='lap'));

INSERT INTO notes(content, `date`, performed, recycle, fileId, userId) 
VALUES ('to need remove debug-information in the project', '2016-08-21', 1, 0, null, 
	(SELECT id FROM users WHERE login='lap'));

INSERT INTO notes(content, `date`, performed, recycle, fileId, userId) 
VALUES ('need to view the source code of other projects in the repository', '2016-08-21', 
	0, 0, null, (SELECT id FROM users WHERE login='lap'));

INSERT INTO notes(content, `date`, performed, recycle, fileId, userId) 
VALUES ('need to add reading settings to connect to the database from the properties file',
	'2016-08-21', 1, 0, null, (SELECT id FROM users WHERE login='lap'));
