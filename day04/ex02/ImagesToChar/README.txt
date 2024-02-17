# Создание папки target и извлечение .jar файлов из lib в target
rm -rf target && mkdir target && cd target && jar xf ../lib/JCDP-4.0.0.jar && jar xf ../lib/jcommander-1.72.jar && cd ..

# копирование в target
javac -cp lib/JCDP-4.0.0.jar:lib/jcommander-1.72.jar: `find ./src -name "*.java"` -d ./target

# копирование .bmp файла в Desktop
cp src/resources/it.bmp ~/Desktop/

# Создание .jar файла и перемещение в folder
jar cfm target/ImagesToChar-folder.jar src/manifest.txt -C ./target .

# Запуск (команда запуска, строка(два флага))
java -jar target/ImagesToChar-folder.jar --white=RED --black=GREEN