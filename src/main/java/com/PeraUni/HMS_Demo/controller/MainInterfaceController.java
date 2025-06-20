package com.PeraUni.HMS_Demo.controller;

import com.PeraUni.HMS_Demo.exception.AllocationException;
import com.PeraUni.HMS_Demo.model.Room;
import com.PeraUni.HMS_Demo.model.Student;
import com.PeraUni.HMS_Demo.repository.RoomRepository;
import com.PeraUni.HMS_Demo.service.MainInterfaceService;
import com.PeraUni.HMS_Demo.service.RoomService;
import com.PeraUni.HMS_Demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainInterfaceController {

    private final MainInterfaceService mainInterfaceService;
    private final RoomService roomService;
    private final StudentService studentService;
    private final RoomRepository roomRepository;

    public MainInterfaceController(MainInterfaceService mainInterfaceService,
                                   RoomService roomService,
                                   StudentService studentService,
                                   RoomRepository roomRepository) {
        this.mainInterfaceService = mainInterfaceService;
        this.roomService = roomService;
        this.studentService = studentService;
        this.roomRepository = roomRepository;
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        return "index"; // This will serve the landing page
    }

    @GetMapping("/")
    public String showHostelList(Model model) {
        try {
            model.addAttribute("hostelList", mainInterfaceService.getAllHostels());
            return "hostel_list";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading hostels: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/hostel/rooms")
    public String showRoomsInHostel(@RequestParam("hostelId") String hostelId, Model model) {
        try {
            List<Room> filteredRooms = roomService.getAllRooms().stream()
                    .filter(room -> room.getHostel() != null && hostelId.equals(room.getHostel().getHostelId()))
                    .filter(room -> room.getAvailable() > 0)
                    .collect(Collectors.toList());

            model.addAttribute("roomList", filteredRooms);
            model.addAttribute("hostelId", hostelId);
            return "room_list";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading rooms: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/students")
    public String showStudentList(@RequestParam("hostelId") String hostelId,
                                  @RequestParam("roomId") String roomId,
                                  Model model) {
        try {
            List<Student> students = studentService.getStudentsEligibleForHostel(hostelId);
            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new AllocationException("Room not found: " + roomId));

            if (room.getAvailable() <= 0) {
                throw new AllocationException("This room has no available space");
            }

            model.addAttribute("students", students);
            model.addAttribute("hostelId", hostelId);
            model.addAttribute("roomId", roomId);
            model.addAttribute("roomAvailable", room.getAvailable());
            return "student_list";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading students: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/allocate-students")
    @Transactional
    public String allocateStudents(
            @RequestParam("hostelId") String hostelId,
            @RequestParam("roomId") String roomId,
            @RequestParam(value = "selectedRegNo", required = false) String[] selectedRegNos,
            RedirectAttributes redirectAttributes) {

        try {
            if (selectedRegNos == null || selectedRegNos.length == 0) {
                throw new AllocationException("Please select at least one student to allocate");
            }

            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new AllocationException("Room not found: " + roomId));

            int availableSpaces = room.getAvailable();
            int studentsToAllocate = selectedRegNos.length;

            if (studentsToAllocate > availableSpaces) {
                throw new AllocationException(String.format(
                        "Cannot allocate %d students. Only %d %s available.",
                        studentsToAllocate,
                        availableSpaces,
                        availableSpaces == 1 ? "space is" : "spaces are"
                ));
            }

            // Allocate students
            studentService.allocateStudentsToRoom(selectedRegNos, roomId, hostelId);

            // Update and save room availability
            int newAvailability = availableSpaces - studentsToAllocate;
            room.setAvailable(newAvailability);
            roomRepository.save(room);

            redirectAttributes.addFlashAttribute("success", String.format(
                    "Successfully allocated %d %s. %d %s remaining in room %s.",
                    studentsToAllocate,
                    studentsToAllocate == 1 ? "student" : "students",
                    newAvailability,
                    newAvailability == 1 ? "space is" : "spaces are",
                    roomId
            ));

            return "redirect:/hostel/rooms?hostelId=" + hostelId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                    "Allocation failed: " + e.getMessage());
            return "redirect:/students?hostelId=" + hostelId + "&roomId=" + roomId;
        }
    }
}