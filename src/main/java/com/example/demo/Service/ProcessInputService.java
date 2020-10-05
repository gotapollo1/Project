package com.example.demo.Service;

import com.example.demo.Dto.ProcessInputDto;
import com.example.demo.Dto.UserProcessDto;
import com.example.demo.Model.Process;
import com.example.demo.Model.ProcessInput;
import com.example.demo.Repository.ProcessInputRepository;
import com.example.demo.Repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessInputService {
    @Autowired
    private ProcessInputRepository processInputRepository;
    @Autowired
    private  ProcessRepository processRepository;



    public ProcessInputDto getByProcessID(Integer processID){

        ProcessInputDto processInputDto = new ProcessInputDto();

        Optional<Process> processDetail = processRepository.findById(processID);
        if(!processDetail.isPresent()){
            return null;
        }

        List<ProcessInput> data = processInputRepository.findByProcess(processDetail.get());

        List listInput = new ArrayList();
        for(ProcessInput Data : data){
            listInput.add(Data.getInput());
            processInputDto.setName(Data.getProcess().getName());
            processInputDto.setDescription(Data.getProcess().getDescription());
        }
            processInputDto.setInputs(listInput);
            return processInputDto;
    }

}
