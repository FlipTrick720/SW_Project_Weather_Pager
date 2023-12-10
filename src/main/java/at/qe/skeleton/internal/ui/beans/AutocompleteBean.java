package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.external.services.GooglePlacesAutocompleteApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("session")
public class AutocompleteBean {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Autowired
    GooglePlacesAutocompleteApiService googlePlacesService;

    public List<String> getAutocompletion(String input) {
        return googlePlacesService.getPredictions(input);
    }
}
