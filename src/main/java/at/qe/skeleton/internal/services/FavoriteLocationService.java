package at.qe.skeleton.internal.services;

import at.qe.skeleton.internal.model.Location;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.UserxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteLocationService {
    @Autowired
    private UserxRepository userxRepository;

    public void addLocation(Userx user, Location location){
        List<Location> userLocations = user.getLocations();
        userLocations.add(location);
        user.setLocations(userLocations);
    }

    public void removeLocation(Userx user, Location location){
        List<Location> userLocations = user.getLocations();
        userLocations.remove(location);
        user.setLocations(userLocations);
    }

    public List<Location> locations(String username){
        return userxRepository.findFirstByUsername(username).getLocations();
    }
}
