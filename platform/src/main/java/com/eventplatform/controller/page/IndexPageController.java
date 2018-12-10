package com.eventplatform.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class IndexPageController {

    private String getHexFromDate(Date date) {
        StringBuilder sb = new StringBuilder();
        String h = Integer.toString(date.getHours());
        String m = Integer.toString(date.getMinutes());
        String s = Integer.toString(date.getSeconds());
        sb.append("#")
                .append(h.length() == 1 ? "0" + h : h)
                .append(m.length() == 1 ? "0" + m : m)
                .append(s.length() == 1 ? "0" + s : s);
        return sb.toString();
    }

    @RequestMapping("/")
    public String handleRequest(Model model) {
        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("from", this.getClass().getCanonicalName());
        model.addAttribute("date", date);
        model.addAttribute("bgcolor", getHexFromDate(date));
        return "index";
    }

}
