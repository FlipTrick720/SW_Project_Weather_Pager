package at.qe.skeleton.internal.ui.beans;


import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.TokenService;
import at.qe.skeleton.internal.services.UserxService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Map;

@Named
@ViewScoped
public class ResetPasswordBean implements Serializable {

    private String token;
    private String newPassword;

    private boolean validToken = false;

    @Autowired
    UserxService userxService;

    @Autowired
    TokenService tokenservice;

    @PostConstruct
    public void init() {
        // Extract token from the URL
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        token = requestParameterMap.get("token");

        // Check if token is valid
        if(tokenservice.getUserByConfirmationToken(token) == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Invalid token"));
            System.out.println("message added");
            this.validToken = false;
        } else {
            this.validToken = true;
        }
    }

    public void resetPassword() {
        Userx user = tokenservice.getUserByConfirmationToken(token);
        user.setPassword(newPassword);
        userxService.saveUser(user);

        //delete token after password change
        tokenservice.deleteToken(tokenservice.findByTokenString(token));
    }

    public boolean isValidToken() {
        return validToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
