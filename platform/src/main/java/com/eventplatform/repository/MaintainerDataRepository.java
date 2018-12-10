package com.eventplatform.repository;

import com.eventplatform.domain.model.Maintainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintainerDataRepository extends MongoRepository<Maintainer, Integer> {
}
