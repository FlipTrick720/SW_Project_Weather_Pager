package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PremiumHistoryService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class PremiumStatusListener implements PropertyChangeListener{

    @Autowired
    private PremiumHistoryService premiumHistoryService;

    /**
     * Creates a new PremiumHistory entry when an event from the Observer is triggered.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("premium".equals(evt.getPropertyName())) {
            boolean newPremiumStatus = (boolean) evt.getNewValue();
            Userx user = (Userx) evt.getSource();
            premiumHistoryService.savePremiumHistory(user, newPremiumStatus);
        }
    }

    /**
     * gets a list of all the dates where a change of the premium status occurred to the user
     * @param user
     * @return
     */
    public List<PremiumHistory> getPremiumIntervalByName(Userx user) {
        return premiumHistoryService.getPremiumChangedByName(user.getUsername());
    }

    /**
     * gets a list of all the dates where a change of the premium status occurred for all users
     * @return
     */
    public List<PremiumHistory> getPremiumInterval() {
        return premiumHistoryService.getPremiumChanged();
    }

    /**
     * gets a list of time spans, of the user, where he/she was a premium user
     * @param user
     * @return
     */
    public List<Integer> getPremiumTupel(Userx user) {
        List<PremiumHistory> allDates = getPremiumIntervalByName(user);
        List<Duration> intervalls = new ArrayList<>();
        if (allDates.toArray().length < 2) {
            return null;
        } else if (allDates.toArray().length%2 == 0) {
            for (int i = 0; i < allDates.toArray().length-1; i=i+2) {
                intervalls.add(Duration.between(allDates.get(i).getChangeDate(), allDates.get(i+1).getChangeDate()));
            }
        } else {
            for (int i = 0; i < allDates.toArray().length-2; i=i+2) {
                intervalls.add(Duration.between(allDates.get(i).getChangeDate(), allDates.get(i+1).getChangeDate()));
            }
        }
        List<Integer> intervallsInInt = new ArrayList<>();
        for (Duration duration : intervalls) {
            intervallsInInt.add((int) duration.toSeconds());
        }
        return intervallsInInt;
    }

    /**
     * gets the time span, of the user, where he/she was a premium user for a certain row count.
     * this is purely a method for display purposes
     * @param user
     * @param rowIndex
     * @return
     */
    public Integer getPremiumTupel(Userx user, int rowIndex) {
        List<Integer> premiumTupelList = getPremiumTupel(user);

        if (premiumTupelList == null) {
            return null;
        } else if (rowIndex >= 0 && rowIndex < premiumTupelList.size()) {
            return premiumTupelList.get(rowIndex);
        } else {
            return null;
        }
    }

}



