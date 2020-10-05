package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "agency_process")
public class AgencyProcess {
    @Id
    Integer id;
    @ManyToOne
    @JoinColumn(name = "agencyID")
    private Agency agency;

    @ManyToOne
    @JoinColumn(name = "processID")
    private Process process;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
