# Day 03 – Java bootcamp
## Exercise 00 – Multithreading?
Программа которая реализует работу двух потоков: один выводит слово "Hen", другой - "Egg". Кроме того, присутствует основной поток, который выводит слово "Человек".
```
$ java Program --count=50
Egg
Hen
Hen
...
Egg
Hen
...
Human
...
...
Human
```

## Exercise 01 – Multithreading queue

Теперь каждый поток может дать ответ только после того, как на него ответил другой поток. Предполагается, что поток с "яйцом" всегда отвечает первым.

```
$ java Program --count=50
Egg
Hen
Egg
Hen
Egg
Hen
...
```
## Exercise 02 – Multithreading computing

Необходимо вычислить сумму элементов массива с использованием нескольких потоков. Каждый поток должен вычислить свой участок в массиве. Длина каждого участка постоянна, за исключением последнего(он всегда меньше остальных). Длина массива и количество потоков передаются как аргументы командной строки:
```
$ java Program --arraySize=13 --threadsCount=3
Sum: 13
Thread 1: from 0 to 4 sum is 5
Thread 2: from 5 to 9 sum is 5
Thread 3: from 10 to 12 sum is 3
Sum by threads: 13
```

## Exercise 03 – Multithreading download

Необходимо скачать список файлов из сети, используя многопоточное скачивание. Если файлов слишком много, нужно запускать и завершать потоки эффективно, чтобы избежать излишней задержки.
```
1 https://i.pinimg.com/originals/11/19/2e/11192eba63f6f3aa591d3263fdb66bd5.jpg
2 https://pluspng.com/img-png/balloon-hd-png-balloons-png-hd-2750.png
3 https://i.pinimg.com/originals/db/a1/62/dba162603c71cac00d3548420c52bac6.png
4 https://pngimg.com/uploads/balloon/balloon_PNG4969.png
5 http://tldp.org/LDP/intro-linux/intro-linux.pdf
```
Example of the program operation:
```
$ java Program.java --threadsCount=3
Thread-1 start download file number 1
Thread-2 start download file number 2
Thread-1 finish download file number 1
Thread-1 start download file number 3
Thread-3 start download file number 4
Thread-1 finish download file number 3
Thread-2 finish download file number 2
Thread-1 start download file number 5
Thread-3 finish download file number 4
Thread-1 finish download file number 5
```