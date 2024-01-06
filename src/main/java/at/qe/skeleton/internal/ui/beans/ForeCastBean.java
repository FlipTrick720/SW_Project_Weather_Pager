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

    @Autowired
    SessionInfoBean sessionInfoBean;

    public String getImageUrl(String icon){
        return "https://openweathermap.org/img/wn/" + icon + "@2x.png";
    }

    public List<DailyWeatherDTO> getDailyWeather() {
        if (sessionInfoBean.isLoggedIn() && sessionInfoBean.isPremium()){
            return weatherBean.getWeather().dailyWeather();
        } else {
            return weatherBean.getWeather().dailyWeather().subList(0, 4);
        }
    }

    public String getTitle() {
        return sessionInfoBean.isLoggedIn() && sessionInfoBean.isPremium() ? "8-Day-Forecast" : "3-Day-Forecast";
    }
}
