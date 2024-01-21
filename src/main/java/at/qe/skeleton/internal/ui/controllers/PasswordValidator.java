package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.configs.WebSecurityConfig;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.stereotype.Component;

/**
 * Validator for Passwords.
 */
@Component
public class PasswordValidator {
    public static void validatePasswords(String confirmPassword, String password, String errorMessageClientId) {
        if (!WebSecurityConfig.passwordEncoder().matches(confirmPassword,password)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(errorMessageClientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Passwords do not match"));
        }
    }

}
