package ru.tsoy.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tsoy.springsecurity.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT user FROM User user JOIN FETCH user.roles where user.username = :username")
    User findUserByUsername(@Param("username") String username);

    @Query(value = "SELECT user FROM User user JOIN FETCH user.roles")
    List<User> findAll();

    @Query(value = "SELECT user FROM User user JOIN FETCH user.roles where user.id = :id")
    User findUserById(@Param("id") long id);

}
