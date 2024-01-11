package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.model.geocoding.GeocodingDTO;
import at.qe.skeleton.external.services.GeocodingApiRequestService;
import at.qe.skeleton.external.services.GooglePlacesAutocompleteApiService;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.faces.event.ValueChangeListener;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Scope("session")
public class AutocompleteBean {

    private final Map<String, GeocodingDTO> currentFiveSuggestedDTOs = new HashMap<>();
    private GeocodingDTO selectedGeocodingDTO;

    @Autowired
    GeocodingApiRequestService geocodingApiRequestService;

    public List<GeocodingDTO> getAutocompletion(String input) {
         List<GeocodingDTO> currentSuggestions = geocodingApiRequestService.retrieveGeocodingData(input);

         if (currentSuggestions != null){
             currentSuggestions.forEach(this::saveCurrentSuggestions); //save current suggestions in a map to be able to retrieve them later
         }
         return currentSuggestions;
    }

    public void onItemSelect(SelectEvent<String> event){
        String geocodingDTOasString = event.getObject();
        selectedGeocodingDTO = currentFiveSuggestedDTOs.get(geocodingDTOasString);

        //debug
        System.out.println(
                "Name: " + selectedGeocodingDTO.name() +
                " Lat: " + selectedGeocodingDTO.lat() +
                " Lon: " + selectedGeocodingDTO.lon());
    }

    public void saveCurrentSuggestions(GeocodingDTO geocodingDTO){
        currentFiveSuggestedDTOs.put(geocodingDTO.toString(), geocodingDTO); //toString is needed to guarantee uniqueness
    }

    public String getDisplayName(GeocodingDTO geocodingDTO){ //this is displayed in the autocomplete dropdown
        if (geocodingDTO == null){
            return "";
        }
        return geocodingDTO.name() + ", " + geocodingDTO.country();
    }

    public GeocodingDTO getSelectedGeocodingDTO() {
        return selectedGeocodingDTO;
    }
}
