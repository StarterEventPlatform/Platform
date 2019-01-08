package com.eventplatform.controller.page;

import com.eventplatform.domain.model.Event;
import com.eventplatform.domain.model.GeoPosition;
import com.eventplatform.domain.model.Maintainer;
import com.eventplatform.domain.model.User;
import com.eventplatform.exception.dataservice.DataServiceException;
import com.eventplatform.exception.dataservice.EmptyDataServiceException;
import com.eventplatform.service.data.EventDataService;
import com.eventplatform.service.data.GeoPositionDataService;
import com.eventplatform.service.data.MaintainerDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class EventPageController {
    @Autowired
    EventDataService eventDataService;
    @Autowired
    private GeoPositionDataService geoPositionDataService;
    @Autowired
    private MaintainerDataService maintainerDataService;
    @RequestMapping("/events")
    public String handleUsersRequest(Model model) {
        // todo research catch exception, maybe made individual try catch for each addAttribute
        try {
            model.addAttribute("geopositions", maintainerDataService.getAll());
            model.addAttribute("maintainers", geoPositionDataService.getAll());
            model.addAttribute("events", eventDataService.getAll());
        } catch (EmptyDataServiceException e) {
            model.addAttribute("events", new ArrayList<>());
        }

        return "events";

    }

    @PostMapping(value = "events")
    public String eventSubmit(@RequestParam(name = "id") Integer id,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "maintainer") Maintainer maintainer,
                              @RequestParam(name = "createDate") Date createDate,
                              @RequestParam(name = "eventDate") Date eventDate,
                              @RequestParam(name = "type") String type,
                              @RequestParam(name = "geoPosition") GeoPosition geoPosition) {
        try {
            eventDataService.create(new Event(id,createDate,name,description,geoPosition,maintainer,type,eventDate));
        } catch (DataServiceException e) {
            e.printStackTrace();
        }
        return "redirect:/events";
    }


}
