package at.qe.skeleton.internal.services;


import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.PaymentHistoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.boot.jaxb.internal.stax.JpaOrmXmlEventReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Service for accessing and manipulating Payment History Data.
 *
 */


@Component
@Scope("application")
public class PaymentHistoryService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;



    public void createPaymentHistory(Userx user){
        LocalDateTime now = LocalDateTime.now();
        Integer currentYear = now.getYear();
        Month currentMonth = now.getMonth();
        if(paymentHistoryRepository.findByUserAndPaymentYearAndPaymentMonth(user, currentYear, currentMonth) != null ){
            System.out.println("already exists");
        } else {
            PaymentHistory paymentHistory = new PaymentHistory();
            paymentHistory.setUser(user);
            paymentHistory.setYear(currentYear);
            paymentHistory.setMonth(currentMonth);
            paymentHistory.setPaymentStatus(false);
            paymentHistoryRepository.save(paymentHistory);
        }
    }

    public void updatePaymentStatus (Userx user, Boolean paymentStatus, Integer chargedDays){
        LocalDateTime now = LocalDateTime.now();
        Integer currentYear = now.getYear();
        Month currentMonth = now.getMonth();

        PaymentHistory paymentHistory  = paymentHistoryRepository.findByUserAndPaymentYearAndPaymentMonth(user, currentYear,currentMonth);

        if (paymentHistory == null ){
            PaymentHistory newPaymentHistory = new PaymentHistory();
            paymentHistory.setUser(user);
            paymentHistory.setYear(currentYear);
            paymentHistory.setMonth(currentMonth);
            paymentHistory.setPaymentStatus(paymentStatus);
            paymentHistory.setChargedDays(chargedDays);
            paymentHistoryRepository.save(newPaymentHistory);
        } else {
            paymentHistory.setPaymentStatus(paymentStatus);
            paymentHistory.setChargedDays(chargedDays);
            paymentHistoryRepository.save(paymentHistory);
        }
    }



    /**
     * finds and returns a List of all the Payment-History Entries for a certain year and month.
     *
     * @param year
     * @param monat
     * @return
     */
    public List<PaymentHistory> getAllByYearAndMonth(Integer year, Month monat){
        return paymentHistoryRepository.findByPaymentYearAndPaymentMonth(year, monat);
    }

}
