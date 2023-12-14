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

    @Column(nullable = false)
    private int chargedDays;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private Month month;



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
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentHistory that = (PaymentHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
