package at.qe.skeleton.internal.model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class FavLocation {
    @Id
    @Column(length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Userx user;

    public Userx getUser() {
        return user;
    }

    public void setUser(Userx user) {
        this.user = user;
    }

    private double longitude;
    private double latitude;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavLocation location = (FavLocation) o;
        return Double.compare(location.longitude, longitude) == 0 && Double.compare(location.latitude, latitude) == 0 && name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, longitude, latitude);
    }

    @Override
    public String toString() {
        return "at.qe.skeleton.model.Location{" + "name='" + name + '\'' + '}';
    }

}
