package com.example.OnlineCourse.repository;



import com.example.OnlineCourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

