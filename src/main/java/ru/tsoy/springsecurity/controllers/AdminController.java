package ru.tsoy.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tsoy.springsecurity.models.User;
import ru.tsoy.springsecurity.service.RoleService;
import ru.tsoy.springsecurity.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(@ModelAttribute("newUser") User user, Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("userList", userService.userList());
        model.addAttribute("user", userService.findUserByUsername(name));
        model.addAttribute("allRoles", roleService.roleList());

        return "index";
    }

    @PostMapping()
    public String createUser(@Valid User user, BindingResult result, @RequestParam("roleNames") List<String> roleNames) {
        if (result.hasErrors()) {
            return "index";
        }
        user.setRoles(roleService.findRolesByNames(roleNames));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String updateUser(@Valid User user, @RequestParam("roleNames") List<String> roleNames) {
        user.setRoles(roleService.findRolesByNames(roleNames));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
