package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "process_input_h")
public class ProcessInput {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "processID")
    private Process process;

    @ManyToOne
    @JoinColumn(name = "inputID")
    private Input input;

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

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}
