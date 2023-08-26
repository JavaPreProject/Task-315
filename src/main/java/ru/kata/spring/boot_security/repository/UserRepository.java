package ru.kata.spring.boot_security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u left join fetch u.role where u.userName=:username")
    User findByUserName (String username);

}
