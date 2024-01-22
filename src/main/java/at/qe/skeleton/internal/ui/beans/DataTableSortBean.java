package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import org.hibernate.annotations.View;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
@Scope("request")
public class DataTableSortBean {
    private List<PaymentHistory> paymentHistoryList;
    private Collection<Userx> userList;
    private List<SortMeta> sortBy;
    @Autowired
    private PaymentHistoryService paymentHistoryService;
    @Autowired
    private UserxService userService;
    @Autowired
    private DateSelectionBean dateSelectionBean;

    @PostConstruct
    public void init() {
        paymentHistoryList = paymentHistoryService.getAllByYearAndMonth(dateSelectionBean.getSelectedYear(), dateSelectionBean.getSelectedMonthInt());
        userList = userService.getAllUsers();

        sortBy = new ArrayList<>();
        sortBy.add(SortMeta.builder()
                .field("name")
                .order(SortOrder.ASCENDING)
                .build());

        sortBy.add(SortMeta.builder()
                .field("category")
                .order(SortOrder.ASCENDING)
                .priority(1)
                .build());
    }

    public List<PaymentHistory> getPaymentHistoryList() {
        return paymentHistoryList;
    }

    public Collection<Userx> getUserList() {
        return userList;
    }

    public List<SortMeta> getSortBy() {
        return sortBy;
    }
}
