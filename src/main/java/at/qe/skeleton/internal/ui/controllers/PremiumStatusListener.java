package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.internal.model.Userx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Component
public class PremiumStatusListener implements PropertyChangeListener {

    private Userx user;

    @Autowired
    public PremiumStatusListener(Userx user){
        this.user = user;
        this.user.addPropertyChangeListener(this);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        System.out.println("changes incomming");
    }
}
