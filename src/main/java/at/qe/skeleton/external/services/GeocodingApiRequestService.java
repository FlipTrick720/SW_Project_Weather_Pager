package at.qe.skeleton.external.services;

import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Scope("application")
@Component
@Validated
public class GeocodingApiRequestService {

    private static final String GEOCODING_URI = "/geo/1.0/direct";
    private static final String CITY_PARAMETER = "q";

    @Autowired
    private RestClient restClient;

    public List<GeocodingDTO> retrieveGeocodingData(String city) {
        String encodedCity = encodeCity(city);
        ResponseEntity<List<GeocodingDTO>> responseEntity = this.restClient.get()
                .uri(UriComponentsBuilder.fromPath(GEOCODING_URI)
                        .queryParam(CITY_PARAMETER, encodedCity)
                        .queryParam("limit", 1)
                        .build().toUriString())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<GeocodingDTO>>() {});

        // todo: introduce error handling using responseEntity.getStatusCode.isXXXError
        return responseEntity.getBody();
    }

    private String encodeCity(String city){
        String[] parts = city.split(",");
        String trimmedCity = parts[0];
        String encodedCity =  URLEncoder.encode(trimmedCity, StandardCharsets.UTF_8);
        return encodedCity;
    }
}
