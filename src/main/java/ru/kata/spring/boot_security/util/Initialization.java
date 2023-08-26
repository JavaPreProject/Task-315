package ru.kata.spring.boot_security.util;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.models.Role;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.services.RoleService;
import ru.kata.spring.boot_security.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class Initialization {
    private final UserService userService;
    private final RoleService roleService;

    public Initialization(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }
    @PostConstruct
    public void addAdmin(){
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.save(roleAdmin);
        roleService.save(roleUser);
        Set<Role> adminSet = new HashSet<>();
        adminSet.add(roleAdmin);

        Set<Role> userSet = new HashSet<>();
        userSet.add(roleUser);

        User admin = new User("admin",40,"admin",adminSet);
        userService.saveUser(admin);

        User user = new User("user",35,"user",userSet);
        userService.saveUser(user);
    }
}