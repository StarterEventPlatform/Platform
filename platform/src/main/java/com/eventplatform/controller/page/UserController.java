package com.eventplatform.controller.page;

import com.eventplatform.exception.controller.ControllerException;
import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.pojo.controller.UserDataController;
import com.eventplatform.pojo.klass.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired
    private UserDataController userDataController;

    @RequestMapping("/users")
    public String handleUsersRequest(Model model) {
        try {
            model.addAttribute("users", userDataController.getAll());
        } catch (EmptyControllerException e) {
            model.addAttribute("users", new ArrayList<>());
        }
        return "users";
    }

    @GetMapping("/signin")
    public String signInShow() {
        return "signin";
    }

    @PostMapping("/signin")
    public ModelAndView signIn(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("completed", "user", user);
        return modelAndView;
    }

    @GetMapping(value = "/signup")
    public String signUpShow() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUpSubmit(@RequestParam(name = "login") String login,
                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "surname") String surname,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password) {
        try {
            userDataController.create(name, surname, login, email, password);
        } catch (ControllerException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }
}
