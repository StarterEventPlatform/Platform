package com.eventplatform.factory;

import com.eventplatform.pojo.klass.GeoPosition;
import com.eventplatform.repository.GeoPositionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Scope(value = "singleton")
@Component
public class GeoPositionFactory {

    @Autowired
    private GeoPositionDataRepository geoPositionDataRepository;

    public GeoPosition createGeoPosition(float latitude, float longitude) {
        GeoPosition geoPosition = new GeoPosition();
        geoPosition.setId((int) geoPositionDataRepository.count());
        geoPosition.setCreationDate(new Date(System.currentTimeMillis()));
        geoPosition.setLatitude(latitude);
        geoPosition.setLongitude(longitude);
        geoPositionDataRepository.save(geoPosition);
        return geoPosition;
    }
}
