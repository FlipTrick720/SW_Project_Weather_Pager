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
