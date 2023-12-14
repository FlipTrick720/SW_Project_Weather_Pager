package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.ui.beans.DateSelectionBean;
import jakarta.annotation.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component
@Scope("view")
public class PaymentHistoryController {
    @Autowired
    private PaymentHistoryService paymentHistoryService;

    public List<PaymentHistory> getPaymentHistoryYearMonth(Integer year, Month month){

            return paymentHistoryService.getAllByYearAndMonth(year, month);

        }
}
