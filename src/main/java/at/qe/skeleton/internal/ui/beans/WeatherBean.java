package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.CurrentAndForecastAnswerDTO;
import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import at.qe.skeleton.external.services.GeocodingApiRequestService;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Scope("view")
public class WeatherBean {

    @Autowired
    private WeatherApiRequestService weatherApiRequestService;
    @Autowired
    private GeocodingApiRequestService geocodingApiRequestService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherBean.class);
    private double latitude;
    private double longitude;
    private String location;

    private Boolean buttonPressed = false;

    private CurrentAndForecastAnswerDTO weather;

    public String imageUrl() {
        return "https://openweathermap.org/img/wn/" + weather.currentWeather().weather().icon() + "@2x.png";
    }

    public void searchWeather() {
        buttonPressed = true;
        System.out.println("button pressed yes");
        List<GeocodingDTO> geocode = geocodingApiRequestService.retrieveGeocodingData(location);
        latitude = geocode.get(0).lat();
        longitude = geocode.get(0).lon();



        try {
            weather = this.weatherApiRequestService.retrieveCurrentAndForecastWeather(getLatitude(), getLongitude());
        } catch (final Exception e) {
            LOGGER.error("error in request", e);
        }
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        System.out.println("location set");
    }

    public CurrentAndForecastAnswerDTO getWeather() {
        return weather;
    }

    public void setWeather(CurrentAndForecastAnswerDTO weather) {
        this.weather = weather;
    }

    public Boolean getButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(Boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
    }
}
