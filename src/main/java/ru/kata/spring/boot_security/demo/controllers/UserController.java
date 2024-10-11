package ru.kata.spring.boot_security.demo.controllers;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping("/new")
    public String showFormForAdd(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("id") Long id, @RequestParam("username") String username, @RequestParam("password") String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String showFormForUpdate(@RequestParam("userId") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id, @RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.findById(id);
        user.setUsername(username);
        user.setPassword(password);
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
