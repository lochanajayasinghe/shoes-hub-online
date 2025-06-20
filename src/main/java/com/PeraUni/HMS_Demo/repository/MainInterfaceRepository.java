package com.PeraUni.HMS_Demo.repository;

import com.PeraUni.HMS_Demo.model.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MainInterfaceRepository extends JpaRepository<Hostel, Long> {

    // Fetch all hostels ordered by Hostel_ID in ascending order
    List<Hostel> findAllByOrderByHostelIdAsc();
}

