package com.example.demo.Service;

import com.fasterxml.jackson.datatype.jsr310.ser.MonthDaySerializer;
import org.springframework.stereotype.Service;

import javax.swing.plaf.metal.OceanTheme;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Service
public class AcademicService {
    DateTimeFormatter formatter;
    public Integer turnYear (String date){
        formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate ld = LocalDate.parse(date,formatter);
        Integer month = ld.getMonthValue();
        Integer year = ld.getYear();
        if(month >= 7){
            return year+543;
        }

        return year+542;
    }
}
