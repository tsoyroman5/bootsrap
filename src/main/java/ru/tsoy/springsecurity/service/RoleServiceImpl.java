package ru.tsoy.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsoy.springsecurity.models.Role;
import ru.tsoy.springsecurity.repository.RoleRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> roleList() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> findRolesByNames(List<String> names) {
        Set<Role> roleList = new HashSet<>();
        for (String name : names) {
            roleList.add(roleRepository.findRoleByName(name));
        }
        return roleList;
    }
}
