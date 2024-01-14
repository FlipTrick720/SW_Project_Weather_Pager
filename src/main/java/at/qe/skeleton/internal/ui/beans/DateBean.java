package at.qe.skeleton.internal.ui.beans;

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
    public
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
    private DailyAggregationDTO averageWeatherData;

    /**
     * Checks if the selected date range is valid.
     * The start date must be before the end date, and the range should not exceed 14 days.
     *
     * @return true if the date range is valid, false otherwise.
     */
    public boolean isDateRangeValid() {
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
     * Calculates the midpoint date of the selected date range.
     * If either the start date or the end date is null, this method returns null.
     *
     * @return The midpoint date of the date range, or null if start or end dates are not set.
     */
    public LocalDate calculateMidpointDate() {
        if (startDate == null || endDate == null) {
            return null;
        }
        int daysBetween = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        return startDate.plusDays(daysBetween / 2);
    }
    /**
     * Fetches the average weather data based on the midpoint of the selected date range.
     * This method aggregates historical weather data for the past five years from the midpoint date.
     * If the midpoint date calculation fails, logs an error and returns null.
     *
     * @return A {@link DailyAggregationDTO} containing average weather data, or null in case of errors.
     */
    public DailyAggregationDTO fetchAverageWeatherData() {
        LocalDate midpointDate = calculateMidpointDate();
        if (midpointDate == null) {
            LOGGER.error("Midpoint date calculation error.");
            return null;
        }

        List<DailyAggregationDTO> pastWeatherData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            LocalDate dateToCheck = midpointDate.minusYears(i);
            GeocodingDTO geocodingData1 = autocompleteBean.getSelectedGeocodingDTO();
            double latitude = geocodingData1.lat();
            double longitude = geocodingData1.lon();

            try {
                DailyAggregationDTO yearlyWeather = this.weatherApiRequestService.retrieveDailyAggregationWeather(latitude, longitude, dateToCheck);
                pastWeatherData.add(yearlyWeather);
            } catch (Exception e) {
                LOGGER.error("Error retrieving weather data for date: " + dateToCheck, e);
            }
        }

        return calculateAverageWeather(pastWeatherData);
    }
    /**
     * Calculates the average weather data from a list of daily weather data.
     * This method averages various weather parameters like temperature, humidity, precipitation, etc.
     * If the provided list is null or empty, the method returns null.
     *
     * @param weatherDataList List of {@link DailyAggregationDTO} objects to average.
     * @return A {@link DailyAggregationDTO} representing the average weather data, or null if the input list is null or empty.
     */

    public DailyAggregationDTO calculateAverageWeather(List<DailyAggregationDTO> weatherDataList) {
        if (weatherDataList == null || weatherDataList.isEmpty()) {
            return null;
        }

        double avgLat = 0, avgLon = 0, avgPrecipitationTotal = 0, avgPressureAfternoon = 0, avgHumidityAfternoon = 0;
        double avgTempMin = 0, avgTempMax = 0, avgTempAfternoon = 0, avgTempNight = 0, avgTempEvening = 0, avgTempMorning = 0;
        double avgWindSpeed = 0, avgWindDirection = 0;

        for (DailyAggregationDTO weather : weatherDataList) {
            avgLat += weather.latitude();
            avgLon += weather.longitude();
            avgPrecipitationTotal += weather.precipitation().total();
            avgPressureAfternoon += weather.pressure().afternoon();
            avgHumidityAfternoon += weather.humidity().afternoon();

            DailyAggregationDTO.Temperature temp = weather.temperature();
            avgTempMin += temp.min();
            avgTempMin = Math.round(avgTempMin);
            avgTempMax += temp.max();
            avgTempMax = Math.round(avgTempMax);
            avgTempAfternoon += temp.afternoon();
            avgTempAfternoon = Math.round(avgTempAfternoon);
            avgTempNight += temp.night();
            avgTempNight = Math.round(avgTempNight);
            avgTempEvening += temp.evening();
            avgTempEvening = Math.round(avgTempEvening);
            avgTempMorning += temp.morning();
            avgTempMorning = Math.round(avgTempMorning);

            DailyAggregationDTO.Wind.Max windMax = weather.wind().max();
            avgWindSpeed += windMax.speed();
            avgWindSpeed = Math.round(avgWindSpeed);
            avgWindDirection += windMax.direction();
            avgWindDirection = Math.round(avgWindDirection);
        }

        int count = weatherDataList.size();
        return new DailyAggregationDTO(
                avgLat / count, avgLon / count, "Average", LocalDate.now(), "Average",
                new DailyAggregationDTO.CloudCover((int) (avgHumidityAfternoon / count)),
                new DailyAggregationDTO.Humidity((int) (avgHumidityAfternoon / count)),
                new DailyAggregationDTO.Precipitation((int) (avgPrecipitationTotal / count)),
                new DailyAggregationDTO.Pressure(avgPressureAfternoon / count),
                new DailyAggregationDTO.Temperature(
                        avgTempMin / count, avgTempMax / count, avgTempAfternoon / count, avgTempNight / count, avgTempEvening / count, avgTempMorning / count),
                new DailyAggregationDTO.Wind(new DailyAggregationDTO.Wind.Max(avgWindSpeed / count, avgWindDirection / count))
        );
    }
    /**
     * Fetches and displays the average weather data.
     * This method is intended for UI interaction, and the result can be used for display purposes.
     *
     * @return A navigation outcome as a String, can be null. It indicates the result of the operation for UI purposes.
     */

    public String fetchAndDisplayAverageWeatherData() {
        averageWeatherData = fetchAverageWeatherData();
        return null; // You can return a navigation outcome if needed
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

    public SessionInfoBean getSessionInfoBean() {
        return sessionInfoBean;
    }

    public void setSessionInfoBean(SessionInfoBean sessionInfoBean) {
        this.sessionInfoBean = sessionInfoBean;
    }

    public DailyAggregationDTO getAverageWeatherData() {
        return averageWeatherData;
    }

    public void setAverageWeatherData(DailyAggregationDTO averageWeatherData) {
        this.averageWeatherData = averageWeatherData;
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

}
