package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "output_map_input")
public class OutputInput {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "outputID")
    private Output output;

    @ManyToOne
    @JoinColumn(name = "inputID")
    private Input input;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}
