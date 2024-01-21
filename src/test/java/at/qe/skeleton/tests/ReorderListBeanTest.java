package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.model.UserxRole;
import at.qe.skeleton.internal.services.FavLocationConverter;
import at.qe.skeleton.internal.services.FavLocationService;
import at.qe.skeleton.internal.services.UserxService;
import at.qe.skeleton.internal.ui.beans.ReorderListBean;
import at.qe.skeleton.internal.ui.beans.SessionInfoBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import org.junit.jupiter.api.Test;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@WebAppConfiguration
public class ReorderListBeanTest {

    @Autowired
    private ReorderListBean reorderListBean;
    @Autowired
    private UserxService userxService;

    @MockBean
    private FavLocationService favLocationService;

    @MockBean
    private SessionInfoBean sessionInfoBean;

    @Test
    @WithMockUser(username = "admin", authorities = {"USER"})
    public void testGetFilteredFavLocations() {
        //create user
        Userx user = new Userx();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRoles(Set.of(UserxRole.USER));

        //create Favorite Locations
        FavLocation favLocation1 = new FavLocation();
        FavLocation favLocation2 = new FavLocation();
        FavLocation favLocation3 = new FavLocation();
        favLocation1.setId(1L);
        favLocation1.setUser(user);
        favLocation1.setIndex(0);
        favLocation1.setName("Location1");
        favLocation2.setId(2L);
        favLocation2.setIndex(1);
        favLocation2.setUser(user);
        favLocation2.setName("Location2");
        favLocation3.setId(3L);
        favLocation3.setIndex(2);
        favLocation3.setName("Location3");
        favLocation3.setUser(user);
        List<FavLocation> favLocations = Arrays.asList(favLocation1, favLocation2, favLocation3);
        reorderListBean.setFilteredFavLocations(favLocations);
        reorderListBean.setFavLocations(favLocations);

        // Test when filterValue is empty
        reorderListBean.setFilterValue("");
        assertEquals(favLocations, reorderListBean.getFilteredFavLocations());

        // Test when filterValue is not empty
        reorderListBean.setFilterValue("Location");
        List<FavLocation> filteredLocations1 = reorderListBean.getFilteredFavLocations();
        assertEquals(3, filteredLocations1.size());

        reorderListBean.setFilterValue("Location1");
        List<FavLocation> filteredLocations2 = reorderListBean.getFilteredFavLocations();
        assertEquals(1, filteredLocations2.size());
        assertEquals("Location1", filteredLocations2.get(0).getName());
    }


    @Test
    public void testOnSelectWithFavLocation() {
        ContextMocker.mockFacesContext();
        // Mock the event object
        SelectEvent<FavLocation> mockEvent = mock(SelectEvent.class);
        FavLocation mockLocation = new FavLocation();
        when(mockEvent.getObject()).thenReturn(mockLocation);

        // Call the onSelect method
        ReorderListBean yourInstance = new ReorderListBean(); // Replace with the actual class name
        yourInstance.onSelect(mockEvent);

        // Verify that the FacesMessage is created and added to the context
        FacesContext context = FacesContext.getCurrentInstance();
        verify(context, times(1)).addMessage(eq(null), any(FacesMessage.class));
    }

}
