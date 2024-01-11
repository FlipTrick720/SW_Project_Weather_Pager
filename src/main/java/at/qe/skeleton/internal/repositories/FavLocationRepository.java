package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;

import java.util.List;

public interface FavLocationRepository extends AbstractRepository<FavLocation,String> {
    public List<FavLocation> findAllByUser(Userx user);
    public FavLocation findFirstById(Long id);
}
