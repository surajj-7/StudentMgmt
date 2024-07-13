package com.app.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.StudentDto;
import com.app.entities.Student;
import com.app.repository.CourseRepository;
import com.app.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentservice;
	@Autowired
	private CourseRepository courserepository;
	@Autowired
	private ModelMapper mapper;

	public StudentController() {
		System.out.println("in ctor of" + getClass());
	}

	@PostMapping
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto) {
//		Course course = courserepository.findById(studentDto.getCourseId())
//				.orElseThrow(() -> new InvalidCredentailException("invalid course id"));
//		Student student = new Student();
		Student student = studentservice.addStudentDetail(studentDto);
		if (student == null) {
			return new ResponseEntity<>("not eligible", HttpStatus.BAD_REQUEST);
		} else if (student.getCourse().getCourseId() == 0L) {
			return new ResponseEntity<>("course not found", HttpStatus.BAD_REQUEST);
		} else
			return ResponseEntity.ok(student);
	}

	@GetMapping("/{studentid}")
	public ResponseEntity<?> getBystudentId(@PathVariable Long studentid) {
		StudentDto student=studentservice.getById(studentid);
		if(student==null) {
			return new  ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(student);

	}
	
	@GetMapping("/courseName/{title}")
	public ResponseEntity<?> getByCourseName(@PathVariable String title) {
		List<StudentDto> student=studentservice.getAllStudentsByCourseName(title);
		
		if(student==null) {
			return new  ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(student);

	}
  
	
    @DeleteMapping("/{studentid}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long studentid) {
   	 
   	  String course=studentservice.deleteCourseDetail(studentid);
   	  if(course==null) {
 			return new  ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
 		}
   	  return  ResponseEntity.ok(course);
    }
	

}
