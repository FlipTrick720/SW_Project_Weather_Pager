package at.qe.skeleton.internal.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "welcome.xhtml"; // here you can change the default landing page
    }
}
