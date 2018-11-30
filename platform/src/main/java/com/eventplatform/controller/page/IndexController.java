package com.eventplatform.controller.page;

import com.eventplatform.pojo.klass.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class IndexController {

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
        User user = new User();
        user.setName("Tester");
        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("user", user);
        model.addAttribute("from", this.getClass().getCanonicalName());
        model.addAttribute("date", date);
        model.addAttribute("bgcolor", getHexFromDate(date));
        return "index";
    }

    /*
    @GetMapping("/")
    public ModelAndView index() {

        Map<String, String> model = new HashMap<>();
        String from = this.getClass().getCanonicalName();
        User user = new User();
        user.setName("Tester");
        user.setCreationDate(new Date(System.currentTimeMillis()));

        model.put("user", user.toString());
        model.put("from", from);

        return new ModelAndView("index", model);
    }*/
}
