package br.com.workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.workshop.entities.dtos.UserDto;
import br.com.workshop.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDto>> getAll() {
		List<UserDto> users = 
				userService.findAll().stream().map(u -> new UserDto(u)).toList();
		return ResponseEntity.ok().body(users);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> get(@PathVariable(value = "userId") String id) {
		UserDto userDto = 
				new UserDto(userService.findById(id));
		return ResponseEntity.ok().body(userDto);
	}
}
