package br.com.workshop.entities.dtos;

import java.io.Serializable;
import java.util.Date;

public class CommentDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String text;
	private Date date;
	private UserDto author;
	
	public CommentDto() {
	}

	public CommentDto(String text, Date date, UserDto author) {
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserDto getAuthor() {
		return author;
	}

	public void setAuthor(UserDto author) {
		this.author = author;
	}
}
