package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.services.GeocodingApiRequestService;
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
public class GeocodingApiDemoBean {

    @Autowired
    private GeocodingApiRequestService geocodingApiRequestService;
    private String geocodingData;
    private String cityName;
    @Autowired
    private AutocompleteBean autocompleteBean;
    private double longitude;
    private double latitude;

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
