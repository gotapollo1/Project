package com.example.demo.Dto;

import com.example.demo.Model.InputTransaction;

import java.util.List;

public class TransactionProcessDto {
    private Integer ProcessInputId;
    private List<InputTransaction> inputTransaction;

    public Integer getProcessInputId() {
        return ProcessInputId;
    }

    public void setProcessInputId(Integer processInputId) {
        ProcessInputId = processInputId;
    }

    public List<InputTransaction> getInputTransaction() {
        return inputTransaction;
    }

    public void setInputTransaction(List<InputTransaction> inputTransaction) {
        this.inputTransaction = inputTransaction;
    }
}
