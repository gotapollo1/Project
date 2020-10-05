package com.example.demo.Repository;

import com.example.demo.Model.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InputRepository extends JpaRepository<Input,Integer> {
    Optional<Input>findById(Integer Id);
}
