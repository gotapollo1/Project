package com.example.demo.Service;

import com.example.demo.Dto.ProcessOutputDto;
import com.example.demo.Model.Process;
import com.example.demo.Model.ProcessOutput;
import com.example.demo.Repository.ProcessOutputRepository;
import com.example.demo.Repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessOutputService {
    @Autowired
    private ProcessOutputRepository processOutputRepository;
    @Autowired
            private ProcessRepository processRepository;

    ProcessOutputDto processOutputDto = new ProcessOutputDto();

    public ProcessOutputDto getProcessOutputDto(Integer processId){
        Optional<Process> processDetail = processRepository.findById(processId);
        if(!processDetail.isPresent()){
            return null;
        }

        List<ProcessOutput> data = processOutputRepository.findByProcess(processDetail.get());

        List listOutput = new ArrayList();
        for (ProcessOutput Data : data){
            listOutput.add(Data.getOutput());
            processOutputDto.setProcess(Data.getProcess());
        }
            processOutputDto.setOutputs(listOutput);
        return processOutputDto;
    }
}
