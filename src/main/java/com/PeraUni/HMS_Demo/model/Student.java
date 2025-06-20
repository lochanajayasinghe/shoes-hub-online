package com.PeraUni.HMS_Demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "surname")
    private String surname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "active")
    private boolean active;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "batch")
    private String batch;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Eligibility> eligibilities = new ArrayList<>();

    // Transient field for eligibility status (not persisted in DB)
    @Transient
    private boolean eligible;

    // Getters and Setters
    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public List<Eligibility> getEligibilities() { return eligibilities; }
    public void setEligibilities(List<Eligibility> eligibilities) {
        this.eligibilities = eligibilities;
    }

    public boolean isEligible() { return eligible; }
    public void setEligible(boolean eligible) { this.eligible = eligible; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}