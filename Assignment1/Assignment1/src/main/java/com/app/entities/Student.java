package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
	//first name , last name , email , course id,score 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentid;
	@Column(length=20,name="first_name")
	private String firstName;
	@Column(length=20,name="last_name")
	private String lastName;
	@Column(length=20,name="email")
	private String email;
	@Column(name="score")
	private int score;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	
	
	
   

}
