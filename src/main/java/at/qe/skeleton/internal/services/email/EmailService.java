package at.qe.skeleton.internal.services.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    private EmailStrategy emailStrategy;


    public void sendMail(String to, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.weatherapp.uibk@gmail.com");
        message.setTo(to);

        //strategy pattern to configure the mail text
        if (emailStrategy != null) {
            emailStrategy.configureMail(message, token);
        } else {
            throw new IllegalStateException("Email strategy is not set.");
        }

        javaMailSender.send(message);
        System.out.println("mail sent");
    }

    public void setEmailStrategy(EmailStrategy emailStrategy) {
        this.emailStrategy = emailStrategy;
    }

}