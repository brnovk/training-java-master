:: Создание директории для размещения скомпилированных class-файлов
mkdir classes

:: Компиляция java-файлов в class-файлы
javac -encoding utf-8 -classpath ./src -d ./classes src/Runner.java

:: Копирование src.txt в "пакет" by\training\beans для статической 
:: инициализации в классе Purchase
COPY "src\by\training\beans\src.txt" "classes\by\training\beans\src.txt"

:: Запуск приложения из class-файлов 
java -classpath ./classes; Runner
pause

:: Удаление директории со скомпилированными файлами
rd "classes" /S /Q
