package at.qe.skeleton.internal.ui.beans;


import at.qe.skeleton.internal.model.PaymentHistory;
import at.qe.skeleton.internal.repositories.PaymentHistoryRepository;
import at.qe.skeleton.internal.services.PaymentHistoryService;
import at.qe.skeleton.internal.ui.controllers.PaymentHistoryController;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Bean to give a dropdown menu of Years and Months and save the input until next input.
 *
 */


@ManagedBean
public class DateSelectionBean implements Serializable {

    private List<Integer> years;
    private List<Month> months;
    private Integer selectedYear;
    private Month selectedMonth;


    public DateSelectionBean() {
        years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 10; i--) {
            years.add(i);
        }


        months = Arrays.asList(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY,
                Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
    }

    public List<Integer> getYears() {
        return years;
    }

    public Integer getSelectedYear() {
        return selectedYear;
    }

    public void setSelectedYear(Integer selectedYear) {
        this.selectedYear = selectedYear;
    }

    public List<Month> getMonths() {
        return months;
    }

    public Month getSelectedMonth() {
        return selectedMonth;
    }

    public void setSelectedMonth(Month selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public String printDates() {
         // System.out.println("Selected Year: " + selectedYear);
        //System.out.println("Selected Month: " + selectedMonth);

        return "/manager/user_list.xhtml";
    }
}