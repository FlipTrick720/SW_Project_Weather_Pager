package at.qe.skeleton.internal.ui.beans;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.services.FavLocationService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.List;

/**
 * Bean for managing and interacting with the favorite location list on the homepage.
 */
@Named
@Scope("request")
public class ReorderListBean implements Serializable {
    @Autowired
    private FavLocationService favLocationService;
    @Autowired
    private SessionInfoBean sessionInfoBean;
    private List<FavLocation> favLocations;

    /**
     * Initializes the favorite locations list for the current user.
     */
    @PostConstruct
    public void init() {
        favLocations = favLocationService.getUserLocations(sessionInfoBean.getCurrentUser());
    }

    public List<FavLocation> getFavLocations() {
        return favLocations;
    }

    public void setFavLocations(List<FavLocation> favLocations) {
        this.favLocations = favLocations;
    }

    /**
     * Handles the case where a favorite location is selected from the list.
     *
     * @param event The selection event
     */
    public void onSelect(SelectEvent<?> event) {
        Object selectedObject = event.getObject();

        if (selectedObject instanceof FavLocation selectedLocation) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", selectedLocation.getName()));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Selection", "Selected object is not of type FavLocation"));
        }
    }



    /**
     * Handles the reordering of the favorite locations list.
     */
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
        favLocationService.updateLocations(favLocations);
    }

    /**
     * Deletes the currently selected Location from the List.
     * The ID of the location to be deleted is obtained from the request parameter 'idFavLocation' (defined in the /secured/welcome.xhtml).
     */
    public void deleteLocation() {
        long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idFavLocation"));

        for (FavLocation favLocation : favLocations) {
            if (favLocation.getId() == id) {
                favLocationService.deleteLocation(favLocation);
                this.favLocations = favLocationService.getUserLocations(sessionInfoBean.getCurrentUser());
                break;
            }
        }
    }
}