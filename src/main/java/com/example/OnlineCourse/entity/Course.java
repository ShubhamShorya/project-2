package com.example.OnlineCourse.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.*;
@Data

@NoArgsConstructor

@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    private String description;
    private String instructor;
    private Date startDate;
    private Date endDate;
    private int maxStudents;
    private int enrolledStudents;

    public Course(Long id, String courseName, String description, String instructor, Date startDate, Date endDate, int maxStudents, int enrolledStudents) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.instructor = instructor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxStudents = maxStudents;
        this.enrolledStudents = enrolledStudents;
    }
}

