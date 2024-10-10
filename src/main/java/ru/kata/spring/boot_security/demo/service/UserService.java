package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.roles.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    void update(User user);
    void delete(Long id);
}