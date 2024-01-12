package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.CurrentAndForecastAnswerDTO;
import at.qe.skeleton.external.model.currentandforecast.DailyAggregationDTO;
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
    @Autowired
    private AutocompleteBean autocompleteBean;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<DailyAggregationDTO> weatherDataList = new ArrayList<>();
    @Autowired
    private WeatherBean weatherBean;
    private Boolean buttonPressed = false;


    private boolean isDateRangeValid() {
        buttonPressed = true;
        System.out.println("submit button pressed yes");
        if (startDate == null || endDate == null) {
            return false;
        }
        if (startDate.isAfter(endDate)) {
            return false;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) <= 14;
    }

    public String submitDates() {
        if (!isDateRangeValid()) {
            LOGGER.error("Invalid date range.");
            return "error"; // Return an appropriate outcome for invalid date range
        }

        GeocodingDTO geocodingData = autocompleteBean.getSelectedGeocodingDTO();
        if (geocodingData == null) {
            LOGGER.error("No location selected.");
            return "error"; // Return an appropriate outcome for no location selected
        }

        double latitude = geocodingData.lat();
        double longitude = geocodingData.lon();

        weatherDataList.clear();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            try {
                DailyAggregationDTO dailyWeather = weatherApiRequestService.retrieveDailyAggregationWeather(latitude, longitude, date);
                weatherDataList.add(dailyWeather);
            } catch (Exception e) {
                LOGGER.error("Error retrieving weather data for date: " + date, e);
            }
        }

        return "success"; // Return the appropriate outcome for success
    }


    public Boolean getButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(Boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
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

    public AutocompleteBean getAutocompleteBean() {
        return autocompleteBean;
    }

    public void setAutocompleteBean(AutocompleteBean autocompleteBean) {
        this.autocompleteBean = autocompleteBean;
    }

    public List<DailyAggregationDTO> getWeatherDataList() {
        return weatherDataList;
    }

    public void setWeatherDataList(List<DailyAggregationDTO> weatherDataList) {
        this.weatherDataList = weatherDataList;
    }

    public WeatherBean getWeatherBean() {
        return weatherBean;
    }

    public void setWeatherBean(WeatherBean weatherBean) {
        this.weatherBean = weatherBean;
    }
}
