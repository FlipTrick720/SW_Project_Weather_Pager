package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.TokenService;
import at.qe.skeleton.internal.services.UserxService;
import at.qe.skeleton.internal.ui.beans.ResetPasswordBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class ResetPasswordBeanTest {

    @Mock
    private UserxService userxService;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private ResetPasswordBean resetPasswordBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock FacesContext for testing
        // Utility class needed as the method setCurrentInstance is protected
        FacesContext facesContext = ContextMocker.mockFacesContext();
    }

    @Test
    void testInitInvalidToken() {
        // Mock the token service to return null, indicating an invalid token
        when(tokenService.getUserByConfirmationToken(anyString())).thenReturn(null);

        // Call the init method
        resetPasswordBean.init();

        System.out.println(tokenService.getUserByConfirmationToken("irgendwas"));

        // Verify that an error message is added
        verify(FacesContext.getCurrentInstance(), times(1)).addMessage(any(), any(FacesMessage.class));

        // verify that isValidToken is false
        assertFalse(resetPasswordBean.isValidToken());
    }

    @Test
    void testInitValidToken() {
        // Mock the token service to return a user, indicating a valid token
        // input value is null here, as no key results in a valid null value
        when(tokenService.getUserByConfirmationToken(null)).thenReturn(new Userx());

        // Call the init method
        resetPasswordBean.init();

        // Verify that no error message is added
        verify(FacesContext.getCurrentInstance(), never()).addMessage(any(), any(FacesMessage.class));

        assertTrue(resetPasswordBean.isValidToken());
    }

    @Test
    void testResetPassword() {
        // mock the token service to return a user
        when(tokenService.getUserByConfirmationToken(anyString())).thenReturn(new Userx());
        // mock the user service to return the same user after saving
        when(userxService.saveUser(any(Userx.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // set valid token
        resetPasswordBean.setToken("validToken");
        // set new password
        resetPasswordBean.setNewPassword("newPassword");

        // call the resetPassword method
        resetPasswordBean.resetPassword();

        // Verify saveUser method was called with a user
        verify(userxService, times(1)).saveUser(any(Userx.class));
        // verify deleteToken method was called
        verify(tokenService, times(1)).deleteToken(any());
    }
}

