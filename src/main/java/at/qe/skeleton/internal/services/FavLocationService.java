package at.qe.skeleton.internal.services;

import at.qe.skeleton.external.services.GeocodingApiRequestService;
import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.FavLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Scope("application")
public class FavLocationService {
    @Autowired
    private FavLocationRepository locationRepository;
    @Autowired
    private GeocodingApiRequestService geocodingApiRequestService;

    public FavLocation saveLocation(FavLocation location) {
        return locationRepository.save(location);
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
    public void updateLocations(List<FavLocation> favLocations) {
        favLocations.stream().forEach(l -> updateIndexLocation(l.getId(), favLocations.indexOf(l)));
    }

    /**public FavLocation getFavLocationById(Long id){
     return locationRepository.findFirstById(id);
     }*/
    public void updateIndexLocation(Long id, Integer newIndex){
        FavLocation location = loadLocation(id);
        location.setIndex(newIndex);
        saveLocation(location);
    }

    public FavLocation stringToFavLocation(String city, Userx user){
        FavLocation favLocation = new FavLocation();
        favLocation.setName(city);
        favLocation.setLatitude(geocodingApiRequestService.retrieveGeocodingData(city).get(0).lat());
        favLocation.setLongitude(geocodingApiRequestService.retrieveGeocodingData(city).get(0).lon());
        favLocation.setUser(user);
        favLocation.setIndex(0);
        saveLocation(favLocation);
        return favLocation;
    }

}
