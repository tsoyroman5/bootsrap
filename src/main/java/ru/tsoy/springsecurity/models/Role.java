package ru.tsoy.springsecurity.models;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;

    public Role(){}

    @Override
    public String getAuthority() {
        return name;
    }

    public void setAuthority(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
