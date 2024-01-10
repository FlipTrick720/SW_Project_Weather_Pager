package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.CreditCard;
import at.qe.skeleton.internal.model.Userx;

public interface CreditCardRepository extends AbstractRepository<CreditCard, String>{
    public CreditCard findFirstByNumber(String number);

    public Boolean existsByUser (Userx user);

    public CreditCard findByUser (Userx user);
}
