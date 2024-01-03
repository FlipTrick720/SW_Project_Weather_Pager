package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.external.model.shared.WeatherDTO;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import at.qe.skeleton.internal.model.FavLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("view")
public class FavLocationController implements Serializable {
    @Autowired
    private WeatherApiRequestService weatherApiRequestService;

    public String getFeelsLikeTemp(FavLocation favLocation){
        return "Feels like: " + weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather().feelsLikeTemperature() + "°C";
    }

    public String currentTemp(FavLocation favLocation){
        return "Current: " + weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather().temperature() + "°C";
    }

    public WeatherDTO currentWeather(FavLocation favLocation){
        return weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather().weather();
    }
}
