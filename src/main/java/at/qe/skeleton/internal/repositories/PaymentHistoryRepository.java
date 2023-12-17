package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.util.List;

/**
 * Repository for managing {@link PaymentHistory} entities.
 *
 */
public interface PaymentHistoryRepository extends AbstractRepository <PaymentHistory, Long> {

    public PaymentHistory findByUser (Userx user);

    List<PaymentHistory> findByPaymentYearAndPaymentMonth(Integer jahr, Month monat);


    //count > 0 evaluates to true if the value is greater than 0.
    @Query("SELECT COUNT(ph) > 0 FROM PaymentHistory ph WHERE ph.user = :user AND ph.paymentYear = :paymentYear AND ph.paymentMonth = :paymentMonth")
    PaymentHistory findByUserAndPaymentYearAndPaymentMonth(
            @Param("user") Userx user,
            @Param("paymentYear") int paymentYear,
            @Param("paymentMonth") Month paymentMonth
    );

}
