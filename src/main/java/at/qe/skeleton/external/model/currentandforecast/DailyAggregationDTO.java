package at.qe.skeleton.external.model.currentandforecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public record DailyAggregationDTO(
        @JsonProperty("lat") double latitude,
        @JsonProperty("lon") double longitude,
        @JsonProperty("timezone") String timezone,
        @JsonProperty("date") LocalDate date,
        @JsonProperty("units") String units,
        @JsonProperty("cloud_cover") CloudCover cloudCover,
        @JsonProperty("humidity") Humidity humidity,
        @JsonProperty("precipitation") Precipitation precipitation,
        @JsonProperty("pressure") Pressure pressure,
        @JsonProperty("temperature") Temperature temperature,
        @JsonProperty("wind") Wind wind
) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public record CloudCover(@JsonProperty("afternoon") int afternoon) {}
    public record Humidity(@JsonProperty("afternoon") int afternoon) {}
    public record Precipitation(@JsonProperty("total") double total) {}
    public record Pressure(@JsonProperty("afternoon") double afternoon) {}

    public record Temperature(
            @JsonProperty("min") double min,
            @JsonProperty("max") double max,
            @JsonProperty("afternoon") double afternoon,
            @JsonProperty("night") double night,
            @JsonProperty("evening") double evening,
            @JsonProperty("morning") double morning
    ) {}

    public record Wind(@JsonProperty("max") Max max) {
        public record Max(
                @JsonProperty("speed") double speed,
                @JsonProperty("direction") double direction
        ) {}
    }
}
