package com.example.demo.Model;

import javax.persistence.*;

@Entity
@Table(name = "user_process_h")
public class UserProcess {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "processID")
    private Process process_r;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Process getProcess_r() {
        return process_r;
    }

    public void setProcess_r(Process process_r) {
        this.process_r = process_r;
    }
}
