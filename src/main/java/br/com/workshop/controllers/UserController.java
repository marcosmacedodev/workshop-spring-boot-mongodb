package br.com.workshop.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.workshop.entities.User;
import br.com.workshop.entities.dtos.PostDto;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
		User entity = userDto.toUser();
		entity = userService.insert(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity.toUserDto());
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(value = "userId") String id){
		userService.remove(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable(value = "userId") String id, @RequestBody UserDto userDto){
		userService.save(id, userDto.toUser());
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{userId}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<PostDto>> getPosts(@PathVariable(value = "userId") String id) {
		User entity = userService.findById(id);
		List<PostDto> posts = entity.getPosts().stream().map(post -> post.toPostDto()).toList();
		return ResponseEntity.ok().body(posts);
	}
}
