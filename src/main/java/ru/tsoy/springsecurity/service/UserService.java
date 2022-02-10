package ru.tsoy.springsecurity.service;

import ru.tsoy.springsecurity.models.User;

import java.util.List;


public interface UserService {
    void addUser(User user);
    List<User> userList();
    User findUserById(long id);
    void deleteUser(long id);
    User findUserByUsername(String username);

}
