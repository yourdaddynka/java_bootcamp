# Day 05 – Java bootcamp

## Exercise 00 – Tables & Entities
Необходимо создать таблицы SQL для моделей Пользователь, Чат, Сообщение, а также классы Java с правильной реализацией реляционных ссылок и переопределением методов equals(), hashCode() и toString().
## Exercise 01 – Read/Find
Необходимо реализовать интерфейс MessagesRepository с методом 
```java
Optional<Course> findById(Long id);
```
 который будет возвращать объект сообщения с заполненными полями автора и чатрума. Затем нужно создать реализацию MessagesRepositoryJdbcImpl, которая будет использовать библиотеку HikariCP для работы с базой данных.
```
$ java Program
Enter a message ID
-> 5
Message : {
  id=5,
  author={id=7,login=“user”,password=“user”,createdRooms=null,rooms=null},
  room={id=8,name=“room”,creator=null,messages=null},
  text=“message”,
  dateTime=01/01/01 15:69
}
```

## Exercise 02 – Create/Save
Метод save(Message message) в MessagesRepository должен сохранить сообщение, а также присвоить ID автору и комнате чата. Если у автора и комнаты нет существующего ID или оно равно null, выбросить исключение NotSavedSubEntityException.


## Exercise 03 – Update
Метод save(Message message) в MessagesRepository сохраняет сообщение, присваивая ID автору и комнате чата. Если у них нет существующего ID или оно null, выбрасывается исключение NotSavedSubEntityException.

# Chapter VIII
### Exercise 04 – Find All
Реализуйте интерфейс UsersRepository, добавив в него метод 
```java
List<User> findAll(int page, int size).
```
Создайте класс UsersRepositoryJdbcImpl, который реализует интерфейс UsersRepository и принимает DataSource в качестве параметра конструктора.
В методе findAll(int page, int size) класса UsersRepositoryJdbcImpl выполните только ОДИН запрос к базе данных для получения данных с пагинацией.
У каждого пользователя должен быть список созданных им чат-комнат и список чат-комнат, в которых он участвует.