# Notes

#### Задача проекта: реализовать методы, указанные ниже, используя Generics и коллекции, и покрыть методы тестами:

![image](https://user-images.githubusercontent.com/98683741/179904176-8171756f-dbf7-4ff2-ba63-227fcec2bdcc.png)

### Используемые технологии: IntelliJ IDEA, Kotlin, JUnit4, Generics, collections

#### Структура проекта:
- Data class Note, data class Comment
- Class DeletedCommentNotFoundException - класс исключения
- Interface CrudService<T>:

![image](https://user-images.githubusercontent.com/98683741/179905056-fc2e5871-0022-4313-810f-cb7bc823dda4.png)

- Class NoteService и class CommentService, реализующие interface CrudService<T>, примеры функций ниже:

![image](https://user-images.githubusercontent.com/98683741/179906017-9260d4b7-ee57-41f0-9ce1-4eb56add46c0.png)
![image](https://user-images.githubusercontent.com/98683741/179906473-2cb4e9e5-1970-4397-aeb8-e56b15527228.png)

- Class NoteServiceTest, тестирующий функции классов NoteService и CommentService, например:

![image](https://user-images.githubusercontent.com/98683741/179907134-a8400be8-bb0c-4fcd-a7fc-aebfa0cd8e53.png)




