package at.qe.skeleton.internal.ui.controllers;


import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.PremiumHistoryRepository;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PremiumStatusListener {
    private PremiumHistoryRepository premiumHistoryRepository;

    @Autowired

    public PremiumStatusListener(PremiumHistoryRepository premiumHistoryRepository) {
        this.premiumHistoryRepository = premiumHistoryRepository;
    }

    @PostUpdate
    public void onPostUpdate(Userx user) {
        boolean newPremiumStatus = user.isPremium();

    }
}
