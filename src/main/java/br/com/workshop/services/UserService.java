package br.com.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workshop.entities.User;
import br.com.workshop.repositories.UserRepository;
import br.com.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> optional = userRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto com id: " + id + ", não encontrado!"));
	}

}
