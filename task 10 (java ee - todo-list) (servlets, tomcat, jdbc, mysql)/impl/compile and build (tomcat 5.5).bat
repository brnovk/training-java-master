:: Создание каталога для размещений скомпилированных class-файлов в соответсвие
:: со структурой war-файла.
mkdir WebContent\WEB-INF\classes

:: Компиляция java-файлов в class-файлы. Для сервлетов указан класс-патч в Servlet API.
javac -encoding utf-8 -classpath ./src;%CATALINA_HOME%\common\lib\servlet-api.jar -d ./WebContent/WEB-INF/classes src/by/training/constans/*.java src/by/training/controllers/*.java src/by/training/enums/*.java src/by/training/exceptions/*.java src/by/training/ifaces/*.java src/by/training/model/beans/*.java src/by/training/model/db/*.java src/by/training/model/factories/*.java src/by/training/model/impls/*.java src/by/training/utils/*.java src/by/training/utils/beans/*.java 

:: Создание директории для размещения собранного war-файла
mkdir build_war

:: Переход в каталог WebContent
cd WebContent

:: Сборка war-файла в созданном каталоге build_war
jar -cvf ../build_war/TodoList.war *

:: Удаление временной директории со скомпилированными class-файлами
rd "WEB-INF/classes" /S /Q 

pause
