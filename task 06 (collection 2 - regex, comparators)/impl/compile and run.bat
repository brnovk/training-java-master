:: Создание директории для размещения скомпилированных class-файлов
mkdir classes

:: Компиляция java-файлов в class-файлы
javac -encoding utf-8 -classpath ./src -d ./classes src/Runner.java


COPY "src\in1.txt" "classes\in1.txt"
COPY "src\in2.txt" "classes\in2.txt"
COPY "src\in3.txt" "classes\in3.txt"
COPY "src\in4.txt" "classes\in4.txt"
COPY "src\in5.txt" "classes\in5.txt"

:: Запуск приложения из class-файлов 
:: java -classpath ./classes; Runner
:: pause

:: Создание директории для размещения "запускаемого" jar-файла
mkdir runnable_jar

:: Создание "запускаемого" jar-файла
jar cvmf manifest.mf runnable_jar/appl.jar -C ./classes .
pause

:: Удаление из директории всех созданных class-файлов
rd "classes" /S /Q 

:: Запуск созданного "запускаемого" jar-файла
java -jar runnable_jar/"appl".jar
pause
