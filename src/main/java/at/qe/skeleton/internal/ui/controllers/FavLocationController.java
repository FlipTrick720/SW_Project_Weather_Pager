package at.qe.skeleton.internal.ui.controllers;

import at.qe.skeleton.external.model.currentandforecast.CurrentAndForecastAnswerDTO;
import at.qe.skeleton.external.model.currentandforecast.misc.CurrentWeatherDTO;
import at.qe.skeleton.external.model.shared.WeatherDTO;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.FavLocationService;
import at.qe.skeleton.internal.services.UserxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("view")
public class FavLocationController implements Serializable {
    @Autowired
    private WeatherApiRequestService weatherApiRequestService;
    @Autowired
    private FavLocationService favLocationService;
    private FavLocation favLocation;
    private Boolean showCurrentSunrise;
    private Boolean showCurrentSunset;
    private Boolean showCurrentTemp;
    private Boolean showCurrentFeelsLike;
    private Boolean showCurrentPressure;
    private Boolean showCurrentHumidity;
    private Boolean showCurrentDewPoint;
    private Boolean showCurrentClouds;
    private Boolean showCurrentUvi;
    private Boolean showCurrentVisibility;
    private Boolean showCurrentWindSpeed;
    private Boolean showCurrentWindDeg;
    private Boolean showCurrentRain;
    private Boolean showCurrentSnow;
    private Boolean showWeatherIcon;
    private Boolean showWeatherMain;

    public FavLocation getFavLocation() {
        return favLocation;
    }

    public void setFavLocation(FavLocation favLocation) {
        this.favLocation = favLocation;
    }

    public String getFeelsLikeTemp(FavLocation favLocation){
        return "Feels like: " + weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather().feelsLikeTemperature() + "°C";
    }

    public String currentTemp(FavLocation favLocation){
        return "Current: " + weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather().temperature() + "°C";
    }

    public WeatherDTO currentWeather(FavLocation favLocation){
        return weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather().weather();
    }
    public String getCurrentConfigWeatherData(FavLocation favLocation){
         CurrentWeatherDTO currentWeather = weatherApiRequestService.retrieveCurrentAndForecastWeather
                (favLocation.getLatitude(), favLocation.getLongitude()).currentWeather();
        StringBuilder returnString = new StringBuilder();
        if (showCurrentSunrise){
            returnString.append("Sunrise: " + currentWeather.sunrise() + "; ");
        }
        if (showCurrentSunset){
            returnString.append("Sunset: " + currentWeather.sunset() + "; ");
        }
        if (showCurrentTemp){
            returnString.append("Temp: " + currentWeather.temperature() + "; ");
        }
        if (showCurrentFeelsLike){
            returnString.append("Feels like: " + currentWeather.feelsLikeTemperature() + "; ");
        }
        if (showCurrentPressure){
            returnString.append("Pressure: " + currentWeather.pressure() + "; ");
        }
        if (showCurrentHumidity){
            returnString.append("Humidity: " + currentWeather.humidity() + "; ");
        }
        if (showCurrentDewPoint){
            returnString.append("Dew Point: " + currentWeather.dewPoint() + "; ");
        }
        if (showCurrentClouds){
            returnString.append("Clouds: " + currentWeather.clouds() + "; ");
        }
        if (showCurrentUvi){
            returnString.append("UVI: " + currentWeather.uvi() + "; ");
        }
        if (showCurrentVisibility){
            returnString.append("Visibility: " + currentWeather.visibility() + "; ");
        }
        if (showCurrentWindSpeed){
            returnString.append("Wind Speed: " + currentWeather.windSpeed() + "; ");
        }
        if (showCurrentWindDeg){
            returnString.append("Wind Degree: " + currentWeather.windDirection() + "; ");
        }
        if (showCurrentRain){
            returnString.append("Rain: " + currentWeather.rain() + "; ");
        }
        if (showCurrentSnow){
            returnString.append("Humidity: " + currentWeather.snow() + "; ");
        }
        return returnString.deleteCharAt(returnString.length()).toString();
    }
    public void doSafeLocationByName(String city, Userx user){
        favLocationService.StringToFavLocation(city, user);
    }


