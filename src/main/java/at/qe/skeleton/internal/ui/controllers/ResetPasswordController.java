package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.TokenService;
import at.qe.skeleton.internal.services.UserxService;
import at.qe.skeleton.internal.services.email.EmailService;
import at.qe.skeleton.internal.services.email.PasswordChangeMailStrategy;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ResetPasswordController {
    @Autowired
    private UserxService userxService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private TokenService tokenService;
    private String email;

    public void requestPasswordChange(){
        Userx user = userxService.getUserByEmail(this.email);

        //display error message if user not found or user has no email
        if (user == null || user.getEmail() == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("E-Mail not found"));
            return;
        }

        //create token and send email
        String token = tokenService.generateTokenString();
        tokenService.createVerificationToken(user, token);

        emailService.setEmailStrategy(new PasswordChangeMailStrategy());
        emailService.sendMail(user.getEmail(), token);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", "Reset email has been sent!"));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
