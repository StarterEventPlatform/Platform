package com.eventplatform.repository;

import com.eventplatform.pojo.klass.GeoPosition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeoPositionDataRepository extends MongoRepository<GeoPosition,Integer> {

}
