package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "process_out_h")
public class ProcessOutput {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "processID")
    private Process process;
    @ManyToOne
    @JoinColumn(name = "outputID")
    private Output output;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }
}
