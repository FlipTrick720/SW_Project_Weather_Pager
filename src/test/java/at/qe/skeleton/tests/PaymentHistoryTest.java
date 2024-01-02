package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.model.UserxRole;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.Set;

@SpringBootTest
@WebAppConfiguration
public class PaymentHistoryTest {

    @Autowired
    public UserxService userxService;

    @Autowired
    public PaymentHistoryService paymentHistoryService;

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testPremiumInDb() {
        String testUser = "testUser";
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        user.setPremium(true);
        userxService.saveUser(user);

        Assertions.assertEquals(1, paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue()).toArray().length);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testPremiumPerMonthOnlyOne(int testCount) {
        String testUser = "testUser";
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        userxService.saveUser(user);

        for (int i = 0; i < testCount; i++) {
            user.setPremium(true);
            userxService.saveUser(user);
            user.setPremium(false);
            userxService.saveUser(user);
        }
        System.out.println(paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue()));
        Assertions.assertEquals(1, paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue()).toArray().length);
    }

}
