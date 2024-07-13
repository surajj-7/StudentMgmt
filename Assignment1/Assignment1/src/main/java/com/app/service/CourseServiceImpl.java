package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Course;
import com.app.exception.InvalidCredentailException;
import com.app.repository.CourseRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courserepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Course addCourseDetails(Course cousreDetails) {

		return courserepository.save(cousreDetails);
	}

	@Override
	public List<Course> getAllDetail() {
		// TODO Auto-generated method stub
//		List<Course> courseList = courserepository.findAll();
//		List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
//for(Course item: courseList) {
//	
//	courseDtoList.add(mapper.map(item, CourseDto.class));
//}

//		return courseList.stream()
//			    .map(item -> mapper.map(item, CourseDto.class))
//			    .collect(Collectors.toList());
		return courserepository.findAll();
	}

	@Override
	public Course updateCourseDetail(Course course) {
		// TODO Auto-generated method stub
		return courserepository.save(course);
	}

	@Override
	public Course getById(Long courseid) {
		// TODO Auto-generated method stub
		Optional<Course> optional = courserepository.findById(courseid);
		return optional.orElseThrow(() -> new InvalidCredentailException("invalid course id"));
	}

	@Override
	public String deleteCourseDetail(Long courseid) {
		if(courserepository.existsById(courseid)) {
			courserepository.deleteById(courseid);
			return "Course Deleted ";
		}
		return "Invalid id";
	}

}
