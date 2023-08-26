package ru.kata.spring.boot_security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.models.Role;
import ru.kata.spring.boot_security.repository.RoleRepository;

import java.util.List;

@Repository
public class RoleDao {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleDao(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    public Role findRoleById(Long id) {
        return roleRepository.findById(id).get();
    }


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


    public void save(Role role) {
        roleRepository.save(role);
    }
}
