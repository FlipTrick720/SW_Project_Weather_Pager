package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.services.UserxService;
import at.qe.skeleton.internal.ui.controllers.UserListController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@WebAppConfiguration
public class UserListControllerTest {
    @Mock
    private UserxService userService;
    @InjectMocks
    private UserListController userListController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Userx user1 = new Userx();
        Userx user2 = new Userx();
        user1.setUsername("User1");
        user1.setPremium(true);
        user2.setUsername("User2");
        user2.setPremium(false);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));
        when(userService.getPremiumUsers()).thenReturn(Arrays.asList(user1));

    }

    @Test
    void testGetUsers(){

        Collection<Userx> users = userListController.getUsers();
        assertEquals(2,users.size());

    }

    @Test
    void testGetPremiumUsers(){
        Collection<Userx> users = userListController.getPremiumUsers();

        assertEquals(1, users.size());
    }

}
