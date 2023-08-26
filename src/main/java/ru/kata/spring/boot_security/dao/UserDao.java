package ru.kata.spring.boot_security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.models.User;
import ru.kata.spring.boot_security.repository.UserRepository;

import java.util.List;


@Repository
public class UserDao implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }



    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByName(String username) {
        return userRepository.findByUserName(username);
    }


    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }


    public void updateUser(User user) {
        userRepository.save(user);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
