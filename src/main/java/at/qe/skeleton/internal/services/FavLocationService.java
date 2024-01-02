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

    public FavLocation saveLocation(FavLocation location) {
        return locationRepository.save(location);
    }

    public FavLocation createLocation(GeocodingDTO geocodingDTO, Userx user) {
        FavLocation favLocation = new FavLocation();
        favLocation.setName(geocodingDTO.name());
        favLocation.setLatitude(favLocation.getLatitude());
        favLocation.setLongitude(favLocation.getLongitude());
        favLocation.setUser(user);
        favLocation.setIndex(0);
        return this.saveLocation(favLocation);
    }

    public FavLocation loadLocation(Long id){
        return locationRepository.findFirstById(id);
    }

    public void deleteLocation(FavLocation location){
        locationRepository.delete(location);
    }

    public List<FavLocation> getUserLocations(Userx user){
        List<FavLocation> favLocations = locationRepository.findAllByUser(user);
        favLocations.sort((l1, l2) -> l1.getIndex().compareTo(l2.getIndex()));
        return favLocations;
    }
    public void updateIndexLocation(Long id, Integer newIndex){
        FavLocation location = loadLocation(id);
        location.setIndex(newIndex);
        saveLocation(location);
    }

    public void updateLocations(List<FavLocation> favLocations) {
        favLocations.stream().forEach(l -> updateIndexLocation(l.getId(), favLocations.indexOf(l)));
    }
}
