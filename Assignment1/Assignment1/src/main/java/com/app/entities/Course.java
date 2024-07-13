package com.app.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="course")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;
	@Column(length=20,name="title")
	private String title;
	@Column(name="startdate")
	private LocalDate startDate;
//	@JsonIgnore
	@Column(name="enddate")
	private LocalDate endDate;
	@Column(name="fees")
	private double fees;
	@Column(name="minscore")
	private int minScore;
//	@OneToMany(mappedBy = "course", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//	private Set<Student> students = new HashSet<>();
	
	public Course(Long courseId, String title, LocalDate startDate, LocalDate endDate, double fees, int minScore) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fees = fees;
		this.minScore = minScore;
	}

	
}
