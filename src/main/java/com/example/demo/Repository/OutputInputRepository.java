package com.example.demo.Repository;

import com.example.demo.Model.Output;
import com.example.demo.Model.OutputInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutputInputRepository extends JpaRepository<OutputInput,Integer> {
    List<OutputInput>findByOutput(Output output);
}
