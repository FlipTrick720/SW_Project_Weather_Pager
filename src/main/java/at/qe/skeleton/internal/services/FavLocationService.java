package at.qe.skeleton.internal.services;

import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.FavLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Component
@Scope("application")
public class FavLocationService {
    @Autowired
    private FavLocationRepository locationRepository;

    public FavLocation saveLocation(GeocodingDTO location, Userx user) {
        return createLocation(location, user);
    }
    public FavLocation saveLocation(FavLocation location) {
        return locationRepository.save(location);
    }

    public FavLocation createLocation(GeocodingDTO geocodingDTO, Userx user) {
        FavLocation favLocation = new FavLocation();
        favLocation.setName(geocodingDTO.name());
        favLocation.setLatitude(favLocation.getLatitude());
        favLocation.setLongitude(favLocation.getLongitude());
        favLocation.setUser(user);
        return this.saveLocation(favLocation);
    }

    public FavLocation loadLocation(String name){
        return locationRepository.findFirstByName(name);
    }

    public void deleteLocation(FavLocation location){
        locationRepository.delete(location);
    }

    public List<FavLocation> getUserLocations(Userx user){
        return locationRepository.findAllByUser(user);

    }
    public List<FavLocation> getAllLocations(){
        return locationRepository.findAll();
    }

    public FavLocation getLocationByName(String name){
        return locationRepository.findFirstByName(name);
    }
}
