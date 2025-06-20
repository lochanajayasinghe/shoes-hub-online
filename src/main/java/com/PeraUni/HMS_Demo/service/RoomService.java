package com.PeraUni.HMS_Demo.service;

import com.PeraUni.HMS_Demo.exception.AllocationException;
import com.PeraUni.HMS_Demo.model.Room;
import com.PeraUni.HMS_Demo.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public void updateRoomAvailability(String roomId, int allocatedStudents) throws AllocationException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new AllocationException("Room not found with ID: " + roomId));

        if (allocatedStudents <= 0) {
            throw new AllocationException("Number of allocated students must be positive");
        }

        if (allocatedStudents > room.getAvailable()) {
            throw new AllocationException("Cannot allocate more students than available spaces");
        }

        room.setAvailable(room.getAvailable() - allocatedStudents);
        roomRepository.save(room);
    }
}