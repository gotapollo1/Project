package com.example.demo.Service;

import com.example.demo.Dto.CheckInputDto1;
import com.example.demo.Dto.CheckInputDto2;
import com.example.demo.Dto.GraphDataDto;
import com.example.demo.Dto.TransactionProcessDto;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InputTransactionService {
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



    LocalDate ld = LocalDate.now();

    public InputTransaction addInput(InputTransaction inputTransaction){
        inputTransactionsList.add(inputTransaction);
        inputTransaction.setJobDate(ld);
        inputTransaction.setUpdateDate(ld);
        return inputTransactionRepository.save(inputTransaction);
    }
    public InputTransaction updateInput(Integer id, InputTransaction inputTransaction){
            InputTransaction existingInput =inputTransactionRepository.findById(id).get();
                existingInput.setProcessInputId(inputTransaction.getProcessInputId());
                existingInput.setKeyID(inputTransaction.getKeyID());
                existingInput.setValue(inputTransaction.getValue());
                existingInput.setUpdateDate(ld);
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
                if (Data.getUpdateDate().isAfter(stDate) && Data.getUpdateDate().isBefore(edDate)
                        || Data.getUpdateDate().equals(stDate) || Data.getUpdateDate().equals(edDate)) {
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
                if(Data.getUpdateDate().isAfter(stDate) && Data.getUpdateDate().isBefore(edDate)
                        || Data.getUpdateDate().equals(stDate) || Data.getUpdateDate().equals(edDate)) {
                    dataList.add(Data);
                }
            }
            transactionProcessDto.setInputTransaction(dataList);
            return transactionProcessDto;
        }
        return null;
    }

    //by ปีการศึกษาปัจจุบัน
    public List<GraphDataDto> getTransactionByNow(Integer outputID) {
        List<GraphDataDto> graphDataList = new ArrayList<>();
        LocalDate ldn = LocalDate.now();
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        Integer Month = ldn.getMonthValue();
        Integer year = ldn.getYear();
        if (Month >= 7) {
            LocalDate startDate = LocalDate.of(year,8,1);
            LocalDate endDate = LocalDate.of(year+1,7,31);
            Optional<Output> outputsDetail = outputRepository.findById(outputID);
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
        else if(Month < 7){
            LocalDate startDate = LocalDate.of(year-1,8,1);
            LocalDate endDate = LocalDate.of(year,7,31);
            Optional<Output> outputsDetail = outputRepository.findById(outputID);
            List<OutputInput> outputInputs = outputInputRepository.findByOutput(outputsDetail.get());
            for(OutputInput Dataa : outputInputs) {
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
        return null;
    }
    public List<CheckInputDto2> getCheckInput(){
        List<CheckInputDto2> checkInputDto2List = new ArrayList<>();
        List<CheckInputDto1> checkInputDto1List = new ArrayList<>();
        List<AgencyProcess> agencyProcess = agencyProcessRepository.findAll();
        for(AgencyProcess dataAgencyProcess : agencyProcess){
            List<ProcessInput> processInputDetail = processInputRepository.findByProcessId(dataAgencyProcess.getProcess().getId());
            for(ProcessInput Data : processInputDetail){
                List<InputTransaction> dataInput = inputTransactionRepository.findByProcessInputId(Data.getInput().getId());
                for(InputTransaction Dataa : dataInput){
                if(Dataa.getProcessInputId() != null) {
                    CheckInputDto2 checkInputDto2 = new CheckInputDto2();
                    CheckInputDto1 checkInputDto1 = new CheckInputDto1();
                    checkInputDto1.setProcess(Data.getProcess().getDescription());
                    checkInputDto1.setInput(Data.getInput().getDescription());
                    checkInputDto1List.add(checkInputDto1);
                    checkInputDto2.setAgency(dataAgencyProcess.getAgency().getName());
                    checkInputDto2.setInputList(checkInputDto1List);
                    checkInputDto2List.add(checkInputDto2);
                }
                }
                return checkInputDto2List;

            }
        }
        return checkInputDto2List;
    }
}
