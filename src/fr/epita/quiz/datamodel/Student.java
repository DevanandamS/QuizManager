package fr.epita.quiz.datamodel;


public class Student {

	private int student_id;
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStudId() {
		return student_id;
	}

	public void setStudId(int id) {
		this.student_id = id;
	}
}
