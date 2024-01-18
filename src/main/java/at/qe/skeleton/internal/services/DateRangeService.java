package at.qe.skeleton.internal.services;


import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DateRangeService {

    public boolean isDateRangeValid(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return false;
        }
        if (startDate.isAfter(endDate)) {
            return false;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) <= 14;
    }

    public static LocalDate calculateMidpointDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        int daysBetween = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        return startDate.plusDays(daysBetween / 2);
    }
}
