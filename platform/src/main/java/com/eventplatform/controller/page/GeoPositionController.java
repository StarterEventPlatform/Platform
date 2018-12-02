package com.eventplatform.controller.page;

import com.eventplatform.exception.controller.ControllerAggregatorException;
import com.eventplatform.pojo.controller.DataControllerAggregator;
import com.eventplatform.pojo.controller.DataControllerConstants;
import com.eventplatform.pojo.klass.GeoPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GeoPositionController {
    @Autowired
    private DataControllerAggregator dataControllerAggregator;

    @RequestMapping("/geopostions")
    public String handleRequest(Model model) {
        try {
            GeoPositionController geoPositionController = (GeoPositionController) dataControllerAggregator.getByType(DataControllerConstants.GEOPOSITION_TYPE);

        } catch (ControllerAggregatorException e) {
            e.printStackTrace();
        }
        model.addAttribute("geopositions" , null);
        return "geopositions";
    }

    @GetMapping("/geoposition")
    public ModelAndView createGeoPosition() {
        ModelAndView modelAndView = new ModelAndView("create", "geoPosition", new GeoPosition());
        return modelAndView;
    }


}
