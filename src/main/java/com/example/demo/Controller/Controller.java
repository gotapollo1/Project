package com.example.demo.Controller;

import com.example.demo.Dto.*;
import com.example.demo.Model.*;
import com.example.demo.Model.Process;
import com.example.demo.Repository.*;
import com.example.demo.Service.*;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class Controller {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProcessRepository userProcessRepository;
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private InputTransactionRepository inputTransactionRepository;
    @Autowired
    ProcessByUserIDService processByUserIdService;
    @Autowired
    ProcessInputRepository processInputRepository;
    @Autowired
    UserProcessService userProcessService;
    @Autowired
    ProcessInputService processInputService;
    @Autowired
    InputTransactionService inputTransactionService;
    @Autowired
    ProcessOutputService processOutputService;
    @Autowired
    AcademicService academicService;
    @Autowired
    OutputInputService outputInputService;

    @Autowired
    AgencyProcessService agencyProcessService;
    @Autowired
    AgencyRepository agencyRepository;


    @GetMapping("/user")
    public List<User> findAllUser() {
        return userService.getAll();
    }

    @GetMapping("/userById")
    public Optional<User> getUserByID(@RequestParam Integer userID) {
        return userRepository.findById(userID);
    }

    @GetMapping("/process_r")
    public List<Process> findAllProduct() {
        return processRepository.findAll();
    }

    @GetMapping("/user/process_r")
    public List<UserProcess> getUserProcess_r() {
        return userProcessRepository.findAll();
    }

    @GetMapping("/user/process_r/{id}")
    public Optional<UserProcess> getUserProcess_rByID(@PathVariable Integer id) {
        return userProcessRepository.findById(id);
    }

    //    @GetMapping("/process_r/ByUserID/")
//    public List<UserProcess> getProcess_rByUserID(@RequestParam Integer userID){
//        return user_process_h_repository.findProcessByUserId(userID);
//    }
    @GetMapping("/process")
    public Optional<ProcessInput> getProcess_Input(@RequestParam Integer id) {
        return processInputRepository.findById(id);
    }
//    @GetMapping("/input/ByProcessID")
//    public List<ProcessInput> getInputByProcessID(@RequestParam Integer processID){
//        return  processInputService.getByProcessID(processID);
//    }

    //ดูโพรเซส จาก UserID ********************** Dto
    @GetMapping("/user/AllProcess")
    public UserProcessDto getAllProcessUserID(@RequestParam Integer userID) {
        return userProcessService.getAllProUserID(userID);
    }

    //ดู Input จาก ProcessID ****************** Dto
    @GetMapping("/process/AllInput")
    public ProcessInputDto getAllInputProcessID(@RequestParam Integer processID) {
        return processInputService.getByProcessID(processID);
    }

    // tb in_transaction
    @PostMapping("/addInput")
    public InputTransaction AddInput(@RequestBody InputTransaction inputTransaction) {
        return inputTransactionService.addInput(inputTransaction);
    }

    // tb input_transaction
    @PutMapping("/updateInput")
    public InputTransaction updateInput(@RequestParam Integer id, @RequestBody InputTransaction inputTransaction) {
        return inputTransactionService.updateInput(id, inputTransaction);
    }

    //ดู Output จาก ProcessID ****************** Dto
    @GetMapping("/outputByProcessId")
    public ProcessOutputDto getAllOutput(@RequestParam Integer processId) {
        return processOutputService.getProcessOutputDto(processId);
    }

    @GetMapping("/TurnOff")
    public User turnStatus(@RequestParam Integer check) {
        return userService.turnOff(check);
    }

    @GetMapping("/Date")
    public Integer turnAcademic(@RequestParam String date) {
        return academicService.turnYear(date);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestParam Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    //ดู Transaction จาก ProcessID โดยเลือกช่วงวันที่่ได้
    @GetMapping("/getTransaction/byProcess")
    public TransactionProcessDto getInputByProcessInputId(@RequestParam Integer ProcessInputId, @RequestParam String startDate,
                                                          @RequestParam String endDate) {
        return inputTransactionService.getInput(ProcessInputId, startDate, endDate);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam Integer id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    //เรียกดู Input จาก OutputID ****************** Dto
    @GetMapping("/input/ByOutputID")
    public OutputInputDto getInputByOutputID(@RequestParam Integer outputID) {
        return outputInputService.getInputByOutputID(outputID);
    }

    //ดู Transaction โดยเรียกจากวันที่ปัจจุบัน
    @GetMapping("/transaction/now")
    public List<GraphDataDto> getTransactionByDateNow(Integer outputID) {
        return inputTransactionService.getTransactionByNow(outputID);
    }

    @GetMapping("/process/ByAgency")
    public AgencyProcessDto getProcessByAgencyID(@RequestParam Integer agencyID) {
        return agencyProcessService.getProcessByAgencyID(agencyID);
    }
    @GetMapping("/agency")
    public List<Agency> getAgency(){
        return agencyRepository.findAll();
    }
    @GetMapping("/checkInput")
    public List<CheckInputDto2> getCheckInput(){
        return inputTransactionService.getCheckInput();
    }

}

