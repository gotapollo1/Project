package com.example.demo.Repository;

import com.example.demo.Model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration,Integer> {
    Optional<Configuration>findById(Integer id);
    List<Configuration>findByCategoryAndSub(String category,String sub);

}
