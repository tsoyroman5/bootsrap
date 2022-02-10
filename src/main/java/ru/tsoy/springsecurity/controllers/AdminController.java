package ru.tsoy.springsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", roleService.roleList());
        return "addUser";
    }

    @PostMapping()
    public String createUser(@Valid User user, BindingResult result, @RequestParam("roleNames") List<String> roleNames) {
        if (result.hasErrors()) {
            return "addUser";
        }
        user.setRoles(roleService.findRolesByNames(roleNames));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("userList", userService.userList());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("allRoles", roleService.roleList());
        return "updateUser";
    }

    //
    @PostMapping("/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, @RequestParam("roleNames") List<String> roleNames) {
        if (result.hasErrors()) {
            user.setId(id);
            return "updateUser";
        }
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
