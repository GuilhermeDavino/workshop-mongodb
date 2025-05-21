package com.spring.workshop_mongo.respositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.workshop_mongo.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String title);

	List<Post> findByTitleContainingIgnoreCase(String title);
	
	@Query("{$and: [{'title': { $regex: ?0, $options: 'i'}}, {'moment': {$gte: ?1}}, {'moment': {$lte: ?2} } ] }")
	List<Post> fullSearch(String title, Instant start, Instant end);
}
