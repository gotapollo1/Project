package com.example.demo.Repository;

import com.example.demo.Model.Output;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OutputRepository extends JpaRepository<Output,Integer> {
    Optional<Output>findById(Integer id);
}
