package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.FavLocationService;
import at.qe.skeleton.internal.services.UserxService;
import at.qe.skeleton.internal.ui.beans.ReorderListBean;
import at.qe.skeleton.internal.ui.beans.SessionInfoBean;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@WebAppConfiguration
public class ReorderListBeanTest {

    @Autowired
    private FavLocationService favLocationService;
    @Autowired
    private UserxService userxService;

    @Mock
    private SessionInfoBean sessionInfoBean;

    @Autowired
    private ReorderListBean reorderListBean;

    @Mock
    private FacesContext facesContext;

    @Mock
    private ExternalContext externalContext;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DirtiesContext
    @WithMockUser(username = "admin", authorities = {"USER"})
    public void testDeleteLocation() {

        // Mocking sessionInfoBean
        when(sessionInfoBean.getCurrentUser()).thenReturn(userxService.loadUser("admin"));

        // Create a test location
        Long testLocId = 1L;
        FavLocation favLocation1 = new FavLocation();
        favLocation1.setId(testLocId);
        favLocation1.setName("testLocation");
        favLocation1.setUser(sessionInfoBean.getCurrentUser());
        favLocationService.saveLocation(favLocation1);

        // Mocking external context request parameter
        when(facesContext.getExternalContext()).thenReturn(externalContext);
        when(externalContext.getRequestParameterMap()).thenReturn(Collections.singletonMap("idFavLocation", "1"));

        // Perform the deletion
        reorderListBean.deleteLocation();

        assertTrue(reorderListBean.getFavLocations().isEmpty());
    }
}
