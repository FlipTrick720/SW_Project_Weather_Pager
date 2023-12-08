package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.model.PredictionDTO;
import at.qe.skeleton.internal.services.GooglePlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class SearchBean {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Autowired
    GooglePlacesService googlePlacesService;

    public List<String> complete(String query) {
        // Call a method to fetch suggestions from the Google Places API
        return googlePlacesService.getPredictions(query);
    }
}
