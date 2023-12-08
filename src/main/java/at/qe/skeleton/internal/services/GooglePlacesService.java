package at.qe.skeleton.internal.services;

import at.qe.skeleton.internal.model.GooglePlacesApiResponseDTO;
import at.qe.skeleton.internal.model.PredictionDTO;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceAutocompleteRequest;
import com.google.maps.PlacesApi;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.PlaceAutocompleteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is part of the skeleton project provided for students of the
 * course "Software Architecture" offered by Innsbruck University.
 */
@Scope("application")
@Component
@Validated
public class GooglePlacesService {
    public List<String> getPredictions(String input) {
        List<String> results = new ArrayList<>();
        // Step 1: Create GeoApiContext
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAy_i1TSaLaFpcoiaFGCVsREJAIRWTKiyQ")
                .build();

        // Step 2: Call Autocomplete API
        try {
            PlaceAutocompleteRequest.SessionToken token = new PlaceAutocompleteRequest.SessionToken();
            AutocompletePrediction[] predictions = PlacesApi.placeAutocomplete(context,input, token).types(PlaceAutocompleteType.CITIES).await();

            // Step 3: Handle the results
            for (AutocompletePrediction prediction : predictions) {
                results.add(prediction.description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }


}
