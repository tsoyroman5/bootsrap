package ru.tsoy.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tsoy.springsecurity.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
