package com.eventplatform.factory;

import com.eventplatform.model.GeoPosition;

import java.util.Date;
import java.util.Map;

public class GeoPosistionFactory {

    public static GeoPosition createGeoPosition(GeoPosition geoPosition) {
        return new GeoPosition(geoPosition.getId(), geoPosition.getCreationDate(), geoPosition.getLatitude(), geoPosition.getLongitude());
    }

    public static GeoPosition createGeoPosition(int id, float latitude, float longitude) {
        return new GeoPosition(id, new Date(System.currentTimeMillis()), latitude, longitude);
    }

    public static GeoPosition createGeoPosition(Map<String, Object> params) {
        return new GeoPosition(Integer.parseInt(params.get("id").toString()), new Date(Long.parseLong(params.get("creationDate").toString())),
                Float.parseFloat(params.get("latitude").toString()), Float.parseFloat(params.get("longitude").toString()));
    }
}
