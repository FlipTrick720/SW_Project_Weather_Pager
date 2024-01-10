package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.currentandforecast.misc.HourlyWeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("view")
public class HourlyWeatherBean {
    @Autowired
    SessionInfoBean sessionInfoBean;

    @Autowired
    WeatherBean weatherBean;

    public String getTitle() {
        return sessionInfoBean.isLoggedIn() && sessionInfoBean.isPremium() ? "Next 48h Details" : "Next 24h Details";
    }

    public List<HourlyWeatherDTO> getHourlyWeather() {
        if (sessionInfoBean.isLoggedIn() && sessionInfoBean.isPremium()){
            return weatherBean.getWeather().hourlyWeather().subList(0, 48);
        } else {
            return weatherBean.getWeather().hourlyWeather().subList(0, 24);
        }
    }
}
