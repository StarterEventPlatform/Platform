package com.eventplatform.controller.api;

import com.eventplatform.domain.model.User;
import com.eventplatform.repository.UserDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserDataRepository userDataRepository;

    public UserApiController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping(value = "/getall", produces = "application/json")
    public Iterable<User> getUsers() {
        return userDataRepository.findAll();
    }

}
