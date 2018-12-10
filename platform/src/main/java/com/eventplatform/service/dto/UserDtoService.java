package com.eventplatform.service.dto;

import com.eventplatform.domain.dto.UserDto;
import com.eventplatform.domain.model.User;
import com.eventplatform.exception.dataservice.EmptyDataServiceException;
import com.eventplatform.exception.dataservice.NotFoundDataServiceException;
import com.eventplatform.service.data.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// todo refactor to strategy pattern
@Scope(value = "singleton")
@Service
public class UserDtoService {

    @Autowired
    UserDataService userDataService;

    public UserDto create(int id, String strategy) {
        switch (strategy) {
            case DtoServiceConstants.PRIVATE_INFO_STRATEGY:
                UserDto userDto = new UserDto();
                try {
                    // todo refactor to factory
                    User user = userDataService.get(id);
                    userDto.setId(user.getId());
                    userDto.setCreationDate(user.getCreationDate());
                    userDto.setEmail(user.getEmail());
                    userDto.setLogin(user.getLogin());
                    userDto.setPassword(user.getPassword());
                    userDto.setName(user.getName());
                    userDto.setSurname(user.getSurname());
                    userDto.setRole(user.getRole());
                } catch (NotFoundDataServiceException e) {
                    e.printStackTrace();
                }
                return userDto;
            default:
                return new UserDto();
        }
    }

    public List<UserDto> createDtoList(String strategy) {
        List<UserDto> list = new ArrayList<>();
        try {
            // todo create stream with custom collector
            for (User u : userDataService.getAll()) {
                list.add(create(u.getId(), DtoServiceConstants.PRIVATE_INFO_STRATEGY));
            }
        } catch (EmptyDataServiceException e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(list);
    }
}
