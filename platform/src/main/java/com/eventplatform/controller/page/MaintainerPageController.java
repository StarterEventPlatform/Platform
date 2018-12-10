package com.eventplatform.controller.page;

import com.eventplatform.exception.dataservice.DataServiceException;
import com.eventplatform.exception.dataservice.EmptyDataServiceException;
import com.eventplatform.service.data.GeoPositionDataService;
import com.eventplatform.service.data.MaintainerDataService;
import com.eventplatform.service.data.UserDataService;
import com.eventplatform.domain.model.GeoPosition;
import com.eventplatform.domain.model.Maintainer;
import com.eventplatform.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class MaintainerPageController {
    @Autowired
    private MaintainerDataService maintainerDataService;
    @Autowired
    private UserDataService userDataController;
    @Autowired
    private GeoPositionDataService geoPositionDataController;

    @RequestMapping("/maintainers")
    public String handleRequest(Model model){
        try {
            model.addAttribute("maintainers", maintainerDataService.getAll());
        } catch (EmptyDataServiceException e) {
            model.addAttribute("maintainers", new ArrayList<>());
        }
        return "maintainers";
    }

    @GetMapping("/maintainer")
    public ModelAndView createMaintainer(Model model){
        ModelAndView modelAndView = new ModelAndView("maintainer", "maintainer", new Maintainer());
        try {
            model.addAttribute("users", userDataController.getAll());
            model.addAttribute("geoPositions", geoPositionDataController.getAll());
        } catch (EmptyDataServiceException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "maintainer")
    public String maintainerSubmit(@RequestParam(name = "name") String name,
                                   @RequestParam(name = "description") String description,
                                   @RequestParam(name = "user") User user,
                                   @RequestParam(name = "geoPosition") GeoPosition geoPosition){
        try {
            maintainerDataService.create(name, description, user, geoPosition);
        } catch (DataServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/maintainers";
    }
}
