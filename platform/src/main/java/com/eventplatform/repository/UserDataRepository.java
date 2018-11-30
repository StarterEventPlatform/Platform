package com.eventplatform.repository;

import com.eventplatform.pojo.klass.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends CrudRepository<User, Integer> {
}