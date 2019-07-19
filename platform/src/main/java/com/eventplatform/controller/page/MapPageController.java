package com.eventplatform.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapPageController {

    @RequestMapping("/twogismap")
    public String handle2GisRequest(Model model) {

        return "twogismap";
    }
}
