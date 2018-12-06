package com.eventplatform.controller.page;

import com.eventplatform.exception.controller.EmptyControllerException;
import com.eventplatform.pojo.controller.GeoPositionDataController;
import com.eventplatform.pojo.klass.GeoPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class GeoPositionController {
    @Autowired
    private GeoPositionDataController geoPositionDataController;

    @RequestMapping("/geopositions")
    public String handleRequest(Model model) {
        try {
            model.addAttribute("geopositions", geoPositionDataController.getAll());
        } catch (EmptyControllerException e) {
            model.addAttribute("geopositions", new ArrayList<>());
        }
        return "geopositions";
    }

    @GetMapping("/geoposition")
    public ModelAndView createGeoPosition() {
        ModelAndView modelAndView = new ModelAndView("geoposition", "geoPosition", new GeoPosition());
        return modelAndView;
    }


}
