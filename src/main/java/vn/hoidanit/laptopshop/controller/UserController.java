package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("test", "test");
        model.addAttribute("meap", "From controller with model");
        return "home_page";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        // Add a User object to the model for form binding
        model.addAttribute("user", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(@ModelAttribute("user") User user, Model model) {
        System.out.println("Received user: " + user);
        this.userService.handleCreateUser(user);
        return "home_page";
    }
}
