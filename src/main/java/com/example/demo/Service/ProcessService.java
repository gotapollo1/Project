package com.example.demo.Service;

import com.example.demo.Model.Process;
import com.example.demo.Repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {
    @Autowired
    ProcessRepository process_r_repository;
    public List<Process> getAll(){
        return process_r_repository.findAll();
    }
}
