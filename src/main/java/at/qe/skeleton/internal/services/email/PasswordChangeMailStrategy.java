package at.qe.skeleton.internal.services.email;

import org.springframework.mail.SimpleMailMessage;

public class PasswordChangeMailStrategy implements EmailStrategy {
    @Override
    public void configureMail(SimpleMailMessage message, String token) {
        message.setSubject("Password change for the WeatherApp");
        message.setText("Please click this link to verify your password change:\n"
                + "http://localhost:8080/confirm?token=" + token);
    }
}
