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

/**
 * Managed bean for handling date-related operations within a user session.
 * This bean is responsible for managing start and end dates for weather data retrieval,
 * invoking weather and geocoding service calls, and maintaining the state of these operations.
 */
@Component
@Scope("session")
public class DateBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateBean.class);

    @Autowired
    private WeatherApiRequestService weatherApiRequestService;

    @Autowired
    SessionInfoBean sessionInfoBean;

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

    /**
     * Checks if the selected date range is valid.
     * The start date must be before the end date, and the range should not exceed 14 days.
     *
     * @return true if the date range is valid, false otherwise.
     */
    private boolean isDateRangeValid() {
        System.out.println("submit button pressed yes");
        if (startDate == null || endDate == null) {
            return false;
        }
        if (startDate.isAfter(endDate)) {
            return false;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) <= 14;
    }

    /**
     * Handles the submission of selected dates.
     * Validates the date range and retrieves weather data for the specified period and location.
     *
     * @return A string indicating the outcome of the operation, such as "success" or "error".
     */
    public String submitDates() {
        buttonPressed = true;
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
                DailyAggregationDTO dailyWeather = this.weatherApiRequestService.retrieveDailyAggregationWeather(latitude, longitude, date);
                weatherDataList.add(dailyWeather);
            } catch (Exception e) {
                LOGGER.error("Error retrieving weather data for date: " + date, e);
            }
        }

        for(DailyAggregationDTO d : weatherDataList) {
            System.out.println(d.date());
        }

        System.out.println("button pressed" + buttonPressed);
        return "success"; // Return the appropriate outcome for success
    }

/**
*Getters and setters for all the variables in this class.
*/
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

//    public String getTitle() {
//        return sessionInfoBean.isLoggedIn() && sessionInfoBean.isPremium() ? "8-Day-Forecast" : "3-Day-Forecast";
//    }
}
