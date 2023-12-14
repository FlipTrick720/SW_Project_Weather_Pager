package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.UserxService;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "welcome.xhtml"; // here you can change the default landing page
    }
}



