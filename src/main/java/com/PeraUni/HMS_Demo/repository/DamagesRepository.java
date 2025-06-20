package com.PeraUni.HMS_Demo.repository;

import com.PeraUni.HMS_Demo.model.Damages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DamagesRepository extends JpaRepository<Damages, Long> {

}
