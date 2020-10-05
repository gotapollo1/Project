package com.example.demo.Service;

import com.example.demo.Dto.AgencyProcessDto;
import com.example.demo.Model.Agency;
import com.example.demo.Model.AgencyProcess;
import com.example.demo.Repository.AgencyProcessRepository;
import com.example.demo.Repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class AgencyProcessService {
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private AgencyProcessRepository agencyProcessRepository;
    AgencyProcessDto agencyProcessDto = new AgencyProcessDto();

    public AgencyProcessDto getProcessByAgencyID(Integer agencyID){
        Optional<Agency> agencyDetail = agencyRepository.findById(agencyID);
        if(!agencyDetail.isPresent()){
            return null;
        }
        List<AgencyProcess> data = agencyProcessRepository.findByAgency(agencyDetail.get());
        List listProcess = new ArrayList();
        for(AgencyProcess Data : data){
            listProcess.add(Data.getProcess());
            agencyProcessDto.setAgency(Data.getAgency());
        }
        agencyProcessDto.setProcessList(listProcess);
        return agencyProcessDto;
    }
}
