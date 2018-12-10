package com.eventplatform.controller.page;

import com.eventplatform.domain.model.User;
import com.eventplatform.exception.dataservice.DataServiceException;
import com.eventplatform.service.data.UserDataService;
import com.eventplatform.service.dto.DtoServiceConstants;
import com.eventplatform.service.dto.UserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPageController {
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private UserDtoService userDtoService;

    @RequestMapping("/users")
    public String handleUsersRequest(Model model) {
        // todo add role
        model.addAttribute("users", userDtoService.createDtoList(DtoServiceConstants.PRIVATE_INFO_STRATEGY));
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
            userDataService.create(name, surname, login, email, password);
        } catch (DataServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }
}
