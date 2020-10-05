package com.example.demo.Dto;

import com.example.demo.Model.Output;
import com.example.demo.Model.Process;

import javax.persistence.*;
import java.util.List;


public class ProcessOutputDto {
    private Process process;
    private List<Output> outputs;

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }
}
