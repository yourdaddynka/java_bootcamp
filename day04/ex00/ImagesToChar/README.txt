# Создание папки target
mkdir target

# Компиляция исходных файлов без указания версии и сохранение target
javac -d ./target/ src/java/edu/school21/printer/*/*.java


# Запуск (команда компиляции, строка(два символа), путь до .bmp)
java -cp ./target edu.school21.printer.app.Main .0 /Users/letishal/Desktop/it.bmp
