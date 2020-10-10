package com.example.demo.Repository;

import com.example.demo.Model.Agency;
import com.example.demo.Model.AgencyProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgencyProcessRepository extends JpaRepository<AgencyProcess,Integer> {
    List<AgencyProcess>findByAgency(Agency agency);
    List<AgencyProcess>findByAgencyId(Integer Id);
}
