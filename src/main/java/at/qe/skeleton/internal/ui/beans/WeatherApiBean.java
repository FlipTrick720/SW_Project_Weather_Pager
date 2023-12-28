package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.CurrentAndForecastAnswerDTO;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Demonstrates the working api and what the raw request data would look like
 * <br><br>
 * This class is part of the skeleton project provided for students of the
 * course "Software Architecture" offered by Innsbruck University.
 */
@Component
@Scope("view")
public class WeatherApiBean {

    @Autowired
    private WeatherApiRequestService weatherApiRequestService;
    @Autowired
    private GeocodingApiDemoBean geocodingApiDemoBean;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApiBean.class);
    private String currentWeather;
    private double latitude;
    private double longitude;

    public void init() {
        latitude = geocodingApiDemoBean.getLatitude();
        longitude = geocodingApiDemoBean.getLongitude();
        try {
            CurrentAndForecastAnswerDTO answer = this.weatherApiRequestService.retrieveCurrentAndForecastWeather(getLatitude(), getLongitude());
            double temp = answer.currentWeather().temperature();
            double feelsLike = answer.currentWeather().feelsLikeTemperature();
            setCurrentWeather("Temperature: " + temp + "°C. Feels like: " + feelsLike + "°C");
        } catch (final Exception e) {
            LOGGER.error("error in request", e);
        }
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(String currentWeather) {
        this.currentWeather = currentWeather;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
