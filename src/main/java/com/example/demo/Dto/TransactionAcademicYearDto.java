package com.example.demo.Dto;

import com.example.demo.Model.Input;
import com.example.demo.Model.InputTransaction;
import com.example.demo.Model.ProcessInput;

import java.util.List;

public class TransactionAcademicYearDto {
    private List<InputTransaction> inputTransactionList;

    private Integer AcademicYear ;



    public List<InputTransaction> getInputTransactionList() {
        return inputTransactionList;
    }

    public void setInputTransactionList(List<InputTransaction> inputTransactionList) {
        this.inputTransactionList = inputTransactionList;
    }

    public Integer getAcademicYear() {
        return AcademicYear;
    }

    public void setAcademicYear(Integer academicYear) {
        AcademicYear = academicYear;
    }
}
