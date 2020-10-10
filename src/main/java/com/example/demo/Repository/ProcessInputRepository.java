package com.example.demo.Repository;

import com.example.demo.Model.Input;
import com.example.demo.Model.Process;
import com.example.demo.Model.ProcessInput;
import org.apache.tomcat.jni.Proc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessInputRepository extends JpaRepository<ProcessInput,Integer> {
    List<ProcessInput> findByProcessId(Integer processID);
    List<ProcessInput>findByProcess(Process process);
    List<ProcessInput>findByInputIdIn(List<Integer> idList);
    Optional<ProcessInput>findById(Integer Id);
}
