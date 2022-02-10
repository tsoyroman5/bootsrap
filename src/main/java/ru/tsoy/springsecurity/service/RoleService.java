package ru.tsoy.springsecurity.service;

import ru.tsoy.springsecurity.models.Role;


import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> roleList();
    Set<Role> findRolesByNames(List<String> roles);
}
