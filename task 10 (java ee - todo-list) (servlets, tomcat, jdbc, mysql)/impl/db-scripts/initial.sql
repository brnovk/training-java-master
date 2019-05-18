
USE db_todolist;

-- --------------------------- initial users ----------------------------------

INSERT INTO db_todolist.users (
	login, password, email)
VALUES 
	('admin', 'admin', 'admin@examle.com'),
	('lap', 'lap', 'lap@examle.com');
