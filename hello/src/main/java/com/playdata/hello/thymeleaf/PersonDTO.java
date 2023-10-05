package com.playdata.hello.thymeleaf;

public class PersonDTO {
	private String id;
	private String pass;
	private String name;
	private int score;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", pass=" + pass + ", name=" + name + ", score=" + score + "]";
	}
	public PersonDTO(String id, String pass, String name, int score) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PersonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
