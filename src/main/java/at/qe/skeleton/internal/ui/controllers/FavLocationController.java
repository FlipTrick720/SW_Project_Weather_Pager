package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.FavLocationService;
import at.qe.skeleton.internal.services.UserxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("view")
public class FavLocationController {
    @Autowired
    private FavLocationService locationService;
    @Autowired
    private UserxService userService;

    public List<FavLocation> getUserLocations(Userx user){
        return locationService.getUserLocations(user);
    }
    public List<FavLocation> getAllLocations() {return locationService.getAllLocations();}
    public void doSaveLocation(Userx user, GeocodingDTO geocodingDTO){
        locationService.saveLocation(geocodingDTO, user);
    }

    public void updateLocations(List<FavLocation> locations, Userx user){
        Userx updatedUser = userService.loadUser(user.getUsername());
        updatedUser.setLocations(locations);

    }
}
