package com.PeraUni.HMS_Demo.service;


import com.PeraUni.HMS_Demo.model.Hostel;
import com.PeraUni.HMS_Demo.repository.MainInterfaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainInterfaceService {

    private final MainInterfaceRepository mainInterfaceRepository;

    @Autowired
    public MainInterfaceService(MainInterfaceRepository mainInterfaceRepository) {
        this.mainInterfaceRepository = mainInterfaceRepository;
    }

    public List<Hostel> getAllHostels() {
        return (List<Hostel>) mainInterfaceRepository.findAllByOrderByHostelIdAsc();
    }

    public Optional<Hostel> findById(Long hostelId) {
        return mainInterfaceRepository.findById(hostelId);
    }

    public Hostel save(Hostel hostel) {
        return mainInterfaceRepository.save(hostel);
    }

    public void deleteById(Long hostelId) {
        mainInterfaceRepository.deleteById(hostelId);
    }

}
