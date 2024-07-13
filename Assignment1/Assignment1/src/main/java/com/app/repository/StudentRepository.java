package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Course;
import com.app.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findAllByCourse(Course course);

}
