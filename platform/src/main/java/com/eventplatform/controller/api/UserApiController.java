package com.eventplatform.controller.api;

import com.eventplatform.pojo.controller.DataControllerAggregator;
import com.eventplatform.pojo.klass.User;
import com.eventplatform.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    @Autowired
    private DataControllerAggregator dataControllerAggregator;
    private final UserDataRepository userDataRepository;

    public UserApiController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping(value = "/getall", produces = "application/json")
    public Iterable<User> getUsers() {
        /*try {
            UserDataController userDataController = (UserDataController) dataControllerAggregator
                    .getByType(DataControllerConstants.USER_TYPE);
            return userDataController.getAll();
        } catch (ControllerAggregatorException | EmptyControllerException e) {
            e.printStackTrace();
        }*/
        return userDataRepository.findAll();
    }

}
