package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PremiumHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


@Component
public class PremiumStatusListener implements PropertyChangeListener{

    @Autowired
    private PremiumHistoryService premiumHistoryService;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("premium".equals(evt.getPropertyName())) {
            boolean newPremiumStatus = (boolean) evt.getNewValue();
            Userx user = (Userx) evt.getSource();
            premiumHistoryService.savePremiumHistory(user, newPremiumStatus);
        }
    }

    public List<PremiumHistory> getPremiumInterval() {//Userx user
        return premiumHistoryService.getPremiumChanged("admin");//user.getUsername())
    }



}
