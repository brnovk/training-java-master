-- ---------------------------- create db -------------------------------------

DROP DATABASE IF EXISTS db_todolist;
CREATE DATABASE IF NOT EXISTS db_todolist;

USE db_todolist;

-- --------------------------- create tables ----------------------------------

CREATE TABLE db_todolist.users (
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	login CHAR(50) CHARACTER SET utf8 NOT NULL,
	password CHAR(50) CHARACTER SET utf8 NOT NULL,
	email CHAR(50)  CHARACTER SET utf8 NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (login),
	UNIQUE (email)
) ENGINE = InnoDB ROW_FORMAT = DEFAULT CHARACTER SET utf8;

CREATE TABLE db_todolist.files (
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	filename CHAR(255) CHARACTER SET utf8 NOT NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB ROW_FORMAT = DEFAULT CHARACTER SET utf8;

CREATE TABLE db_todolist.notes (
	id INT UNSIGNED AUTO_INCREMENT NOT NULL,
	content VARCHAR(1000) CHARACTER SET utf8 NOT NULL,
	`date` DATE NOT NULL,
	performed TINYINT(1) DEFAULT 0,
	recycle TINYINT(1) DEFAULT 0,
	fileId INT UNSIGNED NULL,
	userId INT UNSIGNED NULL,
	FOREIGN KEY (userId) 
		REFERENCES db_todolist.users (id) 
		ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (fileId) 
		REFERENCES db_todolist.files (id) 
		ON UPDATE CASCADE ON DELETE SET NULL,
	PRIMARY KEY (id)
) ENGINE = InnoDB ROW_FORMAT = DEFAULT CHARACTER SET utf8;
