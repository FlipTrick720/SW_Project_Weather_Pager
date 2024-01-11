package at.qe.skeleton.internal.services;

import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import at.qe.skeleton.external.services.GeocodingApiRequestService;
import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.FavLocationRepository;
import jakarta.persistence.EntityNotFoundException;
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
    @Autowired
    private GeocodingApiRequestService geocodingApiRequestService;

    private FavLocation selectedLocation;

    private Long LastGivenId = 1L;


    public FavLocation saveLocation(FavLocation location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(FavLocation location){
        locationRepository.delete(location);
    }
    public void updateIndexLocations(List<FavLocation> favLocations) {
        favLocations.stream().forEach(l -> updateIndexLocation(l.getId(), favLocations.indexOf(l)));
    }

    public FavLocation loadLocation(Long id) {
        FavLocation location = locationRepository.findFirstById(id);
        if (location == null) {
            throw new EntityNotFoundException("Location with ID " + id + " not found");
        }
        return location;
    }
    public List<FavLocation> getUserLocations(Userx user){
        List<FavLocation> favLocations = locationRepository.findAllByUser(user);
        favLocations.sort((l1, l2) -> l1.getIndex().compareTo(l2.getIndex()));
        return favLocations;
    }

    /**public FavLocation getFavLocationById(Long id){
     return locationRepository.findFirstById(id);
     }*/
    public void updateIndexLocation(Long id, Integer newIndex){
        FavLocation location = loadLocation(id);
        location.setIndex(newIndex);
        saveLocation(location);
    }

    /**
     * This function creates and saves a new FavLocation. Converting a Name of a City in a new FavLocation
     * @param city that is supposed to be saved
     * @param user that is currently locked in and will be joined to the according Favorite Location
     * @return the saved FavLocation
     */
    public FavLocation StringToFavLocation(String city, Userx user) throws EntityNotFoundException {
        FavLocation favLocation = new FavLocation();

        try {
            // Set basic attributes
            favLocation.setId(LastGivenId + 1L);
            LastGivenId = favLocation.getId();
            favLocation.setName(city);
            favLocation.setUser(user);
            favLocation.setIndex(0);

            // Retrieve geocoding data
            List<GeocodingDTO> geocodingData = geocodingApiRequestService.retrieveGeocodingData(city);
            System.out.println(geocodingData);

            if (geocodingData != null && !geocodingData.isEmpty()) {
                favLocation.setLatitude(geocodingData.get(0).lat());
                favLocation.setLongitude(geocodingData.get(0).lon());
            } else {
                throw new EntityNotFoundException("Geocoding data not available for the specified city: " + city);
            }

            // Save the location
            saveLocation(favLocation);

            return favLocation;
        } catch (Exception e) {
            throw new EntityNotFoundException("Error while creating FavLocation for city: " + city, e);
        }
    }

}
