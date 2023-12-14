package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.Userx;

import java.time.Month;
import java.util.List;

/**
 * Repository for managing {@link PaymentHistory} entities.
 *
 */
public interface PaymentHistoryRepository extends AbstractRepository <PaymentHistory, Long> {

    public PaymentHistory findByUser (Userx user);

    List<PaymentHistory> findByPaymentYearAndPaymentMonth(Integer jahr, Month monat);
}
