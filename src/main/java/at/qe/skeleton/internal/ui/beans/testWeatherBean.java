package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.CurrentAndForecastAnswerDTO;
import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import at.qe.skeleton.external.services.GeocodingApiRequestService;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * diese klasse ist nur testweise erstellt worden und sollte schnellstmöglich gelöscht werden
 */
@Component
@Scope("view")
public class testWeatherBean {

    @Autowired
    private WeatherApiRequestService weatherApiRequestService;
    @Autowired
    private GeocodingApiRequestService geocodingApiRequestService;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApiBean.class);
    private double latitude;
    private double longitude;
    private String location;
    private double temperature;
    private String weatherDescription;
    private double humidity;
    private double windSpeed;
    public void searchWeather() {
        List<GeocodingDTO> geocode = geocodingApiRequestService.retrieveGeocodingData(location);
        latitude = geocode.get(0).lat();
        longitude = geocode.get(0).lon();


        try {
            CurrentAndForecastAnswerDTO answer = this.weatherApiRequestService.retrieveCurrentAndForecastWeather(getLatitude(), getLongitude());
            temperature = answer.currentWeather().temperature();
            weatherDescription = answer.currentWeather().weather().description();
            humidity = answer.currentWeather().humidity();
            windSpeed = answer.currentWeather().windSpeed();
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
