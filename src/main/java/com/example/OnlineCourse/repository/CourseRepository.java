package com.example.OnlineCourse.repository;


import com.example.OnlineCourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
