package at.qe.skeleton.internal.services;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import at.qe.skeleton.internal.model.FavLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("application")
@FacesConverter("favLocationConverter")
public class FavLocationConverter implements Converter {
    @Autowired
    private FavLocationService favLocationService; // Inject your FavLocationService here

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            // Convert the String representation to FavLocation object using the service
            return favLocationService.getLocationByName(value); // Assuming you can retrieve a FavLocation by ID
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof FavLocation) {
            // Convert FavLocation object to its String representation (for displaying purposes)
            return String.valueOf(((FavLocation) value).getName()); // Assuming you want to display the FavLocation's ID
        }
        return null;
    }
}

