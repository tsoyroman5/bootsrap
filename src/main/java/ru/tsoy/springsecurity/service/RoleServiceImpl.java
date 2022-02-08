package ru.tsoy.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsoy.springsecurity.models.Role;
import ru.tsoy.springsecurity.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Iterable<Role> roleList() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public List<Role> findRolesByNames(List<String> names) {
        List<Role> roleList = new ArrayList<>();
        for (String name : names) {
            roleList.add(roleRepository.findRoleByName(name));
        }
        return roleList;
    }
}
