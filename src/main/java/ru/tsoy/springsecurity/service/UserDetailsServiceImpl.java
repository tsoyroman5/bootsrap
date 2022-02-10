package ru.tsoy.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tsoy.springsecurity.models.Role;
import ru.tsoy.springsecurity.models.User;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    public UserDetailsServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);

        List<String> roleList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roleList.add(role.getAuthority());
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(roleList.toArray(new String[0]))
                .build();
    }
}
