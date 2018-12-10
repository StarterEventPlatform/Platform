package com.eventplatform.repository;

import com.eventplatform.domain.model.GeoPosition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoPositionDataRepository extends MongoRepository<GeoPosition, Integer> {

}
