package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendEmailDemoBean {
    @Autowired
    private EmailService emailService;

    public void send(){
        emailService.sendSimpleMessage("jakob-osl@gmx.at", "Test Mail", "Hallo von Spring");
        System.out.println("bean sent");
    }
}
