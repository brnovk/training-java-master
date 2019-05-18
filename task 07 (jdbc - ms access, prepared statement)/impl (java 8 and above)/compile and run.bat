:: Создание директории для размещения скомпилированных class-файлов
mkdir classes

:: Компиляция java-файлов в class-файлы
javac -encoding utf-8 -classpath ./src -d ./classes src/Runner.java

:: Запуск приложения из class-файлов 
:: java -classpath ./classes;./lib/commons-lang-2.6.jar;./lib/commons-logging-1.1.1.jar;./lib/hsqldb.jar;./lib/jackcess-2.1.3.jar;./lib/ucanaccess-3.0.6.jar Runner
:: pause

:: Создание директории для размещения "запускаемого" jar-файла и драйвера БД
mkdir runnable_jar\lib

XCOPY "lib\commons-lang-2.6.jar" "runnable_jar\lib" 
XCOPY "lib\commons-logging-1.1.1.jar" "runnable_jar\lib" 
XCOPY "lib\hsqldb.jar" "runnable_jar\lib" 
XCOPY "lib\jackcess-2.1.3.jar" "runnable_jar\lib" 
XCOPY "lib\ucanaccess-3.0.6.jar" "runnable_jar\lib" 

:: Создание "запускаемого" jar-файла
jar cvmf manifest.mf runnable_jar/appl.jar -C ./classes .
pause

:: Удаление директории со всеми созданными class-файлами
rd "classes" /S /Q 

:: Запуск созданного "запускаемого" jar-файла
java -jar runnable_jar/"appl".jar
pause