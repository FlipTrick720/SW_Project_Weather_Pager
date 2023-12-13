package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PremiumHistoryRepository extends AbstractRepository <PremiumHistory, Long> {

    public PremiumHistory findByUser (Userx user);

    List<PremiumHistory> findAllByUserId(String userId);

    //humbuck, bitte ignorieren
    //@Query("SELECT ph FROM PremiumHistory ph WHERE ph.user.username = :username")
    //List<PremiumHistory> findAllByUserId(@Param("username") String username);

}
