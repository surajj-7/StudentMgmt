package com.app.service;

import java.util.List;

import com.app.dto.CourseDto;
import com.app.entities.Course;

public interface CourseService {
	Course addCourseDetails(Course cousreDetails);
	
	List<Course> getAllDetail();
	
	Course getById(Long courseid);
 	
	Course updateCourseDetail(Course course);

	String deleteCourseDetail(Long courseid);
    
	
}
