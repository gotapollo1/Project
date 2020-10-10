package com.example.demo.Service;

import com.example.demo.Dto.CheckInputDto1;
import com.example.demo.Dto.CheckInputDto2;
import com.example.demo.Dto.GraphDataDto;
import com.example.demo.Dto.TransactionProcessDto;
import com.example.demo.Model.*;
import com.example.demo.Model.Process;
import com.example.demo.Repository.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InputTransactionService {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate ldn = LocalDate.now();
    private Integer Month = ldn.getMonthValue();
    private Integer year = ldn.getYear();

    DateTimeFormatter formatter;
    private List<InputTransaction> inputTransactionsList = new ArrayList<>();
    @Autowired
    InputTransactionRepository inputTransactionRepository;
    @Autowired
    OutputInputRepository outputInputRepository;
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProcessInputRepository processInputRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserProcessRepository userProcessRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    AgencyProcessRepository agencyProcessRepository;



   // LocalDate ld = LocalDate.now();

    public InputTransaction addInput(InputTransaction inputTransaction){
        inputTransactionsList.add(inputTransaction);
        inputTransaction.setJobDate(ldn);
        inputTransaction.setUpdateDate(ldn);
        return inputTransactionRepository.save(inputTransaction);
    }
    public InputTransaction updateInput(Integer id, InputTransaction inputTransaction){
            InputTransaction existingInput =inputTransactionRepository.findById(id).get();
                existingInput.setProcessInputId(inputTransaction.getProcessInputId());
                existingInput.setKeyID(inputTransaction.getKeyID());
                existingInput.setValue(inputTransaction.getValue());
                existingInput.setUpdateDate(ldn);
                existingInput.setUpdateBY(inputTransaction.getUpdateBY());

        return inputTransactionRepository.save(existingInput);
    }

    public TransactionProcessDto getInput(Integer id , String startDate , String endDate ) {
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        List<InputTransaction> dataList = new ArrayList<>();
        TransactionProcessDto transactionProcessDto = new TransactionProcessDto();
        List<InputTransaction> allDataList = inputTransactionRepository.findAll();
        List<InputTransaction> existingProcess = inputTransactionRepository.findByProcessInputId(id);
        if (id != null && !startDate.isEmpty() && !endDate.isEmpty()) {
            LocalDate stDate = LocalDate.parse(startDate, formatter);
            LocalDate edDate = LocalDate.parse(endDate, formatter);
            for (InputTransaction Data : existingProcess) {
                if (Data.getJobDate().isAfter(stDate) && Data.getJobDate().isBefore(edDate)
                        || Data.getJobDate().equals(stDate) || Data.getJobDate().equals(edDate)) {
                    dataList.add(Data);
                    transactionProcessDto.setProcessInputId(id);
                }
            }
            transactionProcessDto.setInputTransaction(dataList);
            return transactionProcessDto;
        }
        else if(id != null & startDate.isEmpty() && endDate.isEmpty()) {
            for (InputTransaction Data : existingProcess) {
                dataList.add(Data);
                transactionProcessDto.setProcessInputId(id);
            }
            transactionProcessDto.setInputTransaction(dataList);
            return transactionProcessDto;
        }
        else if( id == null && startDate.isEmpty() && endDate.isEmpty()) {
            for (InputTransaction Data : allDataList) {
                dataList.add(Data);
            }
            transactionProcessDto.setInputTransaction(dataList);
                return transactionProcessDto;

        }
        else if (id == null && !startDate.isEmpty() && !endDate.isEmpty()){
            LocalDate stDate = LocalDate.parse(startDate, formatter);
            LocalDate edDate = LocalDate.parse(endDate, formatter);
            List<InputTransaction> dateDataList = inputTransactionRepository.findByJobDateBetween(stDate,edDate);
            for(InputTransaction Data : dateDataList) {
                if(Data.getJobDate().isAfter(stDate) && Data.getJobDate().isBefore(edDate)
                        || Data.getJobDate().equals(stDate) || Data.getJobDate().equals(edDate)) {
                    dataList.add(Data);
                }
            }
            transactionProcessDto.setInputTransaction(dataList);
            return transactionProcessDto;
        }
        return null;
    }

    //ข้อมูลใต้กราฟ
    public List<GraphDataDto> getGraphData(Integer outputID) {
        List<GraphDataDto> graphDataList = new ArrayList<>();
        if(Month>=7) {
            startDate = LocalDate.of(year, 7, 1);
            endDate = LocalDate.of(year + 1, 6, 30);
        }
        else if(Month < 7) {
            startDate = LocalDate.of(year - 1, 7, 1);
            endDate = LocalDate.of(year, 6, 30);
        }
            Optional<Output> outputsDetail = outputRepository.findById(outputID);
            if(!outputsDetail.isPresent()){
                return null;
            }
            List<OutputInput> outputInputs = outputInputRepository.findByOutput(outputsDetail.get());
            for(OutputInput Dataa : outputInputs){
                List<InputTransaction> data = inputTransactionRepository.findByProcessInputIdAndJobDateBetweenOrderByProcessInputIdAscJobDateAsc
                        (Dataa.getInput().getId(), startDate,endDate);
                for(InputTransaction Data : data){
                    GraphDataDto graphDataDto = new GraphDataDto();
                    graphDataDto.setValue(Data.getValue());
                    graphDataDto.setJobDate(Data.getJobDate());
                    graphDataDto.setUpdateDate(Data.getUpdateDate());
                    graphDataDto.setUpdateName(Data.getUpdateBY());
                    Optional<Input> dataInput = inputRepository.findById(Dataa.getInput().getId());
                    graphDataDto.setInputName(dataInput.get().getDescription());
                    Integer id = Integer.parseInt(Data.getUpdateBY());
                    Optional<User> dataUser = userRepository.findById(id);
                    graphDataDto.setUpdateName(dataUser.get().getName_user());
                    graphDataList.add(graphDataDto);
                }
            }
            return graphDataList;
    }
    public List<CheckInputDto2> getCheckInput(){
       // LocalDate ldn = LocalDate.of(2020,6,20);   เอาไว้ Test case
        if(Month>=7) {
            startDate = LocalDate.of(year, 7, 1);
            endDate = LocalDate.of(year + 1, 6, 30);
        }
        else if(Month < 7) {
             startDate = LocalDate.of(year - 1, 7, 1);
             endDate = LocalDate.of(year, 6, 30);
        }
        List<CheckInputDto2> checkInputDto2List = new ArrayList<>();;
        List<Agency> agencyDetail = agencyRepository.findAll();
            for (Agency DataAgency : agencyDetail) {
                    List<CheckInputDto1> checkInputDto1List = new ArrayList<>();
                    CheckInputDto2 checkInputDto2 = new CheckInputDto2();
                    List<AgencyProcess> agencyProcesses = agencyProcessRepository.findByAgencyId(DataAgency.getId());
                    for (AgencyProcess DataAgencyProcess : agencyProcesses) {
                        if (DataAgencyProcess.getAgency().getId() == 6 && DataAgencyProcess.getProcess().getId() == 21) {
                            List<Integer>  idList1 = new ArrayList<>();
                            idList1.add(63);
                            idList1.add(64);
                            idList1.add(65);
                            List<ProcessInput> dataListInputFix = processInputRepository.findByInputIdIn(idList1);
                            for(ProcessInput dataInputFix : dataListInputFix){
                                List<InputTransaction> inputTransactions = inputTransactionRepository.findByProcessInputIdAndJobDateBetween
                                        (dataInputFix.getInput().getId(),startDate,endDate);
                                Optional<InputTransaction> optionalInputTransaction = inputTransactions.stream().findFirst();
                                if (!optionalInputTransaction.isPresent()) { //input ที่ไม่มีอยู่ใน Table Transaction
                                    CheckInputDto1 checkInputDto1 = new CheckInputDto1();
                                    checkInputDto2.setAgency(DataAgency.getName());
                                    checkInputDto1.setProcess(dataInputFix.getProcess().getDescription());
                                    checkInputDto1.setInput(dataInputFix.getInput());
                                    checkInputDto1List.add(checkInputDto1);
                                    checkInputDto2.setInputList(checkInputDto1List);
                                }
                            }
                        } else if (DataAgencyProcess.getAgency().getId() == 7 && DataAgencyProcess.getProcess().getId() == 21) {
                            List<Integer> idList2 = new ArrayList<>();
                            idList2.add(66);
                            idList2.add(67);
                            idList2.add(68);
                            List<ProcessInput> dataListInputFix = processInputRepository.findByInputIdIn(idList2);
                            for(ProcessInput dataInputFix : dataListInputFix){
                                List<InputTransaction> inputTransactions = inputTransactionRepository.findByProcessInputIdAndJobDateBetween
                                        (dataInputFix.getInput().getId(),startDate,endDate);
                                Optional<InputTransaction> optionalInputTransaction = inputTransactions.stream().findFirst();
                                if (!optionalInputTransaction.isPresent()) { //input ที่ไม่มีอยู่ใน Table Transaction
                                    CheckInputDto1 checkInputDto1 = new CheckInputDto1();
                                    checkInputDto2.setAgency(DataAgency.getName());
                                    checkInputDto1.setProcess(dataInputFix.getProcess().getDescription());
                                    checkInputDto1.setInput(dataInputFix.getInput());
                                    checkInputDto1List.add(checkInputDto1);
                                    checkInputDto2.setInputList(checkInputDto1List);
                                }
                            }

                        } else {
                            List<ProcessInput> processInputs = processInputRepository.findByProcessId(DataAgencyProcess.getProcess().getId());
                            for (ProcessInput DataProcessInput : processInputs) {
                                List<InputTransaction> inputTransactions = inputTransactionRepository.findByProcessInputIdAndJobDateBetween
                                        (DataProcessInput.getInput().getId(), startDate, endDate);
                                // Convert List to Optional
                                Optional<InputTransaction> optionalInputTransaction = inputTransactions.stream().findFirst();
                                if (!optionalInputTransaction.isPresent()) { //input ที่ไม่มีอยู่ใน Table Transaction
                                    CheckInputDto1 checkInputDto1 = new CheckInputDto1();
                                    checkInputDto2.setAgency(DataAgency.getName());
                                    checkInputDto1.setProcess(DataProcessInput.getProcess().getDescription());
                                    checkInputDto1.setInput(DataProcessInput.getInput());
                                    checkInputDto1List.add(checkInputDto1);
                                    checkInputDto2.setInputList(checkInputDto1List);
                                }

                            }
                        }
                    }

                    checkInputDto2List.add(checkInputDto2);
            }
            return checkInputDto2List;
}
}
