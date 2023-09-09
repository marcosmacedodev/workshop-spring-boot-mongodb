package br.com.workshop.entities.dtos;

import java.io.Serializable;
import java.util.Date;

import br.com.workshop.entities.Post;

public class PostDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date date;
	private String title;
	private String body;
	private UserDto author;
	
	public PostDto() {
		
	}

	public PostDto(Post post) {
		this.id = post.getId();
		this.date = post.getDate();
		this.title = post.getTitle();
		this.body = post.getBody();
		this.author = post.getAuthor().toUserDto();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public UserDto getAuthor() {
		return author;
	}

	public void setAuthor(UserDto author) {
		this.author = author;
	}

}
