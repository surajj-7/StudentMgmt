package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.StudentDto;
import com.app.entities.Course;
import com.app.entities.Student;
import com.app.exception.InvalidCredentailException;
import com.app.repository.CourseRepository;
import com.app.repository.StudentRepository;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentrepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModelMapper mapper;

	public Student addStudentDetail(StudentDto studentDto) {
		Student student = new Student();
		Course course = new Course();
		if (courseRepository.existsById(studentDto.getCourseId())) {
			course = courseRepository.findById(studentDto.getCourseId())
					.orElseThrow(() -> new InvalidCredentailException("Course not found"));

			if (studentDto.getScore() < course.getMinScore()) {
				return null;
			} else {
				student = mapper.map(studentDto, Student.class);
				student.setCourse(course);
				return studentrepository.save(student);
			}
		} else {
			course.setCourseId(0L);
			student.setCourse(course);
			return student;
		}

	}

	@Override
	public StudentDto getById(Long studentid) {
		// TODO Auto-generated method stub
		if (studentrepository.existsById(studentid)) {
			Student student = studentrepository.findById(studentid)
					.orElseThrow(() -> new InvalidCredentailException("invalid student id"));
			StudentDto studentDto = mapper.map(student, StudentDto.class);
			if (student.getCourse() != null) {
				studentDto.setCourseId(student.getCourse().getCourseId()); // null pointer exception
			}
			return studentDto;
		} else
			return null;
	}

	@Override
	public List<StudentDto> getAllStudentsByCourseName(String courseName) {
		Course course = courseRepository.findByTitle(courseName)
				.orElseThrow(() -> new InvalidCredentailException("Invalid title"));
		List<Student> studentList = studentrepository.findAllByCourse(course);
		List<StudentDto> studentDtoList = new ArrayList<StudentDto>();
		for (Student item : studentList) {
			StudentDto studentDto = mapper.map(item, StudentDto.class);
			studentDto.setCourseId(item.getCourse().getCourseId());
			studentDtoList.add(studentDto);
		}
		return studentDtoList;
	}

	@Override
	public String deleteCourseDetail(Long studentid) {
		if(studentrepository.existsById(studentid)) {
			studentrepository.deleteById(studentid);
			return "Course Deleted ";
		}
		return "Invalid id";
	}
}
