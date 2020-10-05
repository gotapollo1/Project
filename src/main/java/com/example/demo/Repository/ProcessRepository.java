package com.example.demo.Repository;

import com.example.demo.Model.Process;
import org.apache.tomcat.jni.Proc;
import org.aspectj.weaver.ast.Literal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface    ProcessRepository extends JpaRepository <Process,Integer>{
    Optional<Process>findById(Integer id);

}
