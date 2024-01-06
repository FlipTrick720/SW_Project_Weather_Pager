package at.qe.skeleton.internal.ui.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Scope("request") // Correct scope for Spring Web Flow
public class DateBean {

    private Date startDate;
    private Date endDate;
    private Date[] dateRange = new Date[2]; // Array to hold start and end dates

    // Getters and Setters for dateRange
    public Date[] getDateRange() {
        return dateRange;
    }

    public void setDateRange(Date[] dateRange) {
        this.dateRange = dateRange;
        if (dateRange != null && dateRange.length == 2) {
            this.startDate = dateRange[0];
            this.endDate = dateRange[1];
        }
    }

    // Getters and Setters
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // InitBinder should be in a Controller class, not in this bean
    // Consider moving this to the appropriate @Controller class
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // Method to validate the dates
    private boolean validateDates() {
        if (startDate == null || endDate == null) {
            return false;
        }

        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);

        return diffInDays <= 14;
    }

    // Method to check if the start date is valid
    private boolean isStartDateValid() {
        Date currentDate = new Date();
        long diffInMillies = Math.abs(currentDate.getTime() - startDate.getTime());
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);

        return diffInDays >= 0 && diffInDays <= 365;
    }

    // Method to submit dates

    public String submitDates() {
        if (dateRange != null && dateRange.length == 2) {
            this.startDate = dateRange[0];
            this.endDate = dateRange[1];

            if (isStartDateValid() && validateDates()) {
                return "success";
            }
        }
        return "failure";
    }
}
