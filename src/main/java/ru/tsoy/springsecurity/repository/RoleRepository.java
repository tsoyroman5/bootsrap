package ru.tsoy.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsoy.springsecurity.models.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByName(String name);
}
