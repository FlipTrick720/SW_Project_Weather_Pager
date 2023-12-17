package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;

import java.util.List;

public interface LocationRepository extends AbstractRepository<FavLocation,String> {
    public FavLocation findFirstByName(String name);
    public List<FavLocation> findAllByUser(Userx user);
}
