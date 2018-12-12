package com.eventplatform.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapPageController {

    @RequestMapping("/yandexmap")
    public String handleYandexRequest(Model model) {

        return "yandexmap";
    }

    @RequestMapping("/twogismap")
    public String handle2GisRequest(Model model) {

        return "twogismap";
    }
}
