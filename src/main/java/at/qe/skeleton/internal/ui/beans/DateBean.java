package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.misc.DailyWeatherDTO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
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

        // Check if start date is before end date
        if (startDate.isAfter(endDate)) {
            return false;
        }

        // Check if date range is within a two-week window
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        if (daysBetween > 14) {
            return false;
        }

        return true; // Date range is valid
    }



    public String submitDates() {
        buttonPressed = true;
        System.out.println("vacation button pressed yes");
        if (isDateRangeValid()) {
            prepareSelectedWeatherData(); // Diese Methode filtert und bereitet die Daten vor.
            return "vacation"; // oder die Seite, die aktualisiert werden soll
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid date range."));
            return null;
        }
    }

    public WeatherBean getWeatherBean() {
        return weatherBean;
    }

    public void setWeatherBean(WeatherBean weatherBean) {
        this.weatherBean = weatherBean;
    }

    public Boolean getButtonPressed() {
        return buttonPressed;
    }

    public void setButtonPressed(Boolean buttonPressed) {
        this.buttonPressed = buttonPressed;
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
