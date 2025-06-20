package com.PeraUni.HMS_Demo.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "eligibility")
public class Eligibility implements Serializable {

    @EmbeddedId
    private EligibilityId id;

    @ManyToOne
    @MapsId("regNo")
    @JoinColumn(name = "reg_no", referencedColumnName = "reg_no")
    private Student student;

    @ManyToOne
    @MapsId("hostelId")
    @JoinColumn(name = "hostel_id", referencedColumnName = "hostel_id")
    private Hostel hostel;

    @Column(name = "academic_year")
    private String academicYear;

    // Constructors, getters, and setters
    public Eligibility() {}

    public Eligibility(Student student, Hostel hostel, String academicYear) {
        this.id = new EligibilityId(student.getRegNo(), hostel.getHostelId());
        this.student = student;
        this.hostel = hostel;
        this.academicYear = academicYear;
    }

    // Getters and setters...

    public EligibilityId getId() {
        return id;
    }

    public void setId(EligibilityId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}