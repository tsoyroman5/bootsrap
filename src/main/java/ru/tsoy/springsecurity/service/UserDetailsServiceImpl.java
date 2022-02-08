package ru.tsoy.springsecurity.service;

import jdk.jfr.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tsoy.springsecurity.models.Role;
import ru.tsoy.springsecurity.models.User;
import ru.tsoy.springsecurity.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

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
        else {
//            Role role_admin = new Role("ADMIN");
//            Role role_user = new Role("USER");
//
//            roleService.addRole(role_admin);
//            roleService.addRole(role_admin);
//
//            List<Role> rolesAdmin = new ArrayList<>();
//            rolesAdmin.add(role_user);
//            rolesAdmin.add(role_admin);
//
//            List<Role> rolesUser = new ArrayList<>();
//            rolesUser.add(role_user);
//
//
//            User defaultAdmin = new User();
//            defaultAdmin.setUsername("admin");
//            defaultAdmin.setPassword("admin");
//            defaultAdmin.setRoles(rolesAdmin);
//
//            User defaultUser = new User();
//            defaultUser.setUsername("user");
//            defaultUser.setPassword("user");
//            defaultUser.setRoles(rolesUser);
//            userService.addUser(defaultAdmin);
//            userService.addUser(defaultUser);

            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
