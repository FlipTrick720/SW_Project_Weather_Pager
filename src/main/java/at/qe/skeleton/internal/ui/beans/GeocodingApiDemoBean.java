package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import at.qe.skeleton.external.services.GeocodingApiRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Demonstrates the working api and what the raw request data would look like
 * <br><br>
 * This class is part of the skeleton project provided for students of the
 * course "Software Architecture" offered by Innsbruck University.
 */
@Component
@Scope("view")
public class GeocodingApiDemoBean {

    @Autowired
    private GeocodingApiRequestService geocodingApiRequestService;
    private String geocodingData;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApiBean.class);
    private String cityName;
    @Autowired
    private AutocompleteBean autocompleteBean;
    private double longitude;
    private double latitude;


    public void init() {
        try {
            String input = autocompleteBean.getText();
            List<GeocodingDTO> answer = this.geocodingApiRequestService.retrieveGeocodingData(input);
            latitude = answer.get(0).lat();
            longitude = answer.get(0).lon();
            cityName = input;
            this.geocodingData = "Lat: " + latitude + " Lon: " + longitude;
        } catch (final Exception e) {
            LOGGER.error("error in request", e);
        }
    }

    public String getGeocodingData() {
        return geocodingData;
    }

    public void setGeocodingData(String geocodingData) {
        this.geocodingData = geocodingData;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
