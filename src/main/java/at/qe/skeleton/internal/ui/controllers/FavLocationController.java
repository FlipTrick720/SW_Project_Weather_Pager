package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.external.model.currentandforecast.CurrentAndForecastAnswerDTO;
import at.qe.skeleton.external.model.currentandforecast.misc.CurrentWeatherDTO;
import at.qe.skeleton.external.model.shared.WeatherDTO;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.FavLocationService;
import at.qe.skeleton.internal.services.UserxService;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

@Component
@Scope("view")
public class FavLocationController implements Serializable {
    @Autowired
    private FavLocationService favLocationService;
    public void doSafeLocationByName(String city, Userx user){
        favLocationService.StringToFavLocation(city, user);
    }

}
