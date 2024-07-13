package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CourseDto;
import com.app.entities.Course;
import com.app.service.CourseService;

import lombok.Delegate;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseservice;
	
	public CourseController() {
     System.out.println("in ctor of"+getClass());
	}
	
	@PostMapping
	public Course addCourse(@RequestBody Course course) {
		return courseservice.addCourseDetails(course);
	}
	@GetMapping
	public  List<Course> getAllCourse()
	{
		return courseservice.getAllDetail();
	}
	@GetMapping("/{courseid}")
	public Course getByCourseId(@PathVariable Long courseid ) {
		return courseservice.getById(courseid);
	}
	
	@PutMapping
	public Course updateCourseDetails(@RequestBody Course course)
	{
		return courseservice.updateCourseDetail(course);
	}
	
     @DeleteMapping("/{courseid}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long courseid) {
    	 
    	  String course=courseservice.deleteCourseDetail(courseid);
    	  if(course==null) {
  			return new  ResponseEntity<>("Id not found",HttpStatus.BAD_REQUEST);
  		}
    	  return  ResponseEntity.ok(course);
     }
	

}
