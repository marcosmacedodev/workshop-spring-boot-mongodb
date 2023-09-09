package br.com.workshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.workshop.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
