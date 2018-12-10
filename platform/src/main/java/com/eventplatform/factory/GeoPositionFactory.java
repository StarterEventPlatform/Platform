package com.eventplatform.factory;

import com.eventplatform.domain.MongoMappingConstants;
import com.eventplatform.domain.model.GeoPosition;
import com.eventplatform.repository.GeoPositionDataRepository;
import com.eventplatform.repository.SequenceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Scope(value = "singleton")
@Component
public class GeoPositionFactory {

    @Autowired
    private GeoPositionDataRepository geoPositionDataRepository;
    @Autowired
    private SequenceDao sequenceDao;

    public GeoPosition createGeoPosition(float latitude, float longitude) {
        GeoPosition geoPosition = new GeoPosition();
        geoPosition.setId(sequenceDao.getNextSequenceId(MongoMappingConstants.COLLECTION_NAME_GEOPOSITION));
        geoPosition.setCreationDate(new Date(System.currentTimeMillis()));
        geoPosition.setLatitude(latitude);
        geoPosition.setLongitude(longitude);
        geoPositionDataRepository.save(geoPosition);
        return geoPosition;
    }
}
