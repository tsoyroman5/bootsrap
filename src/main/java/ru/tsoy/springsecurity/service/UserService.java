package ru.tsoy.springsecurity.service;

import ru.tsoy.springsecurity.models.User;

import java.util.Optional;

public interface UserService {
    void addUser(User user);
    Iterable<User> userList();
    Optional<User> findById(long id);
    void deleteUser(User user);
    Optional<User> findUserByUsername(String username);
}
