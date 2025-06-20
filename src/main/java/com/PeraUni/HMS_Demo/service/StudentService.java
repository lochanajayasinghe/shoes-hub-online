package com.PeraUni.HMS_Demo.service;

import com.PeraUni.HMS_Demo.exception.AllocationException;
import com.PeraUni.HMS_Demo.model.Room;
import com.PeraUni.HMS_Demo.model.Student;
import com.PeraUni.HMS_Demo.repository.RoomRepository;
import com.PeraUni.HMS_Demo.repository.StudentRepository;
import com.PeraUni.HMS_Demo.service.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RoomService roomService;
    private final RoomRepository roomRepository;

    public StudentService(StudentRepository studentRepository,
                          RoomService roomService,
                          RoomRepository roomRepository) {
        this.studentRepository = studentRepository;
        this.roomService = roomService;
        this.roomRepository = roomRepository;
    }

    public List<Student> getStudentsEligibleForHostel(String hostelId) {
        return studentRepository.findByEligibilitiesHostelHostelId(hostelId)
                .stream()
                .filter(Student::isActive)
                .collect(Collectors.toList());
    }

    @Transactional
    public void allocateStudentsToRoom(String[] selectedRegNos, String roomId, String hostelId) throws AllocationException {
        if (selectedRegNos == null || selectedRegNos.length == 0) {
            throw new AllocationException("No students selected");
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new AllocationException("Room not found: " + roomId));

        if (!room.getHostel().getHostelId().equals(hostelId)) {
            throw new AllocationException("Room doesn't belong to specified hostel");
        }

        for (String regNo : selectedRegNos) {
            Student student = studentRepository.findById(regNo)
                    .orElseThrow(() -> new AllocationException("Student not found: " + regNo));

            boolean isEligible = student.getEligibilities().stream()
                    .anyMatch(e -> e.getHostel().getHostelId().equals(hostelId));

            if (!isEligible) {
                throw new AllocationException("Student " + regNo + " is not eligible for hostel " + hostelId);
            }

            student.setActive(false);
            student.setRoom(room);
            studentRepository.save(student);
        }

        roomService.updateRoomAvailability(roomId, selectedRegNos.length);
    }
}