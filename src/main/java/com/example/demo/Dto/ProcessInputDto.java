package com.example.demo.Dto;

import com.example.demo.Model.Input;
import com.example.demo.Model.Process;

import java.util.List;

public class ProcessInputDto {
    private String name;
    private String description;
    private List<Input> inputs;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }
}
