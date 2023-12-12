package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.services.PremiumHistoryService;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class PremiumStatusBean implements Serializable {

    @Inject
    private PremiumHistoryService premiumHistoryService;

    private List<PremiumHistory> premiumHistoryList;

    @PostConstruct
    public void init() {
        premiumHistoryList = premiumHistoryService.getPremiumChanged("admin");
    }

    public List<PremiumHistory> getPremiumHistoryList() {
        return premiumHistoryList;
    }
}
