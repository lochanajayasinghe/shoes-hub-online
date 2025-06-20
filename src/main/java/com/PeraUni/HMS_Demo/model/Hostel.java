package com.PeraUni.HMS_Demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hostel")
public class Hostel {

    @Id
    @Column(name = "Hostel_ID")
    private String hostelId;

    @Column(name = "Hostel_Name")
    private String hostelName;

    // Getters and Setters
    public String getHostelId() {
        return hostelId;
    }

    public void setHostelId(String hostelId) {
        this.hostelId = hostelId;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {  // Changed setter as well
        this.hostelName = hostelName;
    }

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Eligibility> eligibilities = new ArrayList<>();
}
