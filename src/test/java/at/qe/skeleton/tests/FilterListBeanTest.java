package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.FavLocation;
import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.PaymentStatus;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.ui.beans.FilterListBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class FilterListBeanTest {

    @Autowired
    private FilterListBean filterListBean;

    @Test
    @WithMockUser(username = "admin", authorities = {"USER"})
    public void testGetFilteredFavLocations() {
        FavLocation location1 = new FavLocation();
        FavLocation location2 = new FavLocation();
        location1.setId(1L);
        location1.setName("Location1");
        location2.setId(2L);
        location2.setName("Location2");

        List<FavLocation> favLocations = Arrays.asList(location1, location2);

        // Test without filtering
        List<FavLocation> result = filterListBean.getFilteredFavLocations(favLocations);
        assertEquals(favLocations, result);

        // Test with filtering
        filterListBean.setFilterLocation("Location1");
        result = filterListBean.getFilteredFavLocations(favLocations);
        assertEquals(Arrays.asList(location1), result);
    }

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    public void testGetPaymentHistoryList() {
        Userx user1 = new Userx();
        Userx user2 = new Userx();
        user1.setUsername("user1");
        user2.setUsername("user2");



        PaymentHistory payment1 = new PaymentHistory();
        PaymentHistory payment2 = new PaymentHistory();
        payment1.setUser(user1);
        payment1.setPaymentStatus(PaymentStatus.PAYED);
        payment2.setUser(user2);
        payment2.setPaymentStatus(PaymentStatus.PAYED);

        List<PaymentHistory> paymentHistoryList = Arrays.asList(payment1, payment2);

        // Test without filtering
        List<PaymentHistory> result = filterListBean.getPaymentHistoryList(paymentHistoryList);
        assertEquals(paymentHistoryList, result);

        // Test with filtering
        filterListBean.setFilterUser("user1");
        result = filterListBean.getPaymentHistoryList(paymentHistoryList);
        assertEquals(Arrays.asList(payment1), result);

        filterListBean.setFilterUser("paye");
        result = filterListBean.getPaymentHistoryList(paymentHistoryList);
        assertEquals(paymentHistoryList, result);
    }

    @Test
    public void testGetUserList() {
        Userx user1 = new Userx();
        Userx user2 = new Userx();
        user1.setUsername("user1");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user2.setUsername("user2");
        user2.setFirstName("Lisa");
        user2.setLastName("Müller");

        Collection<Userx> userList = Arrays.asList(user1, user2);

        // Test without filtering
        Collection<Userx> result = filterListBean.getUserList(userList);
        assertEquals(userList, result);

        // Test with filtering
        filterListBean.setFilterUser("John");
        result = filterListBean.getUserList(userList);
        assertEquals(Arrays.asList(user1), result);
    }
}
