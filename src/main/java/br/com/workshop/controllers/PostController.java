package br.com.workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.workshop.controllers.utils.Utils;
import br.com.workshop.entities.dtos.PostDto;
import br.com.workshop.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{postId}", method = RequestMethod.GET)
	public ResponseEntity<PostDto> get(@PathVariable(value = "postId") String id){
		PostDto postDto = postService.findById(id).toPostDto();
		return ResponseEntity.ok().body(postDto);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<PostDto>> getByTitle(
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "ignoreCase", defaultValue = "false") String ignoreCaseStr
	){
		boolean ignoreCase = Boolean.valueOf(ignoreCaseStr);
		title = Utils.decodeParam(title);
		List<PostDto> postsDto = postService.findByTitle(title, ignoreCase).stream().map(p -> p.toPostDto()).toList();
		return ResponseEntity.ok().body(postsDto);
	}
}
