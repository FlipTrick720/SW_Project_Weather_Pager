package at.qe.skeleton.tests;

import at.qe.skeleton.internal.model.PremiumHistory;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.model.UserxRole;
import at.qe.skeleton.internal.repositories.PremiumHistoryRepository;
import at.qe.skeleton.internal.services.PremiumHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootTest
@WebAppConfiguration
public class PayrollTest {


    @Autowired
    private PremiumHistoryService premiumHistoryService;

    @Autowired
    private UserxService userxService;

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testPremStatusAendernDbEintrag() {
        String testUser = "testUser";
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        user.setPremium(false);
        userxService.saveUser(user);

        List<PremiumHistory> firstResult = premiumHistoryService.getPremiumChangedByName(testUser);
        Assertions.assertNull(firstResult.isEmpty() ? null : firstResult);

        premiumHistoryService.savePremiumHistory(user, true);

        List<PremiumHistory> secondResult = premiumHistoryService.getPremiumChangedByName(testUser);
        Assertions.assertNotNull(secondResult.isEmpty() ? null : secondResult);
    }

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testPremStatusAendernChangeDate() {
        String testUser = "testUser";
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        user.setPremium(false);
        userxService.saveUser(user);

        premiumHistoryService.savePremiumHistory(user, true);

        List<PremiumHistory> firstResult = premiumHistoryService.getPremiumChangedByName(testUser);
        Assertions.assertNotNull(firstResult.isEmpty() ? null : firstResult);

        Duration duration = Duration.between(firstResult.get(0).getChangeDate(), LocalDateTime.now());
        System.out.println(duration);
        Assertions.assertTrue(duration.toSeconds() < 0.1);
    }

    @Test
    @WithMockUser(username = "manager", authorities = {"MANAGER"})
    @DirtiesContext
    public void testPremStatusAendernNewStaus() {
        String testUser = "testUser";
        Boolean newStatus = false;
        Userx user = new Userx();
        user.setUsername(testUser);
        user.setPassword("passwd");
        user.setRoles(Set.of(UserxRole.USER));
        user.setPremium(newStatus);
        userxService.saveUser(user);

        premiumHistoryService.savePremiumHistory(user, !newStatus);
        premiumHistoryService.savePremiumHistory(user, newStatus);

        List<PremiumHistory> firstResult = premiumHistoryService.getPremiumChangedByName(testUser);
        Assertions.assertNotNull(firstResult.isEmpty() ? null : firstResult);
        Assertions.assertTrue(firstResult.get(0).getNewPremiumStatus());
        Assertions.assertFalse(firstResult.get(1).getNewPremiumStatus());
    }


}
