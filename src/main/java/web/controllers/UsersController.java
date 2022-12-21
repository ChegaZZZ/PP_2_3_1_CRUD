package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "getAllUsers";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "getUser";
    }

    @GetMapping("/new")
    public String getFormToCreateNewUser(@ModelAttribute("user") User user) {
        return "createNewUser";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String getFormToUpdateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "editUser";
    }

    @PatchMapping("{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}