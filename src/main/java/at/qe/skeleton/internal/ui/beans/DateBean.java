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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("view")
public class DateBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateBean.class);

    @Autowired
    private WeatherApiRequestService weatherApiRequestService;
    @Autowired
    private GeocodingApiRequestService geocodingApiRequestService;

    private double latitude;
    private double longitude;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<CurrentAndForecastAnswerDTO> weatherDataList = new ArrayList<>();

    private boolean isDateRangeValid() {
        if (startDate == null || endDate == null) {
            return false;
        }
        if (startDate.isAfter(endDate)) {
            return false;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) <= 14;
    }

    public void submitDates() {
        if (!isDateRangeValid()) {
            LOGGER.error("Invalid date range.");
            return;
        }

        List<GeocodingDTO> geocode = geocodingApiRequestService.retrieveGeocodingData(location);
        if (geocode.isEmpty()) {
            LOGGER.error("Geocoding returned no results.");
            return;
        }

        latitude = geocode.get(0).lat();
        longitude = geocode.get(0).lon();
        weatherDataList.clear();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            try {
                CurrentAndForecastAnswerDTO dailyWeather = weatherApiRequestService.retrieveDailyAggregationWeather(latitude, longitude, date);
                weatherDataList.add(dailyWeather);
            } catch (final Exception e) {
                LOGGER.error("Error retrieving weather for date: " + date, e);
            }
        }
    }


    // Getters and Setters for start and end dates
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public WeatherApiRequestService getWeatherApiRequestService() {
        return weatherApiRequestService;
    }

    public void setWeatherApiRequestService(WeatherApiRequestService weatherApiRequestService) {
        this.weatherApiRequestService = weatherApiRequestService;
    }

    public GeocodingApiRequestService getGeocodingApiRequestService() {
        return geocodingApiRequestService;
    }

    public void setGeocodingApiRequestService(GeocodingApiRequestService geocodingApiRequestService) {
        this.geocodingApiRequestService = geocodingApiRequestService;
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


}
