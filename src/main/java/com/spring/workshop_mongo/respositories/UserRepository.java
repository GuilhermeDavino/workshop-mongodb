package com.spring.workshop_mongo.respositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.workshop_mongo.models.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
