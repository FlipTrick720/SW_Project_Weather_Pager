package at.qe.skeleton.internal.ui.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "welcome.xhtml"; // change default landing page here
    }
}
