package com.PeraUni.HMS_Demo.repository;

import com.PeraUni.HMS_Demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    // Custom query methods if needed
}