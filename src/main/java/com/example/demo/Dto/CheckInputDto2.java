package com.example.demo.Dto;

import com.example.demo.Model.Agency;
import com.example.demo.Model.Input;

import java.util.List;

public class CheckInputDto2 {
    private String agency;
    private List<CheckInputDto1> inputList;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public List<CheckInputDto1> getInputList() {
        return inputList;
    }

    public void setInputList(List<CheckInputDto1> inputList) {
        this.inputList = inputList;
    }
}
