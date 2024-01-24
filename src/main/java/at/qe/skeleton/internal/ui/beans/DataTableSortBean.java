package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import jakarta.annotation.PostConstruct;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class safes the state of the current order of a data List
 */
@Component
@Scope("session")
public class DataTableSortBean {
    private List<PaymentHistory> paymentHistoryList;
    private Collection<Userx> userList;
    @Autowired
    private PaymentHistoryService paymentHistoryService;
    @Autowired
    private UserxService userService;
    @Autowired
    private DateSelectionBean dateSelectionBean;
    @Autowired
    private FilterListBean filterListBean;

    @PostConstruct
    public void init() {
        paymentHistoryList = paymentHistoryService.getAllByYearAndMonth(dateSelectionBean.getSelectedYear(), dateSelectionBean.getSelectedMonthInt());
        userList = userService.getAllUsers();
    }

    public List<PaymentHistory> getPaymentHistoryList() {
        return filterListBean.getPaymentHistoryList(paymentHistoryList);
    }

    public Collection<Userx> getUserList() {
        return filterListBean.getUserList(userList);
    }
}
