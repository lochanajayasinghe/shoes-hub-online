package com.PeraUni.HMS_Demo.service;

import com.PeraUni.HMS_Demo.model.Damages;
import com.PeraUni.HMS_Demo.repository.DamagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DamagesService {
    private final DamagesRepository damagesRepository;

    public DamagesService(DamagesRepository damagesRepository) {
        this.damagesRepository = damagesRepository;
    }

    public List<Damages> getAllDamages() {
        return damagesRepository.findAll();
    }

    public Optional<Damages> findById(Long damage_Id) {

        return damagesRepository.findById(damage_Id);
    }

    public void save(Damages damages) {

        damagesRepository.save(damages);
    }

    public void deleteById(Long damage_Id) {
        damagesRepository.deleteById(damage_Id);
    }

}
