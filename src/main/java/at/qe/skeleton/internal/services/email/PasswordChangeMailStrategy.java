package at.qe.skeleton.internal.services.email;

import at.qe.skeleton.internal.services.email.EmailStrategy;
import org.springframework.mail.SimpleMailMessage;

public class PasswordChangeMailStrategy implements EmailStrategy {
    @Override
    public void configureMail(SimpleMailMessage message, String token) {
        message.setSubject("Password change for the WeatherApp");
        message.setText("Please click this link to verify your password change:\n"
                + "http://localhost:8080/confirm?token=" + token);
    }
}
