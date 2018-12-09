package com.eventplatform.repository;

import com.eventplatform.pojo.klass.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends MongoRepository<User, Integer> {
}