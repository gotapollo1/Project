package com.example.demo.Service;

import com.example.demo.Model.Configuration;
import com.example.demo.Repository.ConfigurationRepository;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;

    public Configuration changeValue(Integer check) {
        Optional<Configuration> configurationsDetail = configurationRepository.findById(1);
        if (check == 0) {
            String check1 = Integer.toString(check);
            configurationsDetail.get().setValue(check1);
        } else if (check == 1) {
            String check1 = Integer.toString(check);
            configurationsDetail.get().setValue(check1);
        } else return null;
        return configurationRepository.save(configurationsDetail.get());
    }
    public String getValueOpenClose(){
            List<Configuration> value = configurationRepository.findByCategoryAndSub("system","openClose");
            for(Configuration Data : value){
               return Data.getValue();
            }
            return null;
    }
}
