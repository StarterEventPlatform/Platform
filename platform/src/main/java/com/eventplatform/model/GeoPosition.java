package com.eventplatform.model;

import java.util.Date;
import java.util.Objects;

public class GeoPosition extends Entity {

    private float latitude;
    private float longitude;

    public GeoPosition(int id, Date creationDate) {
        super(id, creationDate);
    }

    public GeoPosition(int id, Date creationDate, float latitude,float longitude) {
        super(id,creationDate);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {return latitude;}

    public void setLatitude(float latitude) {this.latitude = latitude;}

    public float getLongitude() {return longitude;}

    public void setLongitude(float longitude) {this.longitude = longitude;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoPosition)) return false;
        GeoPosition geoPosition = (GeoPosition) o;
        return Objects.equals(getCreationDate(), geoPosition.getCreationDate()) &&
                Objects.equals(getId(), geoPosition.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreationDate(), getLatitude(), getLongitude());
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "id='" + super.getId() + '\'' +
                ", latitude=" + latitude + '\''  +
                ", longitude="+ longitude + '\'' +
                ", creationDate=" + super.getCreationDate() +
                '}';
    }
}
