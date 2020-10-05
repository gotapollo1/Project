package com.example.demo.Dto;

import com.example.demo.Model.Agency;

import java.util.List;

public class AgencyProcessDto {
    private Agency agency;
    private List<Process> processList;

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public List<Process> getProcessList() {
        return processList;
    }

    public void setProcessList(List<Process> processList) {
        this.processList = processList;
    }
}
