# Day 02 – Java bootcamp

## Exercise 00 – Подписи файлов

Exercise 00: File Signatures||
---|---
Turn-in | directory	ex00
Files to turn-in |	*.java, signatures.txt
**Permissions**
Recommended types |	Java Collections API (`List<T>`, `Map<K`, `V>`, etc.), InputStream, OutputStream, FileInputStream, FileOutputStream

Например, подпись для типа файла PNG представлена ​​первыми восемью байтами файла, которые одинаковы для всех изображений PNG:
```
89 50 4E 47 0D 0A 1A 0A
```

Реализовано приложение, которое принимает на вход файл signal.txt (имя файла явно указано в коде программы). Он содержит список типов файлов и их соответствующие подписи в формате HEX, вот пример:
```
PNG, 89 50 4E 47 0D 0A 1A 0A
GIF, 47 49 46 38 37 61
```
Программа принимает полные пути к файлам на жестком диске и сохраняет тип, которому соответствует подпись файла. Результат выполнения программы должен быть записан в файл result.txt:

Example of program operation:
```
$java Program
-> C:/Users/Admin/images.png
PROCESSED
-> C:/Users/Admin/Games/WoW.iso
PROCESSED
-> 42
```
Примерное содержимое файла result.txt:
```
PNG
GIF
```


## Exercise 01 – Слова

Exercise 01: Words ||
---|---
Turn-in directory	| ex01
Files to turn-in |	*.java
**Permissions**
Recommended types |	Java Collections API, Java IO

Реализовано приложение, которое  определяет уровень сходства между текстами.Создается словарь, содержащий все слова в этих текстах,

Создается два вектора длиной, равной длине словаря. В i-й позиции каждого вектора отразим частоту встречаемости i-го слова в нашем словаре в первом и последнем текстах.

Определим сходство векторов по следующей формуле:


![formula](./pic/formula.png)


Пример работы программы:
```
$ java Program inputA.txt inputB.txt
Similarity = (result)
```

## Exercise 02 – Файловый менеджер

Exercise 02: File Manager ||
---|---
Turn-in directory |	ex02
Files to turn-in |	*.java
**Permissions**
Recommended types	| Java Collections API, Java IO, Files, Paths, etc.

Приложение отображает информацию о файлах, содержимом и размере папок, а также обеспечивает функцию перемещения/переименования. По сути, приложение эмулирует командную строку Unix-подобных систем.
Пример работы программы для каталога MAIN:
```
$ java Program --current-folder=C:/MAIN
C:/MAIN
-> ls
folder1 60 KB
folder2 90 KB
-> cd folder1
C:/MAIN/folder1
-> ls
image.jpg 10 KB
animation.gif 50 KB
-> mv image.jpg image2.jpg
-> ls
image2.jpg 10 KB
animation.gif 50 KB
-> mv animation.gif ../folder2
-> ls
image2.jpg 10 KB
-> cd ../folder2
C:/MAIN/folder2
-> ls
text.txt 10 KB
Program.java 80 KB
animation.gif 50 KB
-> exit
```
