# Создание папки target
rm -rf target && mkdir target

# Компиляция без указания версии и копирование .bmp файлов в target
cp -R src/resources/ target && javac -d ./target/ src/java/edu/school21/printer/*/*.java

# Создание .jar файла и перемещение в folder
jar cfm target/ImagesToChar-folder.jar src/manifest.txt -C ./target .

# Запуск (команда запуска, строка(два символа), путь до .bmp)
java -jar target/ImagesToChar-folder.jar .# src/resources/it.bmp