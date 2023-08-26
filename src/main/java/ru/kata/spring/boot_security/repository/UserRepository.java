package ru.kata.spring.boot_security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
//    Этот запрос к базе данных MySQL означает следующее:
//            - `Select u from User u`: выбираются все записи из таблицы `User`. Псевдоним `u` присваивается таблице `User`.
//            - `left join fetch u.role`: выполняется "левое" соединение с таблицей `role`, и результаты этого соединения выбираются.
//    Также используется ключевое слово `fetch`, которое указывает на то, что связанная сущность `role` также будет извлекаться из базы данных.
//            - `where u.userName=:username`: задается условие, что значение параметра `username` должно соответствовать значению
//    `userName` в таблице `User`.
//
//    Таким образом, этот запрос выбирает все записи из таблицы `User`, а также связанные записи из таблицы `role`,
//    где значение столбца `userName` равно заданному значению `username`.

    @Query("Select u from User u left join fetch u.role where u.userName=:username")
    User findByUserName (String username);

}
//    В Java, интерфейс UserRepository наследует интерфейс JpaRepository<User, Long> для того, чтобы использовать
//        функциональность, предоставляемую JpaRepository для работы с сущностями типа User в базе данных.

//    JpaRepository является частью Spring Data JPA, и предоставляет готовые методы для выполнения типичных
//        операций с базой данных, таких как сохранение, удаление, обновление и поиск сущностей.
//        Наследуя JpaRepository, интерфейс UserRepository получает доступ к этим методам без необходимости
//        их реализации в самом интерфейсе.
//        Таким образом, интерфейс UserRepository может использовать готовую функциональность для работы с
//        базой данных сущностей User.
