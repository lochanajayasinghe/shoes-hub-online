package com.PeraUni.HMS_Demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "allocation")
public class Allocation {

    @Id
    @Column(name = "allocation_id")
    private String allocationId;

    @Column(name = "academic_year")
    private String academicYear;


    @Column(name = "active")
    private String active;

    @Column(name = "in_date")
    private String inDate;

    @Column(name = "out_date")
    private String outDate;

    @Column(name = "note")
    private String note;

    @Column(name = "occupancy")
    private String occupancy;

    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "available")
    private String available;

    // Getters and Setters
    public String getAllocationId() { return allocationId; }
    public void setAllocationId(String allocationId) { this.allocationId = allocationId; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getActive() { return active; }
    public void setActive(String active) { this.active = active; }

    public String getInDate() { return inDate; }
    public void setInDate(String inDate) { this.inDate = inDate; }

    public String getOutDate() { return outDate; }
    public void setOutDate(String outDate) { this.outDate = outDate; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public String getOccupancy() { return occupancy; }
    public void setOccupancy(String occupancy) { this.occupancy = occupancy; }

    public String getRegNo() { return regNo; }
    public void setRegNo(String regNo) { this.regNo = regNo; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getAvailable() { return available; }
    public void setAvailable(String available) { this.available = available; }
}


