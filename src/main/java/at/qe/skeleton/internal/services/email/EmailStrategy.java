package at.qe.skeleton.internal.services.email;

import org.springframework.mail.SimpleMailMessage;

public interface EmailStrategy{
    void configureMail(SimpleMailMessage message, String token);
}
