package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.misc.DailyWeatherDTO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("view")
public class DateBean {

    @Autowired
    private WeatherBean weatherBean;

    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean buttonPressed = false;

    private List<DailyWeatherDTO> selectedWeatherData;

    public List<DailyWeatherDTO> getSelectedWeatherData() {
        return selectedWeatherData;
    }

    public void setSelectedWeatherData(List<DailyWeatherDTO> selectedWeatherData) {
        this.selectedWeatherData = selectedWeatherData;
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

    private boolean isDateRangeValid() {
        if (startDate == null || endDate == null) {
            return false; // Either start or end date is not set
        }
        return !startDate.isAfter(endDate); // Check if start date is not after end date
    }

    public String submitDates() {
        buttonPressed = true;
        System.out.println("submit button pressed yes");
        if (isDateRangeValid()) {
            // Rufen Sie die Methode in der DateBean auf, um die Wetterdaten vorzubereiten
            prepareSelectedWeatherData();
            return "vacation"; // Navigate to vacation.xhtml
        } else {
            // If the date range is not valid, show an error message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid date range. Please select a valid start and end date."));

            return null; // Stay on the current page
        }
    }

    // Methode, um die Wetterdaten für den ausgewählten Zeitraum vorzubereiten
    private void prepareSelectedWeatherData() {
        if (startDate == null || endDate == null) {
            selectedWeatherData = null;
            return;
        }

        List<DailyWeatherDTO> allWeatherData = weatherBean.getWeather().dailyWeather();

        selectedWeatherData = allWeatherData.stream()
                .filter(dto -> !dto.timestamp().isBefore(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                        && !dto.timestamp().isAfter(endDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .collect(Collectors.toList());
    }


}
