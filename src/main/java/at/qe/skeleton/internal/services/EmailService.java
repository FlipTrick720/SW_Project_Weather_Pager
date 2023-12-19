package at.qe.skeleton.internal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService{

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendConfirmationMail(String to, String token){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply.weatherapp.uibk@gmail.com");
        message.setTo(to);
        message.setSubject("Confirm your email for the WeatherApp");

        message.setText("Please confirm your email by clicking on the link below:\n"
                + "http://localhost:8080/confirm?token=" + token);

        javaMailSender.send(message);
        System.out.println("service sent");
    }
}
