package br.com.workshop.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.workshop.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContaining(String s);
	List<Post> findByTitleContainingIgnoreCase(String s);
	
	@Query("{'title': {$regex: ?0, $options: 'i'}}")
	List<Post> searchTitle(String s);
}
