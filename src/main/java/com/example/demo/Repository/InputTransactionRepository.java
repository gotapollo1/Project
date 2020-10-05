package com.example.demo.Repository;

import com.example.demo.Model.Input;
import com.example.demo.Model.InputTransaction;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InputTransactionRepository extends JpaRepository<InputTransaction,Integer> {
    List<InputTransaction>findByProcessInputId(Integer id);
    List<InputTransaction>findByJobDateBetween(LocalDate startDate, LocalDate endDate);
    List<InputTransaction>findByProcessInputIdAndJobDateBetweenOrderByProcessInputIdAscJobDateAsc(Integer processInputID, LocalDate startDate, LocalDate endDate);
    Optional<InputTransaction> findById(Integer id);
}
