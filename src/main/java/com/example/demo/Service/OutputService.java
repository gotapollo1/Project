package com.example.demo.Service;

import com.example.demo.Model.Output;
import com.example.demo.Repository.OutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    private List<Output> outputList = new ArrayList<>();
    public Output addOutput (@RequestBody Output output){
        outputList.add(output);
        return outputRepository.save(output);
    }
}
