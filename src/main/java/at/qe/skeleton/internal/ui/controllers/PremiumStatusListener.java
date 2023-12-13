package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PremiumHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    public List<PremiumHistory> getPremiumIntervalByName(Userx user) {
        return premiumHistoryService.getPremiumChangedByName(user.getUsername());
        //tupel von aktiven premium phasen
    }

    public List<PremiumHistory> getPremiumInterval() {
        return premiumHistoryService.getPremiumChanged();
    }

    /*
    //work in progress
    public List<PremiumHistory> getPremiumTupel(Userx user) {
        List<PremiumHistory> allDates = getPremiumIntervalByName(user);
        Map<Userx, List<PremiumHistory>> DatesByUser = allDates.stream().collect(Collectors.groupingBy(PremiumHistory::getUser));




        for (int i = 0; i < allDates.toArray().length-1; i++) {
            if (allDates.get(i).getUser().equals(allDates.get(i+1).getUser())) {
                allDates.get(i)
            }
        }
    }*/
}

