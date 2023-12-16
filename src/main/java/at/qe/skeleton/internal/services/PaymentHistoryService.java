package at.qe.skeleton.internal.services;


import at.qe.skeleton.internal.model.PaymentHistory;
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

/**
 * Service for accessing and manipulating Payment History Data.
 *
 */


@Component
@Scope("application")
public class PaymentHistoryService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;


    // Save -> übergeben userx -> status offen, rechnen mit localdate tage bis ende monat
    // oder Charded day einschreiben???
    /*public void createPaymentHistory(Userx user){
        LocalDateTime now = LocalDateTime.now();
        Integer currentYear = now.getYear();
        Month currentMonth = now.getMonth();
        YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
        Integer daysInCurrentMonth = yearMonth.lengthOfMonth();

        if(paymentHistoryRepository.existsByUserAndPaymentYearAndPaymentMonth(user, currentYear, currentMonth)){
            return;
        } else {
            PaymentHistory paymentHistory = new PaymentHistory();
            paymentHistory.setUser(user);
            paymentHistory.setYear(currentYear);
            paymentHistory.setMonth(currentMonth);
            paymentHistory.setPaymentStatus(false);
            paymentHistory.setDaysTillEndMonth(daysInCurrentMonth-now.getDayOfMonth());
        }

    }*/

    // Save -> übergeben userx, status, verrechneten Tage
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
     * @param monat
     * @return
     */
    public List<PaymentHistory> getAllByYearAndMonth(Integer year, Month monat){
        return paymentHistoryRepository.findByPaymentYearAndPaymentMonth(year, monat);
    }

}
