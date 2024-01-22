package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import jakarta.annotation.PostConstruct;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.MatchMode;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.util.LangUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.getInteger;


@Component
@Scope("request")
public class DataTableSortBean {
    private List<PaymentHistory> paymentHistoryList;
    private Collection<Userx> userList;
    private List<SortMeta> sortBy;
    private String filterValue;
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
        System.out.println(filterValue);
        if (filterValue == null || filterValue.isEmpty()) {
            return paymentHistoryList;
        } else {
            return paymentHistoryList.stream()
                    .filter(paymentHistory -> paymentHistory.getUser().getUsername().toLowerCase().contains(filterValue.toLowerCase()) ||
                            paymentHistory.getPaymentStatus().toString().toLowerCase().contains(filterValue.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    public Collection<Userx> getUserList() {
        if (filterValue == null || filterValue.isEmpty()) {
            return userList;
        } else {
            return userList.stream()
                    .filter(user -> user.getUsername().toLowerCase().contains(filterValue.toLowerCase()) ||
                            user.getFirstName().toLowerCase().contains(filterValue.toLowerCase()) ||
                            user.getLastName().toLowerCase().contains(filterValue.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }
}
