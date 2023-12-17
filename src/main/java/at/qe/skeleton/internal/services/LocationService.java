package at.qe.skeleton.internal.services;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.LocationRepository;
import at.qe.skeleton.internal.repositories.UserxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public FavLocation saveLocation(FavLocation location) {
        return locationRepository.save(location);
    }

    public FavLocation loadLocation(String name){
        return locationRepository.findFirstByName(name);
    }

    public void deleteLocation(FavLocation location){
        locationRepository.delete(location);
    }

    public Collection<FavLocation> getLocations(Userx user){
        return locationRepository.findAllByUser(user);
    }
}
