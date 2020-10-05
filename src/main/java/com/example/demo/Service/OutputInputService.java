package com.example.demo.Service;

import com.example.demo.Dto.OutputInputDto;
import com.example.demo.Model.Output;
import com.example.demo.Model.OutputInput;
import com.example.demo.Repository.OutputInputRepository;
import com.example.demo.Repository.OutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OutputInputService {
    @Autowired
    private OutputInputRepository outputInputRepository;
    @Autowired
    private OutputRepository outputRepository;
    OutputInputDto outputInputDto = new OutputInputDto();

    public OutputInputDto getInputByOutputID(Integer outputID){
        Optional<Output> outputDetail = outputRepository.findById(outputID);
        if(!outputDetail.isPresent()){
            return null;
        }
        List<OutputInput> data = outputInputRepository.findByOutput(outputDetail.get());
        List listInput = new ArrayList();
        for(OutputInput Data : data){
            listInput.add(Data.getInput());
            outputInputDto.setOutput(Data.getOutput());
        }
        outputInputDto.setInputList(listInput);
        return outputInputDto;
    }
}
