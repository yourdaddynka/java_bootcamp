# Day 01 – Java bootcamp
### OOP/Collections

*Сегодня я научился правильно моделировать работу различных инкассаций и создавать полномасштабное приложение для денежных переводов.*

## Introduction to exercises
Внутри системы все денежные операции хранятся в виде пар дебет/кредит. Например, Джон перевел Майку 500 долларов. Система сохраняет транзакцию для обоих пользователей:
```java
John -> Mike, -500, OUTCOME, transaction ID
Mike -> John, +500, INCOME, transaction ID
```

## Exercise 00 – Модель

Exercise 00: Models ||
---|---
Turn-in directory |	ex00
Files to turn-in |	User.java, Transaction.java, Program.java

Разработать базовые модели предметной области, а именно классы пользователей и транзакций.

Таким образом, для класса User характерен следующий набор состояний (полей):
-	Identifier
-	Name
-	Balance


Таким образом, для класса Transaction характерен следующий набор состояний (полей):
-	Identifier
-	Recipient (User type)
-	Sender (User type)
-	Transfer category (debits, credits)
-	Transfer amount

Проверены начальный баланс пользователя (он не может быть отрицательным), а также баланс исходящих (только отрицательные суммы) и входящих (только положительные суммы) транзакций (использование методов get/set).

## Exercise 01 – ID Generator

Exercise 01: ID Generator||
---|---
Turn-in directory |	ex01
Files to turn-in |	UserIdsGenerator.java, User.java, Program.java

Итак, класс UserIdsGenerator содержит в качестве своего состояния последний сгенерированный идентификатор.
Поведение UserIdsGenerator определяется методом intgenerId(), который возвращает вновь сгенерированный идентификатор при каждом вызове(шаблон Singleton).
В онструктор класса User необходимо добавлена временная логика инициализации идентификатора

```java
public User(...) {
	this.id = UserIdsGenerator.getInstance().generateId();
}
```

## Exercise 02 – List of Users

Exercise 02: List of Users||
---|---
Turn-in directory	| ex02
Files to turn-in |	UsersList.java, UsersArrayList.java, User.java,Program.java, etc.
**All permissions from the previous exercise  + throw can be used.**

Now we need to implement a functionality for storing users while the program runs. 
Чтобы обеспечить большую гибкость, давайте определим интерфейс UsersList, который описывает следующее поведение:

-	Add a user
-	Retrieve a user by ID
-	Retrieve a user by index
-	Retrieve the number of users

Мы также реализуем класс UsersArrayList, реализующий интерфейс UsersList.

Этот класс должен использовать массив для хранения пользовательских данных. Размер массива по умолчанию равен 10. Если массив полон, его размер увеличивается вдвое. Метод добавления пользователя помещает объект типа Пользователь в первую пустую (свободную) ячейку массива.


## Exercise 03 – List of Transactions

Exercise 03: List of Transactions||
---|---
Turn-in directory |	ex03
Files to turn-in | TransactionsList.java, TransactionsLinkedList.java, User.java, Program.java, etc.


Поскольку количество операций создания транзакций может быть очень большим, нам нужен метод хранения, чтобы избежать дорогостоящего увеличения размера массива.

В этой задаче мы предлагаем вам создать интерфейс TransactionsList, описывающий следующее поведение:
- Add a transaction
- Remove a transaction by ID (in this case, UUID string identifier is used)
- Transform into array (ex. Transaction[] toArray())

Список транзакций реализован в виде связанного списка (LinkedList) в классе TransactionsLinkedList. Поэтому каждая транзакция должна содержать поле со ссылкой на следующий объект транзакции. Если предпринята попытка удалить транзакцию с несуществующим идентификатором, будет исключение времени выполнения TransactionNotFoundException
Добавлено поле транзакций типа TransactionsList в класс User, чтобы каждый пользователь мог хранить список своих транзакций.
Транзакция должна быть добавлена ​​с помощью ОДИНОЧНОЙ операции (O(1))

## Exercise 04 – Business Logic

Exercise 04: Business Logic||
---|---
Turn-in directory |	ex04
Files to turn-in |	TransactionsService.java, Program.java, etc.

В этом случае класс TransactionsService должен содержать поле типа UserList для взаимодействия с пользователем и обеспечивать следующий функционал:

- Добавление пользователя

- Получение баланса пользователя

- Выполнение транзакции перевода (указываются идентификаторы пользователей и сумма перевода). В этом случае создаются две транзакции типа ДЕБЕТ/КРЕДИТ и добавляются к получателю и отправителю. Идентификаторы обеих транзакций должны быть равны

- Получение переводов конкретного пользователя (возвращается МАССИВ переводов). Удаление транзакции по идентификатору для конкретного пользователя (указывается идентификатор транзакции и идентификатор пользователя)

- Проверить достоверность транзакций (возвращает МАССИВ непарных транзакций).

В случае попытки перевода суммы, превышающей остаточный баланс пользователя, должно быть выдано исключение времени выполнения IllegalTransactionException.

# Chapter IX
### Exercise 05 – Menu

Exercise 05: Menu||
---|---
Turn-in directory |	ex05
Files to turn-in |	Menu.java, Program.java, etc.
**All permissions from the previous exercise can be used, as well as try/catch**

В результате должно получиться работающее приложение с консольным меню. Функциональность меню должна быть реализована в соответствующем классе с полем ссылки на TransactionsService.
Каждый пункт меню должен сопровождаться номером команды, введенной пользователем для вызова действия.
Приложение должно поддерживать два режима запуска — производственный (стандартный режим) и режим разработки (где информация о передаче для конкретного пользователя может быть удалена по идентификатору пользователя, а также может быть запущена функция, проверяющая достоверность всех передач).
Если генерируется исключение, появится сообщение, содержащее информацию об ошибке, и пользователю будет предоставлена ​​возможность ввести действительные данные.

The application operation scenario is as follows (the program must carefully follow this output example):

```
$ java Program --profile=dev

1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Jonh 777
User with id = 1 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 1
Enter a user name and a balance
-> Mike 100
User with id = 2 is added
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 100
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 150
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 3
Enter a sender ID, a recipient ID, and a transfer amount
-> 1 2 50
The transfer is completed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 2
Enter a user ID
-> 2
Mike - 400
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 4
Enter a user ID
-> 1
To Mike(id = 2) -100 with id = cc128842-2e5c-4cca-a44c-7829f53fc31f
To Mike(id = 2) -150 with id = 1fc852e7-914f-4bfd-913d-0313aab1ed99
TO Mike(id = 2) -50 with id = ce183f49-5be9-4513-bd05-8bd82214eaba
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 5
Enter a user ID and a transfer ID
-> 1 1fc852e7-914f-4bfd-913d-0313aab1ed99
Transfer To Mike(id = 2) 150 removed
---------------------------------------------------------
1. Add a user
2. View user balances
3. Perform a transfer
4. View all transactions for a specific user
5. DEV – remove a transfer by ID
6. DEV – check transfer validity
7. Finish execution
-> 6
Check results:
Mike(id = 2) has an unacknowledged transfer id = 1fc852e7-914f-4bfd-913d-0313aab1ed99 from John(id = 1) for 150
```
