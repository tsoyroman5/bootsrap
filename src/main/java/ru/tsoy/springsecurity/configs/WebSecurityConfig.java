package ru.tsoy.springsecurity.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tsoy.springsecurity.models.Role;
import ru.tsoy.springsecurity.models.User;
import ru.tsoy.springsecurity.service.RoleService;
import ru.tsoy.springsecurity.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    @Autowired
    @Lazy
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin*").hasRole("ADMIN")
                .antMatchers("/user").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler).permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createUsersAfterStartup() {
        if (!userService.userList().iterator().hasNext()) {
            Role role_admin = new Role();
            Role role_user = new Role();
            role_admin.setAuthority("ADMIN");
            role_user.setAuthority("USER");

            List<Role> rolesAdmin = new ArrayList<>();
            rolesAdmin.add(role_user);
            rolesAdmin.add(role_admin);

            List<Role> rolesUser = new ArrayList<>();
            rolesUser.add(role_user);

            User defaultAdmin = new User();
            defaultAdmin.setName("Roman");
            defaultAdmin.setSurname("Tsoy");
            defaultAdmin.setAge(30);
            defaultAdmin.setUsername("admin");
            defaultAdmin.setPassword("admin");
            defaultAdmin.setRoles(rolesAdmin);

            User defaultUser = new User();
            defaultUser.setName("Milana");
            defaultUser.setSurname("Tsoy");
            defaultUser.setAge(21);
            defaultUser.setUsername("user");
            defaultUser.setPassword("user");
            defaultUser.setRoles(rolesUser);

            List<User> usersAdmin = new ArrayList<>();
            List<User> usersUser = new ArrayList<>();

            usersAdmin.add(defaultAdmin);
            usersUser.add(defaultAdmin);
            usersUser.add(defaultUser);

            role_admin.setUsers(usersAdmin);
            role_user.setUsers(usersUser);


            userService.addUser(defaultAdmin);
            userService.addUser(defaultUser);
            roleService.addRole(role_admin);
            roleService.addRole(role_user);

        }
    }
}