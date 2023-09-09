package br.com.workshop.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.workshop.entities.Post;
import br.com.workshop.entities.User;
import br.com.workshop.entities.dtos.CommentDto;
import br.com.workshop.repositories.PostRepository;
import br.com.workshop.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		// TODO Auto-generated method stub
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", maria);
		
		CommentDto c1 = new CommentDto("Boa viagem mano!", sdf.parse("21/03/2018"), alex.toUserDto());
		CommentDto c2 = new CommentDto("Aproveite", sdf.parse("22/03/2018"), bob.toUserDto());
		CommentDto c3 = new CommentDto("Tenha um ótimo dia.", sdf.parse("23/03/2018"), alex.toUserDto());
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().add(c3);
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
