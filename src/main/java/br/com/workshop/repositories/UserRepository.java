package br.com.workshop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.workshop.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

}
