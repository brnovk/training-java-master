-- Create tables in MS Access

CREATE TABLE [logins] (
	[idLogin] AUTOINCREMENT,
	[name] TEXT(255),
	CONSTRAINT [PrimaryKey] PRIMARY KEY ([idLogin])
);

CREATE TABLE [tests] (
	[idTest] AUTOINCREMENT,
	[name] TEXT(255),
	CONSTRAINT [PrimaryKey] PRIMARY KEY ([idTest])
);

CREATE TABLE [results] (
	[loginId] LONG,
	[testId] LONG,
	[dat] DATETIME,
	[mark] INTEGER
);
