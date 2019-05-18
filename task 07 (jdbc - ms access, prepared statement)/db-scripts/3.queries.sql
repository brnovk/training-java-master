-- Queries in MS Access for execute task 

SELECT abs(int((x1-x2)+0.5)) AS len, COUNT(*) AS num 
FROM Coordinates 
GROUP BY abs(int((x1-x2)+0.5)) 
ORDER BY abs(int((x1-x2)+0.5)) DESC;

SELECT * 
FROM Frequencies 
WHERE len > num;

INSERT INTO Frequencies 
VALUES (?,?);

DELETE * 
FROM Frequencies;
