:: Создание директории для размещения скомпилированных class-файлов
mkdir classes

:: Компиляция java-файлов в class-файлы
javac -encoding utf-8 -classpath ./src -d ./classes src/RunnerConcurrentCsv.java

:: Запуск приложения из class-файлов 
:: java -classpath ./classes;./lib/commons-lang-2.6.jar;./lib/commons-logging-1.1.1.jar;./lib/hsqldb.jar;./lib/jackcess-2.1.3.jar;./lib/ucanaccess-3.0.6.jar RunnerConcurrentCsv
:: pause

:: Создание директории для размещения "запускаемого" jar-файла и драйвера БД
mkdir runnable_jar\lib

XCOPY /Y "lib\commons-lang-2.6.jar" "runnable_jar\lib" 
XCOPY /Y "lib\commons-logging-1.1.1.jar" "runnable_jar\lib" 
XCOPY /Y "lib\hsqldb.jar" "runnable_jar\lib" 
XCOPY /Y "lib\jackcess-2.1.3.jar" "runnable_jar\lib" 
XCOPY /Y "lib\ucanaccess-3.0.6.jar" "runnable_jar\lib" 

:: Создание "запускаемого" jar-файла
jar cvmf "manifest (for multi-threaded csv-runner)"/manifest.mf runnable_jar/appl-multi-threaded-csv.jar -C ./classes .
pause

:: Удаление директории со всеми созданными class-файлами
rd "classes" /S /Q 

:: Запуск созданного "запускаемого" jar-файла
java -jar runnable_jar/"appl-multi-threaded-csv".jar
pause
