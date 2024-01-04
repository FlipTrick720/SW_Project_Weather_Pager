package at.qe.skeleton.external.model.geocoding;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Map;

public record GeocodingDTO(
        @JsonProperty("name") String name,
        @JsonProperty("local_names") Map<String, String> localNames,
        @JsonProperty("lat") double lat,
        @JsonProperty("lon") double lon,
        @JsonProperty("country") String country,
        @JsonProperty("state") String state
) implements Serializable {
    // No need to add getters and setters with records
}

