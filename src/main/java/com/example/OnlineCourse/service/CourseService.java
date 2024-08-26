package com.example.OnlineCourse.service;

import com.example.OnlineCourse.entity.Course;
import com.example.OnlineCourse.exception.CourseFullException;
import com.example.OnlineCourse.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        logger.info("Creating course: {}", course.getCourseName());
        if (course.getStartDate().before(new Date())) {
            logger.error("Failed to create course: Start date cannot be in the past");
            throw new IllegalArgumentException("Start date cannot be in the past");
        }
        Course createdCourse = courseRepository.save(course);
        logger.info("Course created successfully: {}", createdCourse.getId());
        return createdCourse;
    }

    public List<Course> getAllCourses() {
        logger.debug("Fetching all courses");
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        logger.debug("Fetching course with ID: {}", id);
        return courseRepository.findById(id);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        logger.info("Updating course with ID: {}", id);
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            logger.error("Course not found with ID: {}", id);
            return new IllegalArgumentException("Course not found");
        });
        course.setCourseName(courseDetails.getCourseName());
        course.setDescription(courseDetails.getDescription());
        course.setStartDate(courseDetails.getStartDate());
        course.setEndDate(courseDetails.getEndDate());
        course.setMaxStudents(courseDetails.getMaxStudents());
        Course updatedCourse = courseRepository.save(course);
        logger.info("Course updated successfully: {}", updatedCourse.getId());
        return updatedCourse;
    }

    public void deleteCourse(Long id) {
        logger.info("Deleting course with ID: {}", id);
        courseRepository.deleteById(id);
        logger.info("Course deleted successfully: {}", id);
    }

    public void enrollStudent(Long courseId) {
        logger.info("Enrolling student in course with ID: {}", courseId);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> {
            logger.error("Course not found with ID: {}", courseId);
            return new IllegalArgumentException("Course not found");
        });
        if (course.getEnrolledStudents() >= course.getMaxStudents()) {
            logger.error("Failed to enroll student: Course is full");
            throw new CourseFullException("The course is full and cannot accept more students.");
        }
        course.setEnrolledStudents(course.getEnrolledStudents() + 1);
        courseRepository.save(course);
        logger.info("Student enrolled successfully in course with ID: {}", courseId);
    }
}
