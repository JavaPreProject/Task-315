package ru.kata.spring.boot_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.dao.RoleDao;
import ru.kata.spring.boot_security.models.Role;

import java.util.List;

@Service
public class RoleService {

    private final RoleDao roleDao;


    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }



    public Role findRoleById(Long id) {
        return roleDao.findRoleById(id);
    }


    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }


    public List<Role> getRoles() {
        return roleDao.getRoles();
    }


    @Transactional
    public void save(Role role) {
        roleDao.save(role);

    }
}
