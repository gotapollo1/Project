package com.example.demo.Repository;

import com.example.demo.Model.Process;
import com.example.demo.Model.ProcessOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessOutputRepository extends JpaRepository<ProcessOutput,Integer> {
    List<ProcessOutput> findByProcessId(Integer processId);
    List<ProcessOutput>findByProcess(Process process);
}
