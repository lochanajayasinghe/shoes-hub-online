package com.PeraUni.HMS_Demo.repository;


import com.PeraUni.HMS_Demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    // In StudentRepository.java
    List<Student> findByActive(boolean active);

    @Query("SELECT DISTINCT s FROM Student s JOIN s.eligibilities e WHERE e.hostel.hostelId = :hostelId")
    List<Student> findByEligibilitiesHostelHostelId(@Param("hostelId") String hostelId);
}
