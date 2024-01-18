package at.qe.skeleton.internal.services;

import at.qe.skeleton.external.model.currentandforecast.DailyAggregationDTO;
import at.qe.skeleton.external.services.WeatherApiRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherService {

    @Autowired
    private WeatherApiRequestService weatherApiRequestService;

    public List<DailyAggregationDTO> retrieveWeatherDataForRange(LocalDate startDate, LocalDate endDate, double latitude, double longitude) {
        List<DailyAggregationDTO> weatherDataList = new ArrayList<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            try {
                DailyAggregationDTO dailyWeather = weatherApiRequestService.retrieveDailyAggregationWeather(latitude, longitude, date);
                weatherDataList.add(dailyWeather);
            } catch (Exception e) {
                // Loggen Sie den Fehler oder handhaben Sie ihn entsprechend
            }
        }
        return weatherDataList;
    }

    public DailyAggregationDTO calculateAverageWeatherData(LocalDate midpointDate, double latitude, double longitude) {
        List<DailyAggregationDTO> pastWeatherData = new ArrayList<>();
        for (int i = 0; i < 5; i++) { // Letzten 5 Jahre
            LocalDate dateToCheck = midpointDate.minusYears(i);
            try {
                DailyAggregationDTO yearlyWeather = weatherApiRequestService.retrieveDailyAggregationWeather(latitude, longitude, dateToCheck);
                pastWeatherData.add(yearlyWeather);
            } catch (Exception e) {
                // Loggen Sie den Fehler oder handhaben Sie ihn entsprechend
            }
        }
        return calculateAverageWeather(pastWeatherData);
    }

    public static DailyAggregationDTO calculateAverageWeather(List<DailyAggregationDTO> weatherDataList) {
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
}
