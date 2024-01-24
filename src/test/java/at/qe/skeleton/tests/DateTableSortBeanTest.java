package at.qe.skeleton.tests;
import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.services.UserxService;
import at.qe.skeleton.internal.ui.beans.DataTableSortBean;
import at.qe.skeleton.internal.ui.beans.DateSelectionBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DataTableSortBeanTest {

    @InjectMocks
    private DataTableSortBean dataTableSortBean;

    @Mock
    private PaymentHistoryService paymentHistoryService;

    @Mock
    private UserxService userService;

    @Mock
    private DateSelectionBean dateSelectionBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        // Mock data
        int selectedYear = 2022;
        int selectedMonth = 1;
        when(dateSelectionBean.getSelectedYear()).thenReturn(selectedYear);
        when(dateSelectionBean.getSelectedMonthInt()).thenReturn(selectedMonth);

        // Mock service calls
        List<PaymentHistory> mockPaymentHistoryList = Arrays.asList(/* create mock PaymentHistory objects */);
        when(paymentHistoryService.getAllByYearAndMonth(selectedYear, selectedMonth)).thenReturn(mockPaymentHistoryList);
        when(userService.getAllUsers()).thenReturn(Arrays.asList(/* create mock Userx objects */));

        // Call the method to test
        dataTableSortBean.init();

        // Assert the results
        //assertEquals(mockPaymentHistoryList, dataTableSortBean.getPaymentHistoryList());
        // Add more assertions based on the expected behavior of the init method
    }
}

