package at.qe.skeleton.internal.services.email;

import org.springframework.mail.SimpleMailMessage;

/**
 * Interface for configuring email messages with a specific strategy.
 */
public interface EmailStrategy{
    void configureMail(SimpleMailMessage message, String token);
}
