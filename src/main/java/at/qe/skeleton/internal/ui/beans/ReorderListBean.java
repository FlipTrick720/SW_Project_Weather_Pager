package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.FavLocationService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.context.Theme;

import java.io.Serializable;
import java.util.List;

@Named
@Scope("request")
public class ReorderListBean implements Serializable {
    @Autowired
    private FavLocationService favLocationService;
    private List<FavLocation> favLocations;

    @PostConstruct
    public void init() {
        favLocations = favLocationService.getAllLocations();
    }
    public List<FavLocation> getFavLocations() {
        return favLocations;
    }

    public void setFavLocations(List<FavLocation> favLocations) {
        this.favLocations = favLocations;
    }
    public void onSelect(SelectEvent<?> event) {
        Object selectedObject = event.getObject();

        if (selectedObject instanceof FavLocation) {
            FavLocation selectedLocation = (FavLocation) selectedObject;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", selectedLocation.getName()));
        } else {
            // Handle other cases or log unexpected scenarios
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Selection", "Selected object is not of type FavLocation"));
        }
    }


    public void onUnselect(UnselectEvent<FavLocation> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.toString()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }

}
