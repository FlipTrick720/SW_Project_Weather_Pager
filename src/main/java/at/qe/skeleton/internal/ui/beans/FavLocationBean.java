package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.services.WeatherApiRequestService;
import at.qe.skeleton.internal.model.FavLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class FavLocationBean {
    @Autowired
    private WeatherApiRequestService weatherApiRequestService;

    public double getFeelsLikeTemp(FavLocation favLocation){
        return weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather().feelsLikeTemperature();
    }


}
