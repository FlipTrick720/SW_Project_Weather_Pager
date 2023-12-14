package at.qe.skeleton.internal.services;


import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.PaymentHistoryRepository;
import org.hibernate.boot.jaxb.internal.stax.JpaOrmXmlEventReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * Service for accessing and manipulating Payment History Data.
 *
 */


@Component
@Scope("application")
public class PaymentHistoryService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;


    /**
     * Builds and saves a new Payment-History with the given Parameters.
     * @param user
     * @param paymentStatus
     */
    public void savePaymentHistory(Userx user, Boolean paymentStatus){
        LocalDateTime now = LocalDateTime.now();
        PaymentHistory paymentHistory = new PaymentHistory();
        paymentHistory.setUser(user);
        paymentHistory.setPaymentStatus(paymentStatus);
        paymentHistory.setYear(now.getYear());
        paymentHistory.setMonth(now.getMonth());
        paymentHistoryRepository.save(paymentHistory);
    }

    /**
     * finds and returns a List of all the Payment-History Entries for a certain year and month.
     *
     * @param year
     * @param month
     * @return
     */
    public List<PaymentHistory> getAllByYearAndMonth(Integer year, Month month){
        return paymentHistoryRepository.findByYearAndMonth(year, month);
    }
}
