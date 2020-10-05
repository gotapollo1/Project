package com.example.demo.Dto;

import com.example.demo.Model.User;
import com.example.demo.Model.UserProcess;

import java.util.List;

public class UserProcessDto {
    private User user;
    private List<Process> process;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Process> getProcess() {
        return process;
    }

    public void setProcess(List<Process> process) {
        this.process = process;
    }
}
