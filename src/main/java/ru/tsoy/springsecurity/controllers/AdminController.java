package ru.tsoy.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tsoy.springsecurity.models.User;
import ru.tsoy.springsecurity.service.UserService;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping()
    public String createUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "addUser";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.userList());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        return "updateUser";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            user.setId(id);
            return "updateUser";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
        userService.deleteUser(user);
        return "redirect:/admin";
    }
}
