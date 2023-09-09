package br.com.workshop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workshop.entities.Post;
import br.com.workshop.repositories.PostRepository;
import br.com.workshop.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> optional = postRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Post id: " + id + ", não encontrado."));
	}
}