    public FavLocationService getFavLocationService() {
        return favLocationService;
    }

    public void setFavLocationService(FavLocationService favLocationService) {
        this.favLocationService = favLocationService;
    }

    public Boolean getShowCurrentSunrise() {
        return showCurrentSunrise;
    }

    public void setShowCurrentSunrise(Boolean showCurrentSunrise) {
        this.showCurrentSunrise = showCurrentSunrise;
    }

    public Boolean getShowCurrentSunset() {
        return showCurrentSunset;
    }

    public void setShowCurrentSunset(Boolean showCurrentSunset) {
        this.showCurrentSunset = showCurrentSunset;
    }

    public Boolean getShowCurrentTemp() {
        return showCurrentTemp;
    }

    public void setShowCurrentTemp(Boolean showCurrentTemp) {
        this.showCurrentTemp = showCurrentTemp;
    }

    public Boolean getShowCurrentFeelsLike() {
        return showCurrentFeelsLike;
    }

    public void setShowCurrentFeelsLike(Boolean showCurrentFeelsLike) {
        this.showCurrentFeelsLike = showCurrentFeelsLike;
    }

    public Boolean getShowCurrentPressure() {
        return showCurrentPressure;
    }

    public void setShowCurrentPressure(Boolean showCurrentPressure) {
        this.showCurrentPressure = showCurrentPressure;
    }

    public Boolean getShowCurrentHumidity() {
        return showCurrentHumidity;
    }

    public void setShowCurrentHumidity(Boolean showCurrentHumidity) {
        this.showCurrentHumidity = showCurrentHumidity;
    }

    public Boolean getShowCurrentDewPoint() {
        return showCurrentDewPoint;
    }

    public void setShowCurrentDewPoint(Boolean showCurrentDewPoint) {
        this.showCurrentDewPoint = showCurrentDewPoint;
    }

    public Boolean getShowCurrentClouds() {
        return showCurrentClouds;
    }

    public void setShowCurrentClouds(Boolean showCurrentClouds) {
        this.showCurrentClouds = showCurrentClouds;
    }

    public Boolean getShowCurrentUvi() {
        return showCurrentUvi;
    }

    public void setShowCurrentUvi(Boolean showCurrentUvi) {
        this.showCurrentUvi = showCurrentUvi;
    }

    public Boolean getShowCurrentVisibility() {
        return showCurrentVisibility;
    }

    public void setShowCurrentVisibility(Boolean showCurrentVisibility) {
        this.showCurrentVisibility = showCurrentVisibility;
    }

    public Boolean getShowCurrentWindSpeed() {
        return showCurrentWindSpeed;
    }

    public void setShowCurrentWindSpeed(Boolean showCurrentWindSpeed) {
        this.showCurrentWindSpeed = showCurrentWindSpeed;
    }

    public Boolean getShowCurrentWindDeg() {
        return showCurrentWindDeg;
    }

    public void setShowCurrentWindDeg(Boolean showCurrentWindDeg) {
        this.showCurrentWindDeg = showCurrentWindDeg;
    }

    public Boolean getShowCurrentRain() {
        return showCurrentRain;
    }

    public void setShowCurrentRain(Boolean showCurrentRain) {
        this.showCurrentRain = showCurrentRain;
    }

    public Boolean getShowCurrentSnow() {
        return showCurrentSnow;
    }

    public void setShowCurrentSnow(Boolean showCurrentSnow) {
        this.showCurrentSnow = showCurrentSnow;
    }

    public Boolean getShowWeatherIcon() {
        return showWeatherIcon;
    }

    public void setShowWeatherIcon(Boolean showWeatherIcon) {
        this.showWeatherIcon = showWeatherIcon;
    }

    public Boolean getShowWeatherMain() {
        return showWeatherMain;
    }

    public void setShowWeatherMain(Boolean showWeatherMain) {
        this.showWeatherMain = showWeatherMain;
    }
}
