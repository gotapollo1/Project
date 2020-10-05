package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "input_transaction")
public class InputTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "process_input_id")
    private Integer processInputId;
    private Integer keyID;
    private String value;
    private LocalDate jobDate;
    private LocalDate updateDate;
    private String updateBY;

    public LocalDate getJobDate() {
        return jobDate;
    }

    public void setJobDate(LocalDate jobDate) {
        this.jobDate = jobDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBY() {
        return updateBY;
    }

    public void setUpdateBY(String updateBY) {
        this.updateBY = updateBY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcessInputId() {
        return processInputId;
    }

    public void setProcessInputId(Integer processInputId) {
        this.processInputId = processInputId;
    }

    public Integer getKeyID() {
        return keyID;
    }

    public void setKeyID(Integer keyID) {
        this.keyID = keyID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
