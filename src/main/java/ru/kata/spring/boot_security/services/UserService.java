package ru.kata.spring.boot_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.dao.UserDao;
import ru.kata.spring.boot_security.models.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
@Primary
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    //private final PasswordEncoder passwordEncoder;
    private final ApplicationContext applicationContext;

    @Autowired
    public UserService(UserDao userDao, ApplicationContext applicationContext) {
        this.userDao = userDao;
        this.applicationContext = applicationContext;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(name);
    }



    public List<User> findAll() {
        return userDao.findAll();
    }


    public User findByName(String username) {
        return userDao.findByName(username);
    }


    public User getUser(Long id) {
        return userDao.getUser(id);
    }


    @Transactional
    public void saveUser(User user) {
//        user.setPassword(applicationContext.getBean(WebSecurityConfig.class)
//                .getPasswordEncoder()
//                .encode(user.getPassword()));
        user.setPassword(encodePassword(user.getPassword()));
        userDao.saveUser(user);

    }


    @Transactional
    public void updateUser(User user) {
        User existingUser = userDao.getUser(user.getId());
        if (!user.getPassword().equals(existingUser.getPassword())) {
            String encodedPassword = encodePassword(user.getPassword());
            user.setPassword(encodedPassword);
        }
        userDao.updateUser(user);
    }
    private String encodePassword(String password) {
        return applicationContext.getBean(WebSecurityConfig.class)
                .getPasswordEncoder()
                .encode(password);
    }


    public void deleteUser(Long id) {
        userDao.deleteUser(id);

    }
}
