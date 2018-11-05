package com.eventplatform.factory;

import com.eventplatform.model.GeoPosition;

import java.util.Date;

public class GeoPosistionFactory {

    public static GeoPosition createGeoPosition(GeoPosition geoPosition) {
        return new GeoPosition(geoPosition.getId(), geoPosition.getCreationDate(), geoPosition.getLatitude(), geoPosition.getLongitude());
    }

    public static GeoPosition createGeoPosition(int id, float latitude, float longitude) {
        return new GeoPosition(id, new Date(System.currentTimeMillis()), latitude, longitude);
    }
}
