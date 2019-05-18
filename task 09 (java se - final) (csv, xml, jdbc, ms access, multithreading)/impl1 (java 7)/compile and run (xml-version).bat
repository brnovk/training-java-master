:: Создание директории для размещения скомпилированных class-файлов
mkdir classes

:: Компиляция java-файлов в class-файлы
javac -encoding utf-8 -classpath ./src -d ./classes src/RunnerXml.java

:: Запуск приложения из class-файлов 
:: java -classpath ./classes; RunnerXml
:: pause

:: Создание директории для размещения "запускаемого" jar-файла и драйвера БД
mkdir runnable_jar\lib

:: Создание "запускаемого" jar-файла
jar cvmf "manifest (for xml-runner)"/manifest.mf runnable_jar/appl-xml.jar -C ./classes .
pause

:: Удаление директории со всеми созданными class-файлами
rd "classes" /S /Q 

:: Запуск созданного "запускаемого" jar-файла
java -jar runnable_jar/"appl-xml".jar
pause
