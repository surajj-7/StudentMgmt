package com.app.service;

import java.util.List;

import com.app.dto.StudentDto;
import com.app.entities.Student;

public interface StudentService {
	
	Student addStudentDetail(StudentDto studentDto);
	
	StudentDto getById(Long studentid);
	
	List<StudentDto> getAllStudentsByCourseName(String courseName);

	String deleteCourseDetail(Long studentid);

}
