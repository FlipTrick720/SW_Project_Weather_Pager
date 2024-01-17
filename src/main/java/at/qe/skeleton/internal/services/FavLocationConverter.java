package at.qe.skeleton.internal.services;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import at.qe.skeleton.internal.model.FavLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * JSF Converter for FavLocation objects.
 * Converts between FavLocation objects and their String representations in JSF UI components.
 */
@Component
@Scope("application")
@FacesConverter("favLocationConverter") // Name by which this converter can be referenced in JSF components
public class FavLocationConverter implements Converter {

    @Autowired
    private FavLocationService favLocationService; // Inject your FavLocationService here

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            // Convert the String representation to FavLocation object using the service
            // Assuming favLocationService can retrieve a FavLocation by its ID (long)
            return favLocationService.loadLocation(Long.parseLong(value));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof FavLocation favLocation) {
            // Convert FavLocation object to its String representation (for displaying purposes)
            // Assuming FavLocation's ID is of type long and you want to display the ID
            return String.valueOf(favLocation.getId());
        }
        return null;
    }



}
