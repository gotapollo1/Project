package com.example.demo.Dto;

import com.example.demo.Model.Input;
import com.example.demo.Model.Output;

import java.util.List;

public class OutputInputDto {
    private Output output;
    private List<Input> inputList;

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public List<Input> getInputList() {
        return inputList;
    }

    public void setInputList(List<Input> inputList) {
        this.inputList = inputList;
    }
}
