package at.qe.skeleton.internal.services.email;

import at.qe.skeleton.internal.services.email.EmailStrategy;
import org.springframework.mail.SimpleMailMessage;

public class PasswordChangeMailStrategy implements EmailStrategy {
    @Override
    public void configureMail(SimpleMailMessage message, String token) {
        message.setSubject("Password change for the WeatherApp");
        message.setText("Please click this link to change your password:\n"
                + "http://localhost:8080/user/reset_password.xhtml?token=" + token);
    }
}
