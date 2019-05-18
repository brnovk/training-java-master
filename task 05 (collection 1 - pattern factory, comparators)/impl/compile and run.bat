:: Компиляция java-файлов в class-файлы
javac -encoding utf-8 -classpath ./src -d ./src src/Runner.java

:: Запуск приложения из class-файлов 
java -classpath ./src; Runner in addon PurchaseComparatorV1
pause

:: Удаление из директории всех созданных class-файлов
del /S *.class
:: pause
