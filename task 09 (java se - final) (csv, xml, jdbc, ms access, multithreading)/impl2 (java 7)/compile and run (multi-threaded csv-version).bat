:: Создание директории для размещения скомпилированных class-файлов
mkdir classes

:: Компиляция java-файлов в class-файлы
javac -encoding utf-8 -classpath ./src -d ./classes src/RunnerConcurrentCsv.java

:: Запуск приложения из class-файлов 
:: java -classpath ./classes; RunnerConcurrentCsv
:: pause

:: Создание директории для размещения "запускаемого" jar-файла
mkdir runnable_jar

:: Создание "запускаемого" jar-файла
jar cvmf "manifest (for multi-threaded csv-runner)"/manifest.mf runnable_jar/appl-multi-threaded-csv.jar -C ./classes .
pause

:: Удаление директории со всеми созданными class-файлами
rd "classes" /S /Q 

:: Запуск созданного "запускаемого" jar-файла
java -jar runnable_jar/"appl-multi-threaded-csv".jar
pause
