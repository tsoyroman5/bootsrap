package ru.tsoy.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsoy.springsecurity.models.User;
import ru.tsoy.springsecurity.repository.UserRepository;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> userList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(userRepository.findUserById(id));
    }

    @Override
    public User findUserByUsername(String username) {return userRepository.findUserByUsername(username);}
}
