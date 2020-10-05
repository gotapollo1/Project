package com.example.demo.Repository;

import com.example.demo.Model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends JpaRepository<Agency,Integer> {
    Optional<Agency>findById(Integer Id);
}
