package at.qe.skeleton.internal.services;

import org.springframework.mail.SimpleMailMessage;

public interface EmailStrategy{
    void configureMail(SimpleMailMessage message, String token);
}
