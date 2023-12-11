package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
public interface PremiumHistoryRepository extends AbstractRepository <PremiumHistory, Long> {

    public PremiumHistory findByUser (Userx user);
}
