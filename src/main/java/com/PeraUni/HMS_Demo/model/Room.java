package com.PeraUni.HMS_Demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "Room_ID")
    private String roomId;

    @ManyToOne
    @JoinColumn(name = "Hostel_ID")
    private Hostel hostel;

    @Column(name = "Room_No")
    private String roomNo;

    @Column(name = "Capacity")
    private String capacity;

    @Column(name = "available")
    private int available;  // or Integer if it can be null// "1" for available, "0" for not available

    @Column(name = "Occupancy")
    private String occupancy;

    // No-arg constructor required by JPA
    public Room() {
    }

    // Constructor with roomId only (used for references)
    public Room(String roomId) {
        this.roomId = roomId;
    }

    // Full constructor
    public Room(String roomId, Hostel hostel, String roomNo, String capacity, String available, String occupancy) {
        this.roomId = roomId;
        this.hostel = hostel;
        this.roomNo = roomNo;
        this.capacity = capacity;
        this.available = Integer.parseInt(available);
        this.occupancy = occupancy;
    }

    // Getters and Setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public Hostel getHostel() { return hostel; }
    public void setHostel(Hostel hostel) { this.hostel = hostel; }

    public String getRoomNo() { return roomNo; }
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }

    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getOccupancy() { return occupancy; }
    public void setOccupancy(String occupancy) { this.occupancy = occupancy; }
}