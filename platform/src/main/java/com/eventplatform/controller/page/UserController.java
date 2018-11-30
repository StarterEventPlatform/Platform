package com.eventplatform.controller.page;

import com.eventplatform.exception.controller.ControllerAggregatorException;
import com.eventplatform.pojo.controller.DataControllerAggregator;
import com.eventplatform.pojo.controller.DataControllerConstants;
import com.eventplatform.pojo.controller.UserDataController;
import com.eventplatform.pojo.klass.User;
import com.eventplatform.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class UserController {
    @Autowired
    private DataControllerAggregator dataControllerAggregator;
    private final UserDataRepository userDataRepository;
    private Boolean check = false;

    public UserController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @RequestMapping("/users")
    public String handleRequest(Model model) {
        try {
            UserDataController userController = (UserDataController) dataControllerAggregator
                    .getByType(DataControllerConstants.USER_TYPE);
            if (!check) {
                for (int i = 0; i < 20; i++) {
                    User user = new User();
                    //user.setId(i);
                    user.setName(Integer.toString(i));
                    user.setSurname(Integer.toString(i));
                    user.setEmail(Integer.toString(i));
                    user.setPassword(Integer.toString(i));
                    user.setLogin(Integer.toString(i));
                    user.setCreationDate(new Date(System.currentTimeMillis() + i));
                    //userController.create(user);
                    userDataRepository.save(user);

                }
                check = true;
            }
            model.addAttribute("users", userDataRepository.findAll());
        } catch (ControllerAggregatorException /*| ControllerException| EmptyControllerException */e) {
            e.printStackTrace();
        }
        return "users";
    }

    @GetMapping("/login")
    public ModelAndView createStudent() {
        ModelAndView modelAndView = new ModelAndView("login", "user", new User());
        return modelAndView;
    }

    @PostMapping("/signin")
    public ModelAndView signIn(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("completed", "user", user);
        return modelAndView;
    }
}
