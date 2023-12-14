package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.services.PremiumHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


@Component
public class PremiumStatusListener implements PropertyChangeListener{

    @Autowired
    private PremiumHistoryService premiumHistoryService;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

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
    public List<PremiumHistory> getPremiumIntervalByName(Userx user) {//----
        return premiumHistoryService.getPremiumChangedByName(user.getUsername());
    }

    /**
     * gets a list of all the dates where a change of the premium status occurred for all users
     * @return
     */
    public List<PremiumHistory> getPremiumIntervals() {
        return premiumHistoryService.getPremiumChanged();
    }

    /**
     * gives a list of time spans, where the user of allDates was Premium
     * @param allDates
     * @return
     */
    public List<Integer> getPremiumTupel(List<PremiumHistory> allDates) {
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
            intervallsInInt.add((int) duration.toSeconds()); //change to .toDays() //.toSeconds ony for testing purposes
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
        List<Integer> premiumTupelList = getPremiumTupel(getPremiumIntervalByName(user));

        if (premiumTupelList == null) {
            return null;
        } else if (rowIndex >= 0 && rowIndex < premiumTupelList.size()) {
            return premiumTupelList.get(rowIndex);
        } else {
            return null;
        }
    }

    /**
     * gets the total time somebody was premium user by user
     * this is purely a method for display purposes
     * @param user
     * @return
     */
    public Integer getPremiumIntervalByNameTotal(Userx user) {
        List<Integer> premiumTupelList = getPremiumTupel(getPremiumIntervalByName(user));
        if (premiumTupelList == null) {
            return 0;
        }
        return premiumTupelList.stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * gets all ChangeDates by user, month and year
     * @param user
     * @param year
     * @param month
     * @return
     */
    public List<PremiumHistory> filterDatesByMonthAndYear(Userx user, int year, Month month) {
        List<PremiumHistory> allDates = getPremiumIntervalByName(user);
        List<PremiumHistory> filteredDates = new ArrayList<>();

        for (PremiumHistory history : allDates) {
            if (history.getChangeDate().getYear() == year && history.getChangeDate().getMonth() == month) {
                filteredDates.add(history);
            }
        }
        return filteredDates;
    }

    /**
     * gives the prize for the time span given in the invoiceList until end of current month
     * @param invoiceList
     * @return
     */
    public double priceTillEndCurrentMonth(List<PremiumHistory> invoiceList) {
        if (invoiceList.get(invoiceList.toArray().length-1).getNewPremiumStatus()) { //still is Premium Users add temporary end point to calculate
            PremiumHistory dummyEndPoint = new PremiumHistory();
            dummyEndPoint.setNewPremiumStatus(false);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime lastDayOfMonth = currentDateTime.withDayOfMonth(currentDateTime.getMonth().length(YearMonth.from(currentDateTime).isLeapYear()));
            dummyEndPoint.setChangeDate(lastDayOfMonth);
            invoiceList.add(dummyEndPoint);
        }
        List<Integer> totalTimeTillNow = getPremiumTupel(invoiceList);
        double pricePerTimeUnit = 0.5; //Time unit is currently a Second
        return totalTimeTillNow.stream().mapToInt(Integer::intValue).sum() * pricePerTimeUnit;
    }

    /**
     * does the payment until the end of the current month. of account is to empty it sets premium to false and sends a cancellation e-mail.
     * It also updates the PaymentHistory Data Base
     * @param user
     */
    //Not finished waiting for account and e-mail messaging service
    public void cashUpTillEndCurrentMonth (Userx user) {
        double payment = priceTillEndCurrentMonth(filterDatesByMonthAndYear(user, LocalDate.now().getYear() ,LocalDate.now().getMonth()));

        //Test purposes:
        Random random = new Random();

        //check balance if there is balance >= payment
        if (random.nextInt(3) == 1) {
            //balance = balance - payment;
            paymentHistoryService.savePaymentHistory(user, true);
        } else {
            paymentHistoryService.savePaymentHistory(user, false);
            user.setPremium(false);
            //send email
        }
    }
}