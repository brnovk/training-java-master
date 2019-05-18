-- Queries in MS Access for execute task 

-- Clear logins
DELETE * FROM logins;

-- Clear tests
DELETE * FROM tests;

-- Clear results
DELETE * FROM results;

-- Select login id
SELECT idLogin FROM logins WHERE name = ?;

-- Select test id
SELECT idTest FROM tests WHERE name = ?;

-- Insert login
INSERT INTO logins(name) VALUES(?);

-- Insert test
INSERT INTO tests(name) VALUES(?);

-- Insert result
INSERT INTO results(loginId, testId, dat, mark) VALUES(?,?,?,?);

-- Select AVG mark
SELECT avg(mark), name 
FROM results, logins 
WHERE loginId = idLogin 
GROUP BY loginId, name 
ORDER BY 1 DESC;

-- Select current mount
SELECT lgn.name, tst.name, dat, mark 
FROM ( 
  (results rsl INNER JOIN logins lgn ON rsl.loginId = lgn.idLogin) 
  INNER JOIN tests tst ON rsl.testId = tst.idTest) 
WHERE Month(dat) = Month(Date()) 
AND Year(dat) = Year(Date()) 
ORDER BY 3;
