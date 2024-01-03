package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.misc.DailyWeatherDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Scope("view")
public class ForeCastBean {

    @Autowired
    WeatherBean weatherBean;

    public String getDateFormattedFromTimestamp(Instant timestamp){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d", Locale.ENGLISH);
        return timestamp.atZone(ZoneId.systemDefault()).toLocalDate().format(formatter);
    }

    public String getTimeFormattedFromTimestamp(Instant timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
        return timestamp.atZone(ZoneId.systemDefault()).toLocalTime().format(formatter);
    }


    public String getImageUrl(String icon){
        return "https://openweathermap.org/img/wn/" + icon + "@2x.png";
    }

    public List<DailyWeatherDTO> getDailyWeather() {
        return weatherBean.getWeather().dailyWeather();
    }
}
