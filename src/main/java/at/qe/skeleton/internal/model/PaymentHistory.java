package at.qe.skeleton.internal.model;

import jakarta.persistence.*;

import java.time.Month;
import java.util.Objects;

/**
 * Entity Representing the Payment History. The Payment History saves the monthly executed payment roll.
*/

@Entity
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Userx user;

    @Column(nullable = false)
    private  boolean paymentStatus;

    private int chargedDays;

    @Column(nullable = false)
    private int paymentYear;

    @Column(nullable = false)
    private Month paymentMonth;

    @Column(nullable = true)
    private int daysTillEndMonth;

    public int getDaysTillEndMonth() {
        return daysTillEndMonth;
    }

    public void setDaysTillEndMonth(int daysTillEndMonth) {
        this.daysTillEndMonth = daysTillEndMonth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Userx getUser() {
        return user;
    }

    public void setUser(Userx user) {
        this.user = user;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus  = paymentStatus;
    }

    public int getChargedDays() {
        return chargedDays;
    }

    public void setChargedDays(int chargedDays) {
        this.chargedDays = chargedDays;
    }

    public int getYear() {
        return paymentYear;
    }

    public void setYear(int year) {
        this.paymentYear = year;
    }

    public Month getMonth() {
        return paymentMonth;
    }

    public void setMonth(Month month) {
        this.paymentMonth = month;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentHistory that = (PaymentHistory) o;
        return paymentYear == that.paymentYear && Objects.equals(user, that.user) && paymentMonth == that.paymentMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, paymentYear, paymentMonth);
    }
}
