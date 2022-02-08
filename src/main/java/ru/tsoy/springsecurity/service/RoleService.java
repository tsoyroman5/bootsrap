package ru.tsoy.springsecurity.service;

import ru.tsoy.springsecurity.models.Role;
import ru.tsoy.springsecurity.models.User;

import java.util.List;

public interface RoleService {
    void addRole(Role role);
    Iterable<Role> roleList();
    Role findRoleByName(String name);
    List<Role> findRolesByNames(List<String> roles);
}
