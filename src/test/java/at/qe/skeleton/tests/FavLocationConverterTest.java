package at.qe.skeleton.tests;

import static org.junit.jupiter.api.Assertions.*;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.services.FavLocationConverter;
import at.qe.skeleton.internal.services.FavLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FavLocationConverterTest {

    @InjectMocks
    private FavLocationConverter favLocationConverter;

    @Mock
    private FavLocationService favLocationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAsObjectValidValue() {
        // Mock data
        long favLocationId = 1L;
        FavLocation mockFavLocation = new FavLocation();
        mockFavLocation.setId(favLocationId);

        // Mock behavior of FavLocationService
        Mockito.when(favLocationService.loadLocation(favLocationId)).thenReturn(mockFavLocation);

        // Test the getAsObject method
        FacesContext context = Mockito.mock(FacesContext.class);
        UIComponent component = Mockito.mock(UIComponent.class);
        Object result = favLocationConverter.getAsObject(context, component, String.valueOf(favLocationId));

        // Verify that the expected FavLocation object is returned
        assertNotNull(result);
        assertTrue(result instanceof FavLocation);
        assertEquals(favLocationId, ((FavLocation) result).getId());

        // Verify that FavLocationService.loadLocation is called with the correct ID
        Mockito.verify(favLocationService, Mockito.times(1)).loadLocation(favLocationId);
    }

    @Test
    public void testGetAsObjectInvalidValue() {
        // Test the getAsObject method with an invalid value
        FacesContext context = Mockito.mock(FacesContext.class);
        UIComponent component = Mockito.mock(UIComponent.class);
        Object result = favLocationConverter.getAsObject(context, component, "");

        // Verify that null is returned for an empty value
        assertNull(result);

        // Verify that FavLocationService.loadLocation is not called
        Mockito.verify(favLocationService, Mockito.never()).loadLocation(Mockito.anyLong());
    }

    @Test
    public void testGetAsString() {
        // Mock data
        long favLocationId = 1L;
        FavLocation mockFavLocation = new FavLocation();
        mockFavLocation.setId(favLocationId);

        // Test the getAsString method
        FacesContext context = Mockito.mock(FacesContext.class);
        UIComponent component = Mockito.mock(UIComponent.class);
        String result = favLocationConverter.getAsString(context, component, mockFavLocation);

        // Verify that the expected String representation is returned
        assertNotNull(result);
        assertEquals(String.valueOf(favLocationId), result);
    }

    @Test
    public void testGetAsStringNullValue() {
        // Test the getAsString method with a null value
        FacesContext context = Mockito.mock(FacesContext.class);
        UIComponent component = Mockito.mock(UIComponent.class);
        String result = favLocationConverter.getAsString(context, component, null);

        // Verify that null is returned for a null value
        assertNull(result);
    }
}

