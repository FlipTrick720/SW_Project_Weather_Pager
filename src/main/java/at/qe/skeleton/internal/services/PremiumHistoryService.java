package at.qe.skeleton.internal.services;

import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.PremiumHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for accessing and  manipulating Premium History data.
 */
@Component
@Scope("application")
public class PremiumHistoryService {

    @Autowired
    private PremiumHistoryRepository premiumHistoryRepository;

    public void savePremiumHistory(Userx user, boolean newPremiumStatus) {
        PremiumHistory premiumHistory = new PremiumHistory();
        premiumHistory.setUser(user);
        premiumHistory.setChangeDate(LocalDateTime.now());
        premiumHistory.setNewPremiumStatus(newPremiumStatus);
        premiumHistoryRepository.save(premiumHistory);
    }
    @PreAuthorize("hasAuthority('MANAGER')" )
    public List<PremiumHistory> getPremiumChanged(String user) {
        return premiumHistoryRepository.findAllByUserId(user);
    }
}
