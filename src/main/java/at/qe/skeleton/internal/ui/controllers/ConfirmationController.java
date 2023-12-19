package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.UserxRepository;
import at.qe.skeleton.internal.services.UserxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller handling the confirmation of a newly registered user.
 */
@Controller
@RequestMapping("/confirm")
public class ConfirmationController {
    @Autowired
    UserxService userxService;

    @GetMapping
    public String confirmRegistration(@RequestParam("token") String token) {

        Userx user = userxService.getUserByConfirmationToken(token);

        if (user != null) {
            user.setEnabled(true);
            userxService.saveUser(user);
            return "redirect:/verification/success.xhtml";
        } else {
            return "redirect:/verification/failure.xhtml";
        }
    }
    //todo: introduce error handling
}
