package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.model.PaymentStatus;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.model.UserxRole;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import at.qe.skeleton.internal.ui.controllers.PremiumStatusListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@SpringBootTest
@WebAppConfiguration
public class PaymentHistoryTest {

    @Autowired
    public UserxService userxService;

    @Autowired
    public PaymentHistoryService paymentHistoryService;

    @Autowired
    public PremiumStatusListener premiumStatusListener;

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
    @ValueSource(ints = {1, 2, 50, 100})
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
        Assertions.assertEquals(1, paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue()).toArray().length);
    }

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testPremiumStatusUpdate() {
        String testUser = "testUser";
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        userxService.saveUser(user);

        paymentHistoryService.updatePaymentStatus(user, PaymentStatus.PAYED, 12);

        List<PaymentHistory> paymentList = paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue());
        Assertions.assertEquals(user.getUsername(), paymentList.get(0).getUser().getUsername());
        Assertions.assertEquals(PaymentStatus.PAYED, paymentList.get(0).getPaymentStatus());
        Assertions.assertEquals(12, paymentList.get(0).getChargedDays());
        Duration duration = Duration.between(paymentList.get(0).getChangeDate(), LocalDateTime.now());
        Assertions.assertTrue(duration.toSeconds() < 0.1);
    }

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testCashUpFunction() {
        String testUser = "testUser";
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        user.setPremium(true);
        userxService.saveUser(user);

        premiumStatusListener.cashUpTillEndCurrentMonth(user);

        List<PaymentHistory> paymentList = paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue());

        // Calculating the last second of the month, must be changed if billing is in other dimensions
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastDayOfMonth = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getMonth().length(now.toLocalDate().isLeapYear()), 23, 59, 59);
        Duration duration = Duration.between(now, lastDayOfMonth);

        long totaldifferenz = duration.toSeconds() - paymentList.get(0).getChargedDays();
        Assertions.assertTrue(totaldifferenz <= 1, "totaldifferenz should be less than 1 second");
    }

    /*@Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testUserCascadeDeletion() {
        String testUser = "testUser";
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        user.setPremium(true);
        userxService.saveUser(user);

        Assertions.assertEquals(1, paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue()).toArray().length);

        userxService.deleteUser(user);

        Assertions.assertEquals(0, paymentHistoryService.getAllByYearAndMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().getValue()).toArray().length);
    }*/
}
