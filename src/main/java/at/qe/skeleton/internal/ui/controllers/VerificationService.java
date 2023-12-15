package at.qe.skeleton.internal.ui.controllers;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("request")
class VerificationService {

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token") String providedToken, @RequestParam("username") String username){
        String storedToken = "12345";   //hard coded for testing
        System.out.println("works");

        if (storedToken.equals(providedToken)){
            //activate account
            return "redirect:/verification/success.xhtml";
        } else {
            return "redirect:/verification/failure.xhtml";
        }
    }
}
