package com.eventplatform.controller.page;

import com.eventplatform.exception.controller.ControllerAggregatorException;
import com.eventplatform.pojo.controller.DataControllerAggregator;
import com.eventplatform.pojo.controller.DataControllerConstants;
import com.eventplatform.pojo.controller.GeoPositionDataController;
import com.eventplatform.pojo.klass.GeoPosition;
import com.eventplatform.repository.GeoPositionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class GeoPositionController {
    @Autowired
    private DataControllerAggregator dataControllerAggregator;
    private final GeoPositionDataRepository geoPositionDataRepository;

    public GeoPositionController(GeoPositionDataRepository geoPositionDataRepository) {
        this.geoPositionDataRepository = geoPositionDataRepository;
    }

    @RequestMapping("/geopositions")
    public String handleRequest(Model model) {
        try {
            GeoPositionDataController geoPositionDataController = (GeoPositionDataController) dataControllerAggregator.getByType(DataControllerConstants.GEOPOSITION_TYPE);

            for (int i = 0; i < 20; i++) {
                GeoPosition geoPosition = new GeoPosition();
                geoPosition.setLatitude(i);
                geoPosition.setLongitude(i+100);
                geoPosition.setCreationDate(new Date(System.currentTimeMillis() + i));
                geoPositionDataRepository.save(geoPosition);
            }
            //model.addAttribute("geopositions",geoPositionDataRepository.findAll());
        } catch (ControllerAggregatorException e) {
            e.printStackTrace();
        }
        model.addAttribute("geopositions" , null);
        return "geopositions";
    }

    @GetMapping("/geoposition")
    public ModelAndView createGeoPosition() {
        ModelAndView modelAndView = new ModelAndView("geoposition", "geoPosition", new GeoPosition());
        return modelAndView;
    }


}
